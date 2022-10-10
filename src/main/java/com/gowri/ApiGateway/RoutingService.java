package com.gowri.ApiGateway;

import com.gowri.ApiGateway.client.RoutingGatewayClient;
import com.gowri.ApiGateway.config.RoutingConfigs;
import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RoutingService {

    @Autowired
    private RoutingConfigs routingConfigs;

    @Autowired
    private RoutingGatewayClient routingGatewayClient;

    public ResponseEntity<Object> forward(HttpServletRequest request) throws IOException {
        IncomingRequest incomingRequest = convert(request);
        String serviceForwardUrl = findServiceForwardUrl(incomingRequest.getUrl())
                .orElseThrow(RuntimeException::new);
        return routingGatewayClient.send(serviceForwardUrl, incomingRequest);
    }

    private  IncomingRequest convert(HttpServletRequest request) throws IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String body = request.getReader()
                .lines()
                .collect(Collectors.joining(System.lineSeparator()));
        IncomingRequest incomingRequest = new IncomingRequest();
        incomingRequest.setUrl(uri);
        incomingRequest.setHttpMethod(HttpMethod.resolve(method));
        incomingRequest.setBody(body);
        HttpHeaders httpHeaders = Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
        incomingRequest.setQueryString(request.getQueryString());
        incomingRequest.setHttpHeaders(httpHeaders);
        return incomingRequest;
    }


    public Optional<String> findServiceForwardUrl(String path) {
        Map<Pattern, String> patternToBaseUrlMatch = routingConfigs.getPatternToBaseUrlMatch();
        for (Pattern pattern: patternToBaseUrlMatch.keySet()) {
            Matcher matcher = pattern.matcher(path);
            boolean matches = matcher.matches();
            if (matches) {
                return Optional.of(patternToBaseUrlMatch.get(pattern));
            }
        }
        return Optional.empty();
    }

}

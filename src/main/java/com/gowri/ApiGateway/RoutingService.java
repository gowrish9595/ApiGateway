package com.gowri.ApiGateway;

import com.gowri.ApiGateway.config.RoutingConfigs;
import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RoutingService {

    @Autowired
    private RoutingConfigs routingConfigs;

    @Autowired
    private RoutingGatewayClient routingGatewayClient;

    public ResponseEntity<Object> forward(IncomingRequest incomingRequest) {
        String serviceForwardUrl = findServiceForwardUrl(incomingRequest.getUrl())
                .orElseThrow(RuntimeException::new);
        return routingGatewayClient.send(serviceForwardUrl, incomingRequest);
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

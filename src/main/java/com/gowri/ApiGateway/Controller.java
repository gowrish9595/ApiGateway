package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.handler.impl.AuthenticationHandler;
import com.gowri.ApiGateway.handler.impl.RoutingHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private RoutingGatewayClient routingGatewayClient;


    @Autowired
    AuthenticationHandler authenticationHandler;

    @Autowired
    RoutingHandlerImpl routingHandler;

    @RequestMapping("/v1/**")
    public ResponseEntity<Object> get(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        IncomingRequest request1 = new IncomingRequest();
        request1.setUrl(uri);
        request1.setHttpMethod(HttpMethod.resolve(method));
        request1.setBody(body);
        HttpHeaders httpHeaders = Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        h -> Collections.list(request.getHeaders(h)),
                        (oldValue, newValue) -> newValue,
                        HttpHeaders::new
                ));
        request1.setQueryString(request.getQueryString());
        request1.setHttpHeaders(httpHeaders);
        authenticationHandler.handle(request1);
        authenticationHandler.setHandler(routingHandler);
        return authenticationHandler.handle(request1);
    }
}

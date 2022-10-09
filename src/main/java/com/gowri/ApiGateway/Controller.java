package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @Autowired
    private RoutingGatewayClient routingGatewayClient;

    @RequestMapping("/**")
    public ResponseEntity<Object> get(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        IncomingRequest request1 = new IncomingRequest();
        request1.setUrl(uri);
        request1.setHttpMethod(HttpMethod.resolve(method));
        request1.setBody(body);
        return routingGatewayClient.send(request1);
    }
}
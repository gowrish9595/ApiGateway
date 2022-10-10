package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.handler.impl.AuthenticationHandler;
import com.gowri.ApiGateway.handler.impl.RoutingHandlerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
        CommonResponse commonResponse = new CommonResponse();
        authenticationHandler.setHandler(routingHandler);
        authenticationHandler.handle(request, commonResponse);
        return commonResponse.getResponseEntity();
    }
}

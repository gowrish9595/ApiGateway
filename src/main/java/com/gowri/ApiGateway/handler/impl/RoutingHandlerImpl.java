package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.exception.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.service.RoutingService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class RoutingHandlerImpl extends BaseHandler {

    RoutingService routingService;

    public RoutingHandlerImpl(RoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public void handle(HttpServletRequest request, CommonResponse response) {
        try {
            response.setResponseEntity(routingService.forward(request));
        } catch (IOException e) {
            throw ApiGatewayExceptionFactory.genericError("");
        }
    }
}

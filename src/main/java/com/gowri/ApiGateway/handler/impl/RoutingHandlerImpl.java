package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.CommonResponse;
import com.gowri.ApiGateway.RoutingService;
import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.util.ResponseEntityUtil;
import org.apache.tomcat.util.http.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

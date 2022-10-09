package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.RoutingService;
import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class RoutingHandlerImpl extends  BaseHandler {

    @Autowired
    RoutingService routingService;
    @Override
    public ResponseEntity<Object> handle(IncomingRequest request) {
        return routingService.forward(request);
    }
}

package com.gowri.ApiGateway.handler;

import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.handler.impl.AuthenticationHandler;
import com.gowri.ApiGateway.handler.impl.RoutingHandlerImpl;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

    private final AuthenticationHandler authenticationHandler;
    private final RoutingHandlerImpl routingHandler;

    public HandlerFactory(AuthenticationHandler authenticationHandler, RoutingHandlerImpl routingHandler) {
        this.authenticationHandler = authenticationHandler;
        this.routingHandler = routingHandler;
    }

    public Handler createHandlers() {
        authenticationHandler.setHandler(routingHandler);
        return authenticationHandler;
    }
}

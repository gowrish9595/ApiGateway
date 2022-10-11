package com.gowri.ApiGateway.handler;

import com.gowri.ApiGateway.handler.impl.AuthenticationHandler;
import com.gowri.ApiGateway.handler.impl.RequestLoggingHandler;
import com.gowri.ApiGateway.handler.impl.RoutingHandlerImpl;
import org.springframework.stereotype.Component;

@Component
public class HandlerFactory {

    private final AuthenticationHandler authenticationHandler;
    private final RoutingHandlerImpl routingHandler;

    private final RequestLoggingHandler loggingHandler;

    public HandlerFactory(AuthenticationHandler authenticationHandler, RoutingHandlerImpl routingHandler, RequestLoggingHandler loggingHandler) {
        this.authenticationHandler = authenticationHandler;
        this.routingHandler = routingHandler;
        this.loggingHandler = loggingHandler;
    }

    public Handler createHandlers() {
        authenticationHandler.setHandler(routingHandler);
        loggingHandler.setHandler(authenticationHandler);
        return loggingHandler;
    }
}

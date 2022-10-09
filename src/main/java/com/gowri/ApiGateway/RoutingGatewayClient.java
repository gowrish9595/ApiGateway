package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.http.ResponseEntity;

public interface RoutingGatewayClient {
    ResponseEntity<Object> send(String baseUrl, IncomingRequest incomingRequest);
}

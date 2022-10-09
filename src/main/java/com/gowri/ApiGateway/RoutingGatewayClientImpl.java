package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RoutingGatewayClientImpl implements  RoutingGatewayClient{

    private final RestTemplate client;

    public RoutingGatewayClientImpl(RestTemplate client) {
        this.client = client;
    }

    @Override
    public ResponseEntity<Object> send(IncomingRequest incomingRequest) {
        RequestEntity<Object> body = RequestEntity
                .method(incomingRequest.getHttpMethod(), incomingRequest.getUrl())
                .body(incomingRequest.getBody());
        ResponseEntity<Object> response = client
                .exchange(body, Object.class);
        return response;

    }
}

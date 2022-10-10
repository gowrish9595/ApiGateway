package com.gowri.ApiGateway;

import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

@Component
public class RoutingGatewayClientImpl implements  RoutingGatewayClient{

    private final RestTemplate client;

    public RoutingGatewayClientImpl(RestTemplate client) {
        this.client = client;
    }

    @Override
    public ResponseEntity<Object> send(String baseUrl, IncomingRequest incomingRequest) {
        String url = baseUrl + incomingRequest.getUrl();
        if(StringUtils.hasText(incomingRequest.getQueryString())) {
            url = url + "?" + incomingRequest.getQueryString();
        }
        RequestEntity<Object> body = RequestEntity
                .method(incomingRequest.getHttpMethod(), url)
                .headers(incomingRequest.getHttpHeaders())
                .body(incomingRequest.getBody());
        ResponseEntity<Object> response = client
                .exchange(body, Object.class);
        return response;

    }
}

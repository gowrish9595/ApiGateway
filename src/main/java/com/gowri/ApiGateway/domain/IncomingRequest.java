package com.gowri.ApiGateway.domain;

import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

@Data
public class IncomingRequest {
    private String url;
    private HttpMethod httpMethod;
    private HttpHeaders httpHeaders;
    private String body;
}

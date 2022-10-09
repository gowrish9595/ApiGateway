package com.gowri.ApiGateway;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ApiGatewayException extends RuntimeException{
    private ApiGatewayError error;
    private String additionalErrorDetails;
}

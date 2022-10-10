package com.gowri.ApiGateway.exception;

import com.gowri.ApiGateway.exception.ApiGatewayError;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class ApiGatewayException extends RuntimeException{
    private ApiGatewayError error;
    private String additionalErrorDetails;
}

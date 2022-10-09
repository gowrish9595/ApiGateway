package com.gowri.ApiGateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ApiGatewayError {
    INTERNAL_SERVER_ERROR("E001", "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),

    AUTHENTICATION_TOKEN_NOT_FOUND("E002", "Authentication token not found", HttpStatus.UNAUTHORIZED),
    AUTHENTICATION_FAILED("E003", "Authentication failed", HttpStatus.UNAUTHORIZED),
    TOKEN_EXPIRED("E004", "Token Expired", HttpStatus.UNAUTHORIZED);

    private final String errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatusCode;
}

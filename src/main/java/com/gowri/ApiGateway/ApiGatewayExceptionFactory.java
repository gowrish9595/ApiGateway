package com.gowri.ApiGateway;

import org.springframework.http.HttpStatus;

public class ApiGatewayExceptionFactory {
    public static ApiGatewayException genericError(String errorMessage) {
        return new ApiGatewayException(ApiGatewayError.INTERNAL_SERVER_ERROR, errorMessage);
    }

    public static final ApiGatewayException AUTHENTICATION_TOKEN_NOT_FOUND =
            new ApiGatewayException(ApiGatewayError.AUTHENTICATION_TOKEN_NOT_FOUND, "");

    public static final ApiGatewayException AUTHENTICATION_FAILED =
            new ApiGatewayException(ApiGatewayError.AUTHENTICATION_FAILED, "");

    public static final ApiGatewayException TOKEN_EXPIRED =
            new ApiGatewayException(ApiGatewayError.TOKEN_EXPIRED, "");

}

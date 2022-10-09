package com.gowri.ApiGateway;

public class ApiGatewayExceptionFactory {
    public static ApiGatewayException genericError(String errorMessage) {
        return new ApiGatewayException(ApiGatewayError.INTERNAL_SERVER_ERROR, errorMessage);
    }
    public static final ApiGatewayException AUTHENTICATION_TOKEN_NOT_FOUND =
            new ApiGatewayException(ApiGatewayError.AUTHENTICATION_TOKEN_NOT_FOUND, "");
}

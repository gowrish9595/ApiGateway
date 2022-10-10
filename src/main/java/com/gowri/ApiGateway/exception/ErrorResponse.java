package com.gowri.ApiGateway.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
    private String additionalErrorDetails;
    private int httpStatusCode;
}

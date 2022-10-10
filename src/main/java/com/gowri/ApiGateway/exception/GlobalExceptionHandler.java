package com.gowri.ApiGateway.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ApiGatewayException.class)
    public ResponseEntity<Object> handleAll(ApiGatewayException ex) {
        logger.error("Api Gateway error has occurred", ex);
        ApiGatewayError error = ex.getError();
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(error.getErrorCode())
                .message(error.getErrorMessage())
                .additionalErrorDetails(ex.getAdditionalErrorDetails())
                .httpStatusCode(error.getHttpStatusCode().value())
                .build();
        logger.info("Returning error response [{}]", errorResponse);
        return new ResponseEntity<>(errorResponse, error.getHttpStatusCode());
    }
}

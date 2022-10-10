package com.gowri.ApiGateway.service;

import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.handler.Handler;
import com.gowri.ApiGateway.handler.HandlerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class ApiGatewayService {

    private final HandlerFactory handlerFactory;

    public ApiGatewayService(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
    }

    public ResponseEntity<Object> handle(HttpServletRequest request) {
        Handler handler = handlerFactory.createHandlers();
        CommonResponse commonResponse = new CommonResponse();
        handler.handle(request, commonResponse);
        return commonResponse.getResponseEntity();
    }
}

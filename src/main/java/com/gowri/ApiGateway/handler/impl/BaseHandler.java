package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.CommonResponse;
import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.handler.Handler;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract class BaseHandler implements Handler {
    Handler nextHandler;

    @Override
    public void setHandler(Handler handler) {
        this.nextHandler = handler;
    }

    void handleNext(HttpServletRequest request, CommonResponse commonResponse) {
        if (nextHandler != null) {
            nextHandler.handle(request, commonResponse);
        }
    }


}

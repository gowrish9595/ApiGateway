package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.handler.Handler;

import javax.servlet.http.HttpServletRequest;

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

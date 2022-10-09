package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.handler.Handler;
import org.springframework.http.ResponseEntity;

abstract class BaseHandler implements Handler {
    Handler nextHandler;
    @Override
    public void setHandler(Handler handler) {
      this.nextHandler = handler;
    }
}

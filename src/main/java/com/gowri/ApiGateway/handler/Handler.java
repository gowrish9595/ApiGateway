package com.gowri.ApiGateway.handler;

import com.gowri.ApiGateway.CommonResponse;

import javax.servlet.http.HttpServletRequest;

public interface Handler {
    void handle(HttpServletRequest request, CommonResponse commonResponse);
    void setHandler(Handler handler);
}

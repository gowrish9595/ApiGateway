package com.gowri.ApiGateway.handler;

import com.gowri.ApiGateway.domain.CommonResponse;

import javax.servlet.http.HttpServletRequest;

public interface Handler {
    void handle(HttpServletRequest request, CommonResponse commonResponse);
    void setHandler(Handler handler);
}

package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.ApiGatewayError;
import com.gowri.ApiGateway.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Component
public class AuthenticationHandler extends BaseHandler {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<Object> handle(IncomingRequest request) {
        if(request.getHttpHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            String token = request.getHttpHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            boolean b = jwtTokenUtil.validateToken(token);
            if(!b) {
                throw new InvalidParameterException();
            }
        } else {
            throw ApiGatewayExceptionFactory.AUTHENTICATION_TOKEN_NOT_FOUND;
        }
        if(nextHandler != null) {
           return nextHandler.handle(request);
        }
        return null;
    }
}

package com.gowri.ApiGateway.handler.impl;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gowri.ApiGateway.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.CommonResponse;
import com.gowri.ApiGateway.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@Component
@Slf4j
public class AuthenticationHandler extends BaseHandler {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public void handle(HttpServletRequest request, CommonResponse response) {
        Enumeration<String> authorizationHeaders = request.getHeaders(HttpHeaders.AUTHORIZATION);
        if (authorizationHeaders != null && authorizationHeaders.hasMoreElements() ) {
            String token = authorizationHeaders.nextElement();
            try {
                jwtTokenUtil.validateToken(token);
            } catch (TokenExpiredException tokenExpiredException) {
                throw ApiGatewayExceptionFactory.TOKEN_EXPIRED;
            } catch (AlgorithmMismatchException | MissingClaimException e) {
                throw ApiGatewayExceptionFactory.AUTHENTICATION_FAILED;
            } catch (Exception e) {
                log.error("Authentication failed", e);
                throw ApiGatewayExceptionFactory.AUTHENTICATION_FAILED;
            }
        } else {
            throw ApiGatewayExceptionFactory.AUTHENTICATION_TOKEN_NOT_FOUND;
        }
        handleNext(request, response);
    }
}

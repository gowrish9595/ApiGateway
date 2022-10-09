package com.gowri.ApiGateway.handler.impl;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gowri.ApiGateway.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.domain.IncomingRequest;
import com.gowri.ApiGateway.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Stream;

@Component
@Slf4j
public class AuthenticationHandler extends BaseHandler {

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public ResponseEntity<Object> handle(IncomingRequest request) {
        if (request.getHttpHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            String token = Stream.ofNullable(request.getHttpHeaders().get(HttpHeaders.AUTHORIZATION))
                    .flatMap(Collection::stream)
                    .findAny()
                    .orElseThrow(() -> ApiGatewayExceptionFactory.AUTHENTICATION_TOKEN_NOT_FOUND);
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
        if (nextHandler != null) {
            return nextHandler.handle(request);
        }
        return null;
    }
}

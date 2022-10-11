package com.gowri.ApiGateway.handler.impl;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.MissingClaimException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.gowri.ApiGateway.AuthenticationConfig;
import com.gowri.ApiGateway.exception.ApiGatewayExceptionFactory;
import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Pattern;

@Component
@Slf4j
public class AuthenticationHandler extends BaseHandler {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationConfig authenticationConfig;

    public AuthenticationHandler(JwtTokenUtil jwtTokenUtil,
                                 AuthenticationConfig authenticationConfig) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationConfig = authenticationConfig;
    }

    public boolean isHandlingNeeded(HttpServletRequest request) {
        List<Pattern> exemptionList = authenticationConfig.getExemptionList();
        return exemptionList
                .stream()
                .noneMatch(pattern -> pattern.matcher(request.getRequestURI()).matches());
    }

    @Override
    public void handle(HttpServletRequest request, CommonResponse response) {
        Enumeration<String> authorizationHeaders = request.getHeaders(HttpHeaders.AUTHORIZATION);
        if(isHandlingNeeded(request)) {
            authenticate(authorizationHeaders);
        }
        handleNext(request, response);
    }

    private void authenticate(Enumeration<String> authorizationHeaders) {
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
    }
}

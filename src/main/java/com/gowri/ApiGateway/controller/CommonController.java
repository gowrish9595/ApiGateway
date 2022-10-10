package com.gowri.ApiGateway.controller;

import com.gowri.ApiGateway.client.RoutingGatewayClient;
import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.handler.impl.AuthenticationHandler;
import com.gowri.ApiGateway.handler.impl.RoutingHandlerImpl;
import com.gowri.ApiGateway.service.ApiGatewayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class CommonController {
    private final ApiGatewayService apiGatewayService;

    public CommonController(ApiGatewayService apiGatewayService) {
        this.apiGatewayService = apiGatewayService;
    }

    @RequestMapping("/v1/**")
    public ResponseEntity<Object> get(HttpServletRequest request)  {
        return apiGatewayService.handle(request);
    }
}

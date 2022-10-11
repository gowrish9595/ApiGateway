package com.gowri.ApiGateway.controller;

import com.gowri.ApiGateway.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    @Autowired
//    private JwtTokenUtil keyUtil;
//
//    @GetMapping("/login")
//    public String login() {
//        return keyUtil.generateToken().orElseThrow(IllegalArgumentException::new);
//    }
}

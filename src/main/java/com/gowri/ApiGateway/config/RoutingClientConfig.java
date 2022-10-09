package com.gowri.ApiGateway.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RoutingClientConfig {

    @Bean
    public RestTemplate client() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .build();
        return  restTemplate;
    }
}

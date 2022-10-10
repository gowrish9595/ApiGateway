package com.gowri.ApiGateway.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RoutingClientConfig {

    @Value("${routing.client.connection.timeoutInSeconds}")
    private int connectionTimeOut;
    @Value("${routing.client.read.timeoutInSeconds}")
    private int readTimeOut;

    @Bean
    public RestTemplate client() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofSeconds(connectionTimeOut))
                .setReadTimeout(Duration.ofSeconds(readTimeOut))
                .build();
    }
}

package com.gowri.ApiGateway.config;

import com.gowri.ApiGateway.RoutingRules;
import com.gowri.ApiGateway.YamlPropertySourceFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@ConfigurationProperties(prefix = "routing")
@PropertySource(value = "classpath:routing_config.yml", factory = YamlPropertySourceFactory.class)
public class RoutingConfigs {
    @Setter private Map<String, String> services;
    @Setter private List<RoutingRules> rules;

    @Getter private Map<Pattern, String> patternToBaseUrlMatch;


    @PostConstruct
    public void routingConfigs() {
        patternToBaseUrlMatch = rules
                .stream()
                .collect(Collectors.toMap(rule -> Pattern.compile(rule.getPattern()),
                        rule -> services.get(rule.getServiceToForward())));
    }


}

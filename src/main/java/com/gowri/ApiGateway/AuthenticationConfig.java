package com.gowri.ApiGateway;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Configuration
@ConfigurationProperties(prefix = "authentication.exemption")
public class AuthenticationConfig {


    @Setter List<String> patterns;
    @Getter private List<Pattern> exemptionList;

    @PostConstruct
    public void exemption() {
        exemptionList = patterns
                .stream()
                .map(Pattern::compile)
                .collect(Collectors.toList());
    }
}

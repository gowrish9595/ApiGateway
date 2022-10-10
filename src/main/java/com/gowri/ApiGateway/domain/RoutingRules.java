package com.gowri.ApiGateway.domain;

import lombok.Data;

@Data
public class RoutingRules {
    private String pattern;
    private String serviceToForward;
}

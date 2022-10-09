package com.gowri.ApiGateway;

import lombok.Data;

@Data
public class RoutingRules {
    private String pattern;
    private String serviceToForward;
}

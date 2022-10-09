package com.gowri.ApiGateway.handler;

import com.gowri.ApiGateway.domain.IncomingRequest;
import org.springframework.http.ResponseEntity;

public interface Handler {
    void handle(IncomingRequest request);
    void setHandler();
}

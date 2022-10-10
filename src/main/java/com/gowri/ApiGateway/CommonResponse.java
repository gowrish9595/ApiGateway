package com.gowri.ApiGateway;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class CommonResponse {
    private ResponseEntity<Object> responseEntity;
}

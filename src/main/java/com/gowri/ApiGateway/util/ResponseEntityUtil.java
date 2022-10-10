package com.gowri.ApiGateway.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class ResponseEntityUtil {

    public static void populateResponse(ResponseEntity<Object> responseEntity,
                                        HttpServletResponse servletResponse) {
        for (Map.Entry<String, List<String>> header : responseEntity.getHeaders().entrySet()) {
            String key = header.getKey();
            for (String value : header.getValue()) {
                servletResponse.addHeader(key, value);
            }
        }

        try {
            String json = new ObjectMapper().writeValueAsString(responseEntity.getBody());
            servletResponse.getWriter().write(json);
            servletResponse.setStatus(responseEntity.getStatusCodeValue());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

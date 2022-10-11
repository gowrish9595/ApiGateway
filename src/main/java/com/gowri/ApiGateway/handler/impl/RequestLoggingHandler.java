package com.gowri.ApiGateway.handler.impl;

import com.gowri.ApiGateway.domain.CommonResponse;
import com.gowri.ApiGateway.repo.Request;
import com.gowri.ApiGateway.repo.RequestRepository;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class RequestLoggingHandler extends BaseHandler {

    ExecutorService executorService = Executors.newFixedThreadPool(10);
    private RequestRepository requestRepository;

    public RequestLoggingHandler(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public void handle(HttpServletRequest request, CommonResponse commonResponse) {
        executorService.submit(() -> saveToDb(request));
        handleNext(request, commonResponse);
    }

    private void saveToDb(HttpServletRequest request) {
        Request requestToSave = new Request();
        requestToSave.setUrl(request.getRequestURI());
        requestRepository.save(requestToSave);
    }
}

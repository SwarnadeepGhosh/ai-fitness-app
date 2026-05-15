package com.sg.fitness.aiservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception exception) {
        String message = "AI-SERVICE:: " + exception.getClass().getSimpleName() + ": " + exception.getMessage();
        log.error("Global-Exception occurred: {}", message);

        return ResponseEntity.internalServerError().body(message);
    }
}

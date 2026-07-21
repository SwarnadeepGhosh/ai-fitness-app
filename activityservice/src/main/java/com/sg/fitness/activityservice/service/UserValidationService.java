package com.sg.fitness.activityservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
public class UserValidationService {
    @Autowired
    private WebClient userServiceWebClient;

//    public boolean validateUserByKeycloakId(String keycloakId) {
//        log.info("Inside validateUserByKeycloakId method, key{}", keycloakId);
//        try {
//            return Boolean.TRUE.equals(userServiceWebClient.get()
//                    .uri("/api/users/{keycloakId}/validateByKeycloakId", keycloakId)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .block());
//        } catch (WebClientResponseException e) {
//            if (e.getStatusCode() == HttpStatus.NOT_FOUND)
//                throw new RuntimeException("User Not Found: " + keycloakId);
//            else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
//                throw new RuntimeException("Invalid Request: " + keycloakId);
//        }
//        return false;
//    }
}

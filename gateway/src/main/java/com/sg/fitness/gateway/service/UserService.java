package com.sg.fitness.gateway.service;


import com.sg.fitness.gateway.dto.RegisterRequest;
import com.sg.fitness.gateway.dto.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

/**
 * Gateway-side helper service for communicating with the downstream user service.
 *
 * <p>This class wraps the HTTP calls used by the gateway to validate whether a
 * user already exists and to register a new user when the JWT identity is not
 * yet known to the user service. In simple terms, it acts as the gateway's
 * bridge to the user-management backend.</p>
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private WebClient userServiceWebClient;

    /**
     * Checks whether a user already exists in the downstream user service.
     *
     * <p>This method sends a request to the user service validation endpoint and
     * expects a boolean result. In simple terms, it answers the question: "Does
     * this user already exist in the system?" If the user does not exist, the
     * downstream service returns 404, which is converted into a readable runtime
     * exception so the gateway can handle the situation clearly.</p>
     *
     * @param userId the user identifier that must be validated
     * @return a reactive boolean that resolves to true when the user exists and
     * false when the user is missing
     */
    public Mono<Boolean> validateUser(String userId) {
        log.info("Calling User Validation API for userId: {}", userId);
        return userServiceWebClient.get()
                .uri("/api/users/{userId}/validate", userId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.NOT_FOUND)
                        return Mono.error(new RuntimeException("User Not Found: " + userId));
                    else if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Invalid Request: " + userId));
                    return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                });
    }

    /**
     * Registers a new user with the downstream user service.
     *
     * <p>This method posts the incoming registration payload to the user service
     * registration endpoint and waits for the created user details in return.
     * In simple terms, it is the gateway-side "create user" call that forwards
     * the registration request to the correct service and then maps the response
     * or error into a meaningful runtime error when something goes wrong.</p>
     *
     * @param registerRequest the user registration payload that should be sent to
     *                        the user service
     * @return a reactive user response returned by the downstream registration API
     */
    public Mono<UserResponse> registerUser(RegisterRequest registerRequest) {
        log.info("Calling User Registration API for email: {}", registerRequest.getEmail());
        return userServiceWebClient.post()
                .uri("/api/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .onErrorResume(WebClientResponseException.class, e -> {
                    if (e.getStatusCode() == HttpStatus.BAD_REQUEST)
                        return Mono.error(new RuntimeException("Bad Request: " + e.getMessage()));
                    else if (e.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR)
                        return Mono.error(new RuntimeException("Internal Server Error: " + e.getMessage()));
                    return Mono.error(new RuntimeException("Unexpected error: " + e.getMessage()));
                });
    }

}

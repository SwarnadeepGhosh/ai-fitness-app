package com.sg.fitness.userservice.controller;

import com.sg.fitness.userservice.dto.RegisterRequest;
import com.sg.fitness.userservice.dto.UserResponse;
import com.sg.fitness.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * Retrieves the profile of a specific user.
     * <p>
     * This API is intended for internal or future use and is not consumed by the UI.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserProfile(@PathVariable String userId) {
        log.info("Inside getUserProfile method at controller layer, userId={}", userId);
        return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    /**
     * Registers a new user.
     * <p>
     * This API is consumed by the UI during user registration.
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Inside register method at controller layer, keycloakId={}", request.getKeycloakId());
        return ResponseEntity.ok(userService.register(request));
    }

    /**
     * Validates whether a user exists for the given Keycloak ID.
     * <p>
     * This API is consumed by the UI to verify user registration.
     */
    @GetMapping("/{userId}/validateByKeycloakId")
    public ResponseEntity<Boolean> validateUser(@PathVariable String userId) {
        log.info("Inside validateUser method at controller layer, userId={}", userId);
        Boolean b = userService.existsByKeycloakId(userId);
        log.info("User Validation by KeycloakId is {}", b);
        return ResponseEntity.ok(b);
    }
}

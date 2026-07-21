package com.sg.fitness.userservice.service;


import com.sg.fitness.userservice.dto.RegisterRequest;
import com.sg.fitness.userservice.dto.UserResponse;
import com.sg.fitness.userservice.model.UserEntity;
import com.sg.fitness.userservice.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public UserResponse register(RegisterRequest request) {

        if (userRepo.existsByEmail(request.getEmail())) {
            UserEntity existingUser = userRepo.findByEmail(request.getEmail());
            return new UserResponse(existingUser);
        }

        UserEntity userEntity = new UserEntity(request);
        UserEntity savedUser = userRepo.save(userEntity);
        return new UserResponse(savedUser);
    }

    public UserResponse getUserProfile(String userId) {
        UserEntity existingUser = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        return new UserResponse(existingUser);
    }

    public Boolean existsByKeycloakId(String keycloakId) {
        log.info("Calling User Validation API for keycloakId: {}", keycloakId);
//        return userRepo.existsById(userId);
        return userRepo.existsByKeycloakId(keycloakId);
    }
}

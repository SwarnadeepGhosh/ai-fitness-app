package com.sg.fitness.gateway.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserResponse {
    private String id;
    private String keycloakId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

//    public UserResponse(UserEntity userEntity) {
//        this.id = userEntity.getId();
//        this.keycloakId = userEntity.getKeycloakId();
//        this.email = userEntity.getEmail();
//        this.password = userEntity.getPassword();
//        this.firstName = userEntity.getFirstName();
//        this.lastName = userEntity.getLastName();
//        this.createdAt = userEntity.getCreatedAt();
//        this.updatedAt = userEntity.getUpdatedAt();
//    }
}

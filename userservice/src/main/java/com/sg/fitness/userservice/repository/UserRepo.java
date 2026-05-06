package com.sg.fitness.userservice.repository;

import com.sg.fitness.userservice.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserEntity, String> {
    boolean existsByEmail(String email);

    Boolean existsByKeycloakId(String userId);

    UserEntity findByEmail(String email);
}
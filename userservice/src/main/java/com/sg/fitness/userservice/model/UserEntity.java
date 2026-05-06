package com.sg.fitness.userservice.model;

import com.sg.fitness.userservice.dto.RegisterRequest;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users", schema = "ai_fitness")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(unique = true, nullable = false)
    private String email;

    private String keycloakId;

    @Column(nullable = false)
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public UserEntity(RegisterRequest request) {
        this.email = request.getEmail();
        this.keycloakId = request.getKeycloakId();
        this.password = request.getPassword();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
    }
}

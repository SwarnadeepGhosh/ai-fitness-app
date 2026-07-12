package com.sg.fitness.gateway.dto;

import com.nimbusds.jwt.JWTClaimsSet;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.ParseException;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must have atleast 6 characters")
    private String password;

    private String keycloakId;

    private String firstName;
    private String lastName;

    public RegisterRequest(JWTClaimsSet claims, String password) throws ParseException {
        this.email = claims.getStringClaim("email");
        this.password = password;
        this.keycloakId = claims.getStringClaim("sub");
        this.firstName = claims.getStringClaim("given_name");
        this.lastName = claims.getStringClaim("family_name");
    }
}
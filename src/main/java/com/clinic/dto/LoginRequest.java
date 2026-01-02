package com.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Schema(description = "Login request payload")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Schema(description = "Username for authentication", example = "admin")
    private String username;

    @Schema(description = "Password for authentication", example = "password123")
    private String password;
}

package com.clinic.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String type;
    private long expiresIn;
    private String username;

    // Builder-style static method
    public static LoginResponse of(String token, long expiresIn, String username) {
        return new LoginResponse(token, "Bearer", expiresIn, username);
    }
}

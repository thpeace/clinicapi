package com.clinic.dto.response;

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
    private String role;

    // Builder-style static method
    public static LoginResponse of(String token, long expiresIn, String username, String role) {
        return new LoginResponse(token, "Bearer", expiresIn, username, role);
    }
}

package com.clinic.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Response DTO for session validation
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionValidationResponse {

    private boolean valid;
    private String username;
    private Long expiresIn;
    private String message;

    public static SessionValidationResponse valid(String username, Long expiresIn) {
        return new SessionValidationResponse(true, username, expiresIn, "Session is valid");
    }

    public static SessionValidationResponse invalid(String message) {
        return new SessionValidationResponse(false, null, null, message);
    }
}

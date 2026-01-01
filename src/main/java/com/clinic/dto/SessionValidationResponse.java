package com.clinic.dto;

/**
 * Response DTO for session validation
 */
public class SessionValidationResponse {

    private boolean valid;
    private String username;
    private Long expiresIn;
    private String message;

    public SessionValidationResponse() {
    }

    public SessionValidationResponse(boolean valid, String username, Long expiresIn, String message) {
        this.valid = valid;
        this.username = username;
        this.expiresIn = expiresIn;
        this.message = message;
    }

    public static SessionValidationResponse valid(String username, Long expiresIn) {
        return new SessionValidationResponse(true, username, expiresIn, "Session is valid");
    }

    public static SessionValidationResponse invalid(String message) {
        return new SessionValidationResponse(false, null, null, message);
    }

    // Getters and Setters
    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

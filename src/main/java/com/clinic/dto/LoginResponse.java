package com.clinic.dto;

public class LoginResponse {

    private String token;
    private String type;
    private long expiresIn;
    private String username;

    // Default constructor
    public LoginResponse() {
    }

    // Constructor with fields
    public LoginResponse(String token, String type, long expiresIn, String username) {
        this.token = token;
        this.type = type;
        this.expiresIn = expiresIn;
        this.username = username;
    }

    // Builder-style static method
    public static LoginResponse of(String token, long expiresIn, String username) {
        return new LoginResponse(token, "Bearer", expiresIn, username);
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

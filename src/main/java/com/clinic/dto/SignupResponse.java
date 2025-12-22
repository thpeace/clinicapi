package com.clinic.dto;

import com.clinic.model.Role;

public class SignupResponse {

    private Long id;
    private String username;
    private Role role;
    private String message;

    // Default constructor
    public SignupResponse() {
    }

    // Constructor with fields
    public SignupResponse(Long id, String username, Role role, String message) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.message = message;
    }

    // Builder-style static method
    public static SignupResponse of(Long id, String username, Role role) {
        return new SignupResponse(id, username, role, "User registered successfully");
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

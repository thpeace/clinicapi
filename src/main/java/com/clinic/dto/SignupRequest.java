package com.clinic.dto;

import com.clinic.model.Role;

public class SignupRequest {

    private String username;
    private String password;
    private Role role;

    // Default constructor
    public SignupRequest() {
    }

    // Constructor with fields
    public SignupRequest(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

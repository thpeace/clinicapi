package com.clinic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.LoginRequest;
import com.clinic.dto.LoginResponse;
import com.clinic.dto.SignupRequest;
import com.clinic.dto.SignupResponse;
import com.clinic.model.User;
import com.clinic.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate user and return JWT token")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up", description = "Register a new user")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        User user = authService.register(
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                signupRequest.getRole());
        SignupResponse signupResponse = SignupResponse.of(
                user.getId(),
                user.getUsername(),
                user.getRole());
        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
    }
}

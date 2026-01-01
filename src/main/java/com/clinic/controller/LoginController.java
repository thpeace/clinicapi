package com.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.LoginRequest;
import com.clinic.dto.LoginResponse;
import com.clinic.dto.SessionValidationResponse;
import com.clinic.dto.SignupRequest;
import com.clinic.dto.SignupResponse;
import com.clinic.model.User;
import com.clinic.security.JwtUtil;
import com.clinic.service.AuthService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication", description = "Authentication endpoints")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public LoginController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Authenticate user and return JWT token")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());

        String ipAddress = getClientIpAddress(request);
        String userAgent = request.getHeader("User-Agent");
        logger.debug("Login request from IP: {}, User-Agent: {}", ipAddress, userAgent);

        LoginResponse loginResponse = authService.login(loginRequest, ipAddress, userAgent);
        logger.info("Login successful for user: {}", loginRequest.getUsername());

        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up", description = "Register a new user")
    public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
        logger.info("Signup attempt for username: {}", signupRequest.getUsername());

        User user = authService.register(
                signupRequest.getUsername(),
                signupRequest.getPassword(),
                signupRequest.getRoleName(),
                signupRequest.getFirstName(),
                signupRequest.getLastName(),
                signupRequest.getEmail(),
                signupRequest.getPhoneNumber(),
                signupRequest.getCountry(),
                signupRequest.getPostalCode(),
                signupRequest.getProvince());
        logger.info("User registered successfully: {}", user.getUsername());

        SignupResponse signupResponse = SignupResponse.of(
                user.getId(),
                user.getUsername(),
                user.getRole(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCountry(),
                user.getPostalCode(),
                user.getProvince());

        return ResponseEntity.status(HttpStatus.CREATED).body(signupResponse);
    }

    @GetMapping("/validate")
    @Operation(summary = "Validate session", description = "Check if the current JWT token is still valid")
    public ResponseEntity<SessionValidationResponse> validateSession(
            @RequestHeader(value = "Authorization", required = false) String authHeader) {
        logger.debug("Session validation request received");

        // Check if Authorization header is present
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.warn("Session validation failed: No valid authorization token provided");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(SessionValidationResponse.invalid("No valid authorization token provided"));
        }

        String token = authHeader.substring(7); // Remove "Bearer " prefix
        logger.debug("Validating token");

        // Validate the token
        if (!jwtUtil.validateToken(token)) {
            logger.warn("Session validation failed: Token is invalid or expired");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(SessionValidationResponse.invalid("Token is invalid or expired"));
        }

        // Token is valid, get username and remaining time
        String username = jwtUtil.getUsernameFromToken(token);
        long expiresIn = jwtUtil.getRemainingExpirySeconds(token);
        logger.info("Session validated successfully for user: {}, expires in: {} seconds", username, expiresIn);

        return ResponseEntity.ok(SessionValidationResponse.valid(username, expiresIn));
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout", description = "Logout current user and invalidate session")
    public ResponseEntity<Void> logout(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            HttpServletRequest request) {
        logger.debug("Logout request received");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.getUsernameFromToken(token);
                String ipAddress = getClientIpAddress(request);
                logger.info("Logging out user: {} from IP: {}", username, ipAddress);
                authService.logout(username, ipAddress);
                logger.info("User logged out successfully: {}", username);
            }
        } else {
            logger.debug("Logout request without valid token");
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Get client IP address, handling proxy headers
     */
    private String getClientIpAddress(HttpServletRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        return request.getRemoteAddr();
    }
}

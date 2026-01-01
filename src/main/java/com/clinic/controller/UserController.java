package com.clinic.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.dto.UserDetailResponse;
import com.clinic.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User", description = "User management endpoints")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get current logged-in user's details
     */
    @GetMapping("/me")
    @Operation(summary = "Get current user", description = "Get details of the currently authenticated user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Not authenticated or invalid token"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDetailResponse> getCurrentUser() {
        // Get authentication from SecurityContext (set by JwtAuthenticationFilter)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("Get current user failed: No authentication in SecurityContext");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String username = authentication.getName();
        logger.info("Fetching user details for: {}", username);

        // Find user by username
        return userService.findByUsername(username)
                .map(user -> {
                    logger.info("User details retrieved successfully for: {}", username);
                    return ResponseEntity.ok(UserDetailResponse.fromUser(user));
                })
                .orElseGet(() -> {
                    logger.warn("User not found: {}", username);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }

    /**
     * Get user details by ID
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Get user details by user ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved successfully"),
            @ApiResponse(responseCode = "401", description = "Not authenticated or invalid token"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserDetailResponse> getUserById(
            @Parameter(description = "User ID") @PathVariable Long id) {

        // Get authentication from SecurityContext (set by JwtAuthenticationFilter)
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            logger.warn("Get user by ID failed: No authentication in SecurityContext");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        logger.info("Fetching user details for ID: {}", id);

        // Find user by ID
        return userService.findById(id)
                .map(user -> {
                    logger.info("User details retrieved successfully for ID: {}", id);
                    return ResponseEntity.ok(UserDetailResponse.fromUser(user));
                })
                .orElseGet(() -> {
                    logger.warn("User not found with ID: {}", id);
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                });
    }
}

package com.clinic.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Error response payload")
public class ErrorResponse {

    @Schema(description = "Error code for programmatic handling", example = "ACCOUNT_LOCKED")
    private String errorCode;

    @Schema(description = "Human-readable error message", example = "Account is locked due to too many failed login attempts")
    private String message;

    @Schema(description = "Additional details about the error", example = "5")
    private String details;

    @Schema(description = "Timestamp of the error", example = "2024-12-31T22:30:00")
    private String timestamp;

    public ErrorResponse() {
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public ErrorResponse(String errorCode, String message) {
        this();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ErrorResponse(String errorCode, String message, String details) {
        this(errorCode, message);
        this.details = details;
    }

    // Static factory methods
    public static ErrorResponse accountLocked(int remainingMinutes) {
        return new ErrorResponse(
                "ACCOUNT_LOCKED",
                "Your account has been locked due to too many failed login attempts.",
                "Please try again in " + remainingMinutes + " minutes or contact support.");
    }

    public static ErrorResponse invalidCredentials(int remainingAttempts) {
        if (remainingAttempts > 0) {
            return new ErrorResponse(
                    "INVALID_CREDENTIALS",
                    "Invalid username or password.",
                    "You have " + remainingAttempts + " attempt(s) remaining before your account is locked.");
        } else {
            return new ErrorResponse(
                    "INVALID_CREDENTIALS",
                    "Invalid username or password.",
                    null);
        }
    }

    public static ErrorResponse userNotFound() {
        return new ErrorResponse(
                "INVALID_CREDENTIALS",
                "Invalid username or password.",
                null);
    }

    // Getters and Setters
    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}

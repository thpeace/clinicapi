package com.clinic.dto.response;

import static com.clinic.constant.ErrorConstants.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Error response payload")
@Data
@NoArgsConstructor
public class ErrorResponse {

    @Schema(description = "Error code for programmatic handling", example = "ACCOUNT_LOCKED")
    private String errorCode;

    @Schema(description = "Human-readable error message", example = "Account is locked due to too many failed login attempts")
    private String message;

    @Schema(description = "Additional details about the error", example = "5")
    private String details;

    @Schema(description = "Timestamp of the error", example = "2024-12-31T22:30:00")
    private String timestamp = java.time.LocalDateTime.now().toString();

    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public ErrorResponse(String errorCode, String message, String details) {
        this(errorCode, message);
        this.details = details;
    }

    // Static factory methods
    public static ErrorResponse accountLocked(int remainingMinutes) {
        return new ErrorResponse(
                ERROR_CODE_ACCOUNT_LOCKED,
                MSG_ACCOUNT_LOCKED,
                "Please try again in " + remainingMinutes + " minutes or contact support.");
    }

    public static ErrorResponse invalidCredentials(int remainingAttempts) {
        if (remainingAttempts > 0) {
            return new ErrorResponse(
                    ERROR_CODE_INVALID_CREDENTIALS,
                    MSG_INVALID_CREDENTIALS,
                    "You have " + remainingAttempts + " attempt(s) remaining before your account is locked.");
        } else {
            return new ErrorResponse(
                    ERROR_CODE_INVALID_CREDENTIALS,
                    MSG_INVALID_CREDENTIALS,
                    null);
        }
    }

    public static ErrorResponse userNotFound() {
        return new ErrorResponse(
                ERROR_CODE_INVALID_CREDENTIALS,
                MSG_INVALID_CREDENTIALS,
                null);
    }
}

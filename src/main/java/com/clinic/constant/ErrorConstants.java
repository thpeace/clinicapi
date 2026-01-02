package com.clinic.constant;

/**
 * Constants for error codes and messages.
 */
public final class ErrorConstants {

    private ErrorConstants() {
        // Prevent instantiation
    }

    // Error codes
    public static final String ERROR_CODE_ACCOUNT_LOCKED = "ACCOUNT_LOCKED";
    public static final String ERROR_CODE_INVALID_CREDENTIALS = "INVALID_CREDENTIALS";

    // Error messages
    public static final String MSG_INVALID_CREDENTIALS = "Invalid username or password.";
    public static final String MSG_ACCOUNT_LOCKED = "Your account has been locked due to too many failed login attempts.";
}

package com.clinic.exception;

public class InvalidCredentialsException extends RuntimeException {

    private final int remainingAttempts;

    public InvalidCredentialsException(String message, int remainingAttempts) {
        super(message);
        this.remainingAttempts = remainingAttempts;
    }

    public int getRemainingAttempts() {
        return remainingAttempts;
    }
}

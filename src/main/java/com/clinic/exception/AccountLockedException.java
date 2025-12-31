package com.clinic.exception;

public class AccountLockedException extends RuntimeException {

    private final int remainingMinutes;

    public AccountLockedException(String message, int remainingMinutes) {
        super(message);
        this.remainingMinutes = remainingMinutes;
    }

    public int getRemainingMinutes() {
        return remainingMinutes;
    }
}

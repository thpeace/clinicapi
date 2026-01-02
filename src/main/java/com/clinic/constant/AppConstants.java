package com.clinic.constant;

/**
 * Application-wide constants.
 */
public final class AppConstants {

    private AppConstants() {
        // Prevent instantiation
    }

    // API Paths
    public static final String API_BASE_PATH = "/api";

    // Pagination defaults
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;

    // Date formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}

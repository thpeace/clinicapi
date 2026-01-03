package com.clinic.constant;

/**
 * Configuration-related constants.
 */
public final class ConfigConstants {

    private ConfigConstants() {
        // Prevent instantiation
    }

    // Security Constants
    public static final String SECURITY_SCHEME_NAME = "bearerAuth";
    public static final String SECURITY_SCHEME_TYPE = "bearer";
    public static final String SECURITY_BEARER_FORMAT = "JWT";

    // CORS Configuration

    // Public Endpoints (no authentication required)
    public static final String AUTH_PATH_PATTERN = "/api/auth/**";
    public static final String SWAGGER_UI_PATH_PATTERN = "/swagger-ui/**";
    public static final String SWAGGER_UI_HTML_PATH = "/swagger-ui.html";
    public static final String API_DOCS_PATH_PATTERN = "/v3/api-docs/**";
    public static final String SWAGGER_RESOURCES_PATH_PATTERN = "/swagger-resources/**";

    // Swagger/OpenAPI Info
    public static final String API_TITLE = "Clinic API";
    public static final String API_VERSION = "1.0";
    public static final String API_DESCRIPTION = "API documentation for Clinic system";

    // Default Role Names
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_DOCTOR = "DOCTOR";
    public static final String ROLE_NURSE = "NURSE";
    public static final String ROLE_RECEPTIONIST = "RECEPTIONIST";
    public static final String ROLE_PHARMACIST = "PHARMACIST";
    public static final String ROLE_LAB_TECHNICIAN = "LAB_TECHNICIAN";
    public static final String ROLE_USER = "USER";

    // Role Descriptions
    public static final String ROLE_ADMIN_DESC = "Administrator with full access";
    public static final String ROLE_DOCTOR_DESC = "Medical doctor";
    public static final String ROLE_NURSE_DESC = "Nursing staff";
    public static final String ROLE_RECEPTIONIST_DESC = "Front desk receptionist";
    public static final String ROLE_PHARMACIST_DESC = "Pharmacy staff";
    public static final String ROLE_LAB_TECHNICIAN_DESC = "Laboratory technician";
    public static final String ROLE_USER_DESC = "Standard user";
}

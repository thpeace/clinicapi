package com.clinic.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "User registration request payload")
@Data
@NoArgsConstructor
public class SignupRequest {

    @Schema(description = "Username for the new account", example = "john_doe")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "Password for the new account (min 8 characters recommended)", example = "SecurePass123!")
    @NotBlank(message = "Password is required")
    private String password;

    @Schema(description = "Role name for the user (e.g., USER, ADMIN, DOCTOR)", example = "USER")
    @NotBlank(message = "Role is required")
    private String roleName;

    @Schema(description = "User's first name", example = "John")
    @NotBlank(message = "First name is required")
    private String firstName;

    @Schema(description = "User's last name", example = "Doe")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @Schema(description = "User's email address", example = "john.doe@example.com")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @Schema(description = "User's phone number (10-15 digits, optional + prefix)", example = "+1234567890")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Phone number should be valid")
    private String phoneNumber;

    @Schema(description = "Country of residence", example = "United States")
    private String country;

    @Schema(description = "Postal/ZIP code", example = "90210")
    private String postalCode;

    @Schema(description = "State or province", example = "California")
    private String province;
}

package com.clinic.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LoginRequest DTO.
 * 
 * Best Practices Applied:
 * - @Nested classes for logical grouping
 * - @DisplayName for readable test names
 * - Test fixtures as constants
 * - Edge case testing (null, empty)
 */
@DisplayName("LoginRequest DTO Tests")
class LoginRequestTest {

    private LoginRequest sut;

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testpass123";

    @BeforeEach
    void setUp() {
        sut = new LoginRequest();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor should create empty LoginRequest")
        void defaultConstructor_ShouldCreateEmptyRequest() {
            // Assert
            assertAll("All fields should be null",
                    () -> assertNull(sut.getUsername()),
                    () -> assertNull(sut.getPassword()));
        }

        @Test
        @DisplayName("Parameterized constructor should set username and password")
        void parameterizedConstructor_ShouldSetFields() {
            // Act
            LoginRequest request = new LoginRequest(TEST_USERNAME, TEST_PASSWORD);

            // Assert
            assertAll("Fields should be set correctly",
                    () -> assertEquals(TEST_USERNAME, request.getUsername()),
                    () -> assertEquals(TEST_PASSWORD, request.getPassword()));
        }
    }

    @Nested
    @DisplayName("Username Field Tests")
    class UsernameTests {

        @Test
        @DisplayName("setUsername and getUsername should work correctly")
        void setAndGetUsername_ShouldWorkCorrectly() {
            // Act
            sut.setUsername(TEST_USERNAME);

            // Assert
            assertEquals(TEST_USERNAME, sut.getUsername());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setUsername should accept null and empty values")
        void setUsername_ShouldAcceptNullAndEmpty(String username) {
            // Act
            sut.setUsername(username);

            // Assert
            assertEquals(username, sut.getUsername());
        }
    }

    @Nested
    @DisplayName("Password Field Tests")
    class PasswordTests {

        @Test
        @DisplayName("setPassword and getPassword should work correctly")
        void setAndGetPassword_ShouldWorkCorrectly() {
            // Act
            sut.setPassword(TEST_PASSWORD);

            // Assert
            assertEquals(TEST_PASSWORD, sut.getPassword());
        }

        @ParameterizedTest
        @NullAndEmptySource
        @DisplayName("setPassword should accept null and empty values")
        void setPassword_ShouldAcceptNullAndEmpty(String password) {
            // Act
            sut.setPassword(password);

            // Assert
            assertEquals(password, sut.getPassword());
        }
    }
}

package com.clinic.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.clinic.dto.response.LoginResponse;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for LoginResponse DTO.
 * 
 * Best Practices Applied:
 * - @Nested classes for logical grouping
 * - Testing static factory methods
 * - Boundary value testing for numeric fields
 */
@DisplayName("LoginResponse DTO Tests")
class LoginResponseTest {

    private LoginResponse sut;

    private static final String TEST_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.test";
    private static final String TEST_TYPE = "Bearer";
    private static final long TEST_EXPIRES_IN = 3600L;
    private static final String TEST_USERNAME = "testuser";

    @BeforeEach
    void setUp() {
        sut = new LoginResponse();
    }

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor should create empty LoginResponse")
        void defaultConstructor_ShouldCreateEmptyResponse() {
            // Assert
            assertAll("Default values",
                    () -> assertNull(sut.getToken()),
                    () -> assertNull(sut.getType()),
                    () -> assertEquals(0L, sut.getExpiresIn()),
                    () -> assertNull(sut.getUsername()));
        }

        @Test
        @DisplayName("Parameterized constructor should set all fields")
        void parameterizedConstructor_ShouldSetAllFields() {
            // Act
            LoginResponse response = new LoginResponse(
                    TEST_TOKEN, TEST_TYPE, TEST_EXPIRES_IN, TEST_USERNAME);

            // Assert
            assertAll("All fields should be set",
                    () -> assertEquals(TEST_TOKEN, response.getToken()),
                    () -> assertEquals(TEST_TYPE, response.getType()),
                    () -> assertEquals(TEST_EXPIRES_IN, response.getExpiresIn()),
                    () -> assertEquals(TEST_USERNAME, response.getUsername()));
        }
    }

    @Nested
    @DisplayName("Static Factory Method Tests")
    class StaticFactoryMethodTests {

        @Test
        @DisplayName("of() should create LoginResponse with Bearer type")
        void of_ShouldCreateResponseWithBearerType() {
            // Act
            LoginResponse response = LoginResponse.of(TEST_TOKEN, TEST_EXPIRES_IN, TEST_USERNAME);

            // Assert
            assertAll("Factory method should set correct values",
                    () -> assertEquals(TEST_TOKEN, response.getToken()),
                    () -> assertEquals("Bearer", response.getType(), "Type should be 'Bearer'"),
                    () -> assertEquals(TEST_EXPIRES_IN, response.getExpiresIn()),
                    () -> assertEquals(TEST_USERNAME, response.getUsername()));
        }

        @Test
        @DisplayName("of() should accept zero expiration")
        void of_ShouldAcceptZeroExpiration() {
            // Act
            LoginResponse response = LoginResponse.of(TEST_TOKEN, 0L, TEST_USERNAME);

            // Assert
            assertEquals(0L, response.getExpiresIn());
        }
    }

    @Nested
    @DisplayName("Setter Tests")
    class SetterTests {

        @Test
        @DisplayName("setToken should update token value")
        void setToken_ShouldUpdateValue() {
            // Act
            sut.setToken(TEST_TOKEN);

            // Assert
            assertEquals(TEST_TOKEN, sut.getToken());
        }

        @Test
        @DisplayName("setType should update type value")
        void setType_ShouldUpdateValue() {
            // Act
            sut.setType(TEST_TYPE);

            // Assert
            assertEquals(TEST_TYPE, sut.getType());
        }

        @Test
        @DisplayName("setExpiresIn should accept boundary values")
        void setExpiresIn_ShouldAcceptBoundaryValues() {
            // Test max value
            sut.setExpiresIn(Long.MAX_VALUE);
            assertEquals(Long.MAX_VALUE, sut.getExpiresIn());

            // Test zero
            sut.setExpiresIn(0L);
            assertEquals(0L, sut.getExpiresIn());
        }

        @Test
        @DisplayName("setUsername should update username value")
        void setUsername_ShouldUpdateValue() {
            // Act
            sut.setUsername(TEST_USERNAME);

            // Assert
            assertEquals(TEST_USERNAME, sut.getUsername());
        }
    }
}

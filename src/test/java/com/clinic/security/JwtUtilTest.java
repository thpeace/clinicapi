package com.clinic.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for JwtUtil.
 * 
 * Best Practices Applied:
 * - Testing token generation and validation
 * - Testing edge cases (invalid tokens, expired tokens)
 * - Using realistic test data
 */
@DisplayName("JwtUtil Tests")
class JwtUtilTest {

    private JwtUtil sut;

    // Secret must be at least 256 bits (32 bytes) for HS256
    private static final String TEST_SECRET = "ThisIsAVerySecretKeyForHmacSHA256Algorithm!";
    private static final long TEST_EXPIRATION = 3600000L; // 1 hour in milliseconds
    private static final String TEST_USERNAME = "testuser";

    @BeforeEach
    void setUp() {
        sut = new JwtUtil(TEST_SECRET, TEST_EXPIRATION);
    }

    @Nested
    @DisplayName("Token Generation Tests")
    class TokenGenerationTests {

        @Test
        @DisplayName("generateToken should create a non-null token")
        void generateToken_ShouldCreateNonNullToken() {
            // Act
            String token = sut.generateToken(TEST_USERNAME);

            // Assert
            assertNotNull(token);
            assertFalse(token.isEmpty());
        }

        @Test
        @DisplayName("generateToken should create token with three parts")
        void generateToken_ShouldCreateTokenWithThreeParts() {
            // Act
            String token = sut.generateToken(TEST_USERNAME);

            // Assert - JWT has format: header.payload.signature
            String[] parts = token.split("\\.");
            assertEquals(3, parts.length, "JWT should have 3 parts separated by dots");
        }

        @Test
        @DisplayName("generateToken should create different tokens for different users")
        void generateToken_ShouldCreateDifferentTokensForDifferentUsers() {
            // Act
            String token1 = sut.generateToken("user1");
            String token2 = sut.generateToken("user2");

            // Assert
            assertNotEquals(token1, token2);
        }
    }

    @Nested
    @DisplayName("Username Extraction Tests")
    class UsernameExtractionTests {

        @Test
        @DisplayName("getUsernameFromToken should extract correct username")
        void getUsernameFromToken_ShouldExtractCorrectUsername() {
            // Arrange
            String token = sut.generateToken(TEST_USERNAME);

            // Act
            String extractedUsername = sut.getUsernameFromToken(token);

            // Assert
            assertEquals(TEST_USERNAME, extractedUsername);
        }

        @Test
        @DisplayName("getUsernameFromToken should work for various usernames")
        void getUsernameFromToken_ShouldWorkForVariousUsernames() {
            // Test with different username formats
            String[] usernames = { "admin", "user@email.com", "user_123", "User-Name" };

            for (String username : usernames) {
                String token = sut.generateToken(username);
                assertEquals(username, sut.getUsernameFromToken(token),
                        "Should extract username: " + username);
            }
        }
    }

    @Nested
    @DisplayName("Token Validation Tests")
    class TokenValidationTests {

        @Test
        @DisplayName("validateToken should return true for valid token")
        void validateToken_WithValidToken_ShouldReturnTrue() {
            // Arrange
            String token = sut.generateToken(TEST_USERNAME);

            // Act
            boolean isValid = sut.validateToken(token);

            // Assert
            assertTrue(isValid);
        }

        @Test
        @DisplayName("validateToken should return false for malformed token")
        void validateToken_WithMalformedToken_ShouldReturnFalse() {
            // Act
            boolean isValid = sut.validateToken("malformed.token.here");

            // Assert
            assertFalse(isValid);
        }

        @Test
        @DisplayName("validateToken should return false for empty token")
        void validateToken_WithEmptyToken_ShouldReturnFalse() {
            // Act
            boolean isValid = sut.validateToken("");

            // Assert
            assertFalse(isValid);
        }

        @Test
        @DisplayName("validateToken should return false for null-ish token")
        void validateToken_WithNullContent_ShouldReturnFalse() {
            // Act
            boolean isValid = sut.validateToken("   ");

            // Assert
            assertFalse(isValid);
        }

        @Test
        @DisplayName("validateToken should return false for token with wrong signature")
        void validateToken_WithWrongSignature_ShouldReturnFalse() {
            // Arrange - Create token with different secret
            JwtUtil otherJwtUtil = new JwtUtil(
                    "AnotherVerySecretKeyForHmacSHA256Algorithm!", TEST_EXPIRATION);
            String tokenFromOtherSecret = otherJwtUtil.generateToken(TEST_USERNAME);

            // Act
            boolean isValid = sut.validateToken(tokenFromOtherSecret);

            // Assert
            assertFalse(isValid);
        }

        @Test
        @DisplayName("validateToken should return false for expired token")
        void validateToken_WithExpiredToken_ShouldReturnFalse() {
            // Arrange - Create JwtUtil with 1ms expiration (essentially expired)
            JwtUtil shortExpirationJwtUtil = new JwtUtil(TEST_SECRET, 1L);
            String token = shortExpirationJwtUtil.generateToken(TEST_USERNAME);

            // Wait for token to expire
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Act
            boolean isValid = shortExpirationJwtUtil.validateToken(token);

            // Assert
            assertFalse(isValid);
        }
    }

    @Nested
    @DisplayName("Expiration Tests")
    class ExpirationTests {

        @Test
        @DisplayName("getExpirationInSeconds should return correct value")
        void getExpirationInSeconds_ShouldReturnCorrectValue() {
            // Act
            long expirationSeconds = sut.getExpirationInSeconds();

            // Assert
            assertEquals(TEST_EXPIRATION / 1000, expirationSeconds);
        }

        @Test
        @DisplayName("getExpirationInSeconds should handle different expiration values")
        void getExpirationInSeconds_ShouldHandleDifferentValues() {
            // Test with 1 hour = 3600000ms = 3600s
            assertEquals(3600, sut.getExpirationInSeconds());

            // Test with different value
            JwtUtil customJwtUtil = new JwtUtil(TEST_SECRET, 7200000L); // 2 hours
            assertEquals(7200, customJwtUtil.getExpirationInSeconds());
        }
    }
}

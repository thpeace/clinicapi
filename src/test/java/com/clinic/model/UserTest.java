package com.clinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the User entity class.
 * 
 * Best Practices Applied:
 * 1. @Nested classes for logical grouping
 * 2. @DisplayName for readable test names
 * 3. @BeforeEach for test setup (SUT creation)
 * 4. @ParameterizedTest for data-driven tests
 * 5. AAA pattern (Arrange-Act-Assert)
 * 6. One assertion concept per test
 * 7. Descriptive test method names
 * 8. Testing edge cases (null, empty, special characters)
 */
@DisplayName("User Entity Tests")
class UserTest {

    // SUT (System Under Test)
    private User sut;

    // Test fixtures (constants)
    private static final Long DEFAULT_ID = 1L;
    private static final String DEFAULT_USERNAME = "testuser";
    private static final String DEFAULT_PASSWORD = "securePassword123";
    private static final RoleEntity DEFAULT_ROLE = new RoleEntity("USER", "Default user role");

    @BeforeEach
    void setUp() {
        sut = new User();
    }

    // =========================================================================
    // BEST PRACTICE 1: Group related tests using @Nested
    // =========================================================================

    @Nested
    @DisplayName("Constructor Tests")
    class ConstructorTests {

        @Test
        @DisplayName("Default constructor should create User with all null fields")
        void defaultConstructor_ShouldCreateUserWithNullFields() {
            // Act - already done in @BeforeEach

            // Assert
            assertAll("All fields should be null",
                    () -> assertNull(sut.getId()),
                    () -> assertNull(sut.getUsername()),
                    () -> assertNull(sut.getPassword()),
                    () -> assertNull(sut.getRole()));
        }

        @Test
        @DisplayName("Parameterized constructor should set username, password, and role")
        void parameterizedConstructor_ShouldSetFields() {
            // Arrange & Act
            User user = new User(DEFAULT_USERNAME, DEFAULT_PASSWORD, DEFAULT_ROLE);

            // Assert
            assertAll("Constructor should set all provided fields",
                    () -> assertNull(user.getId(), "ID should remain null (DB generated)"),
                    () -> assertEquals(DEFAULT_USERNAME, user.getUsername()),
                    () -> assertEquals(DEFAULT_PASSWORD, user.getPassword()),
                    () -> assertEquals(DEFAULT_ROLE, user.getRole()));
        }

        @Test
        @DisplayName("Parameterized constructor should accept null values")
        void parameterizedConstructor_ShouldAcceptNullValues() {
            // Act
            User user = new User(null, null, null);

            // Assert
            assertAll("Constructor should accept null values",
                    () -> assertNull(user.getUsername()),
                    () -> assertNull(user.getPassword()),
                    () -> assertNull(user.getRole()));
        }
    }

    // =========================================================================
    // BEST PRACTICE 2: Parameterized tests for multiple inputs
    // =========================================================================

    @Nested
    @DisplayName("Username Field Tests")
    class UsernameTests {

        @Test
        @DisplayName("setUsername and getUsername should work correctly")
        void setAndGetUsername_ShouldWorkCorrectly() {
            // Act
            sut.setUsername(DEFAULT_USERNAME);

            // Assert
            assertEquals(DEFAULT_USERNAME, sut.getUsername());
        }

        @ParameterizedTest(name = "Username ''{0}'' should be accepted")
        @ValueSource(strings = { "admin", "user_123", "UPPERCASE", "mixed.case", "a" })
        @DisplayName("setUsername should accept various valid usernames")
        void setUsername_ShouldAcceptValidUsernames(String username) {
            // Act
            sut.setUsername(username);

            // Assert
            assertEquals(username, sut.getUsername());
        }

        @ParameterizedTest(name = "Username should accept null/empty: ''{0}''")
        @NullAndEmptySource
        @DisplayName("setUsername should accept null and empty values")
        void setUsername_ShouldAcceptNullAndEmpty(String username) {
            // Act
            sut.setUsername(username);

            // Assert
            assertEquals(username, sut.getUsername());
        }

        @Test
        @DisplayName("setUsername should handle special characters")
        void setUsername_ShouldHandleSpecialCharacters() {
            // Arrange
            String specialUsername = "user@email.com!#$%";

            // Act
            sut.setUsername(specialUsername);

            // Assert
            assertEquals(specialUsername, sut.getUsername());
        }
    }

    @Nested
    @DisplayName("Password Field Tests")
    class PasswordTests {

        @Test
        @DisplayName("setPassword and getPassword should work correctly")
        void setAndGetPassword_ShouldWorkCorrectly() {
            // Act
            sut.setPassword(DEFAULT_PASSWORD);

            // Assert
            assertEquals(DEFAULT_PASSWORD, sut.getPassword());
        }

        @Test
        @DisplayName("setPassword should handle hashed password format")
        void setPassword_ShouldHandleHashedFormat() {
            // Arrange - BCrypt-like hash
            String hashedPassword = "$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZRGdjGj/n3.Rl5v7Xc6w";

            // Act
            sut.setPassword(hashedPassword);

            // Assert
            assertEquals(hashedPassword, sut.getPassword());
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

    @Nested
    @DisplayName("Role Field Tests")
    class RoleTests {

        @Test
        @DisplayName("setRole should accept RoleEntity")
        void setRole_ShouldAcceptValidRoles() {
            // Arrange
            RoleEntity role = new RoleEntity("ADMIN", "Administrator role");

            // Act
            sut.setRole(role);

            // Assert
            assertEquals(role, sut.getRole());
        }

        @Test
        @DisplayName("setRole should allow role update")
        void setRole_ShouldAllowRoleUpdate() {
            // Arrange
            RoleEntity userRole = new RoleEntity("USER", "User role");
            RoleEntity adminRole = new RoleEntity("ADMIN", "Admin role");
            sut.setRole(userRole);

            // Act
            sut.setRole(adminRole);

            // Assert
            assertEquals(adminRole, sut.getRole());
        }
    }

    @Nested
    @DisplayName("ID Field Tests")
    class IdTests {

        @Test
        @DisplayName("setId and getId should work correctly")
        void setAndGetId_ShouldWorkCorrectly() {
            // Act
            sut.setId(DEFAULT_ID);

            // Assert
            assertEquals(DEFAULT_ID, sut.getId());
        }

        @ParameterizedTest(name = "ID {0} should be accepted")
        @ValueSource(longs = { 0L, 1L, Long.MAX_VALUE })
        @DisplayName("setId should accept various Long values")
        void setId_ShouldAcceptVariousValues(Long id) {
            // Act
            sut.setId(id);

            // Assert
            assertEquals(id, sut.getId());
        }

        @Test
        @DisplayName("setId should accept null value")
        void setId_ShouldAcceptNull() {
            // Arrange
            sut.setId(DEFAULT_ID);

            // Act
            sut.setId(null);

            // Assert
            assertNull(sut.getId());
        }
    }

    // =========================================================================
    // BEST PRACTICE 3: Integration/Scenario tests
    // =========================================================================

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("Fully populated User should return correct values")
        void fullyPopulatedUser_ShouldReturnCorrectValues() {
            // Arrange
            sut.setId(DEFAULT_ID);
            sut.setUsername(DEFAULT_USERNAME);
            sut.setPassword(DEFAULT_PASSWORD);
            sut.setRole(DEFAULT_ROLE);

            // Assert
            assertAll("All fields should be correctly set",
                    () -> assertEquals(DEFAULT_ID, sut.getId()),
                    () -> assertEquals(DEFAULT_USERNAME, sut.getUsername()),
                    () -> assertEquals(DEFAULT_PASSWORD, sut.getPassword()),
                    () -> assertEquals(DEFAULT_ROLE, sut.getRole()));
        }

        @Test
        @DisplayName("User created via constructor should allow field updates")
        void userCreatedViaConstructor_ShouldAllowFieldUpdates() {
            // Arrange
            RoleEntity userRole = new RoleEntity("USER", "User role");
            RoleEntity adminRole = new RoleEntity("ADMIN", "Admin role");
            User user = new User("original", "pass", userRole);

            // Act
            user.setUsername("updated");
            user.setPassword("newpass");
            user.setRole(adminRole);

            // Assert
            assertAll("Fields should be updated",
                    () -> assertEquals("updated", user.getUsername()),
                    () -> assertEquals("newpass", user.getPassword()),
                    () -> assertEquals(adminRole, user.getRole()));
        }
    }
}

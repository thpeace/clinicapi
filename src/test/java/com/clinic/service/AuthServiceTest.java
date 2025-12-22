package com.clinic.service;

import com.clinic.dto.LoginRequest;
import com.clinic.dto.LoginResponse;
import com.clinic.model.Role;
import com.clinic.model.User;
import com.clinic.repository.UserRepository;
import com.clinic.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit tests for AuthService.
 * 
 * Best Practices Applied:
 * - @Mock for dependencies (mocking collaborators)
 * - @InjectMocks for SUT (System Under Test)
 * - @Captor for argument verification
 * - Verify mock interactions
 * - Test both success and failure cases
 * - Behavior verification with verify()
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("AuthService Tests")
class AuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService sut;

    @Captor
    private ArgumentCaptor<User> userCaptor;

    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_PASSWORD = "testpass123";
    private static final Role TEST_ROLE = Role.USER;
    private static final String TEST_TOKEN = "jwt.token.here";
    private static final String ENCODED_PASSWORD = "$2a$10$encoded";
    private static final long EXPIRATION_SECONDS = 3600L;

    @Nested
    @DisplayName("Login Tests")
    class LoginTests {

        private LoginRequest loginRequest;

        @BeforeEach
        void setUp() {
            loginRequest = new LoginRequest(TEST_USERNAME, TEST_PASSWORD);
        }

        @Test
        @DisplayName("login should return LoginResponse when credentials are valid")
        void login_WithValidCredentials_ShouldReturnLoginResponse() {
            // Arrange
            when(jwtUtil.generateToken(TEST_USERNAME)).thenReturn(TEST_TOKEN);
            when(jwtUtil.getExpirationInSeconds()).thenReturn(EXPIRATION_SECONDS);

            // Act
            LoginResponse response = sut.login(loginRequest);

            // Assert
            assertNotNull(response);
            assertAll("LoginResponse should have correct values",
                    () -> assertEquals(TEST_TOKEN, response.getToken()),
                    () -> assertEquals("Bearer", response.getType()),
                    () -> assertEquals(EXPIRATION_SECONDS, response.getExpiresIn()),
                    () -> assertEquals(TEST_USERNAME, response.getUsername()));
        }

        @Test
        @DisplayName("login should call authenticationManager.authenticate")
        void login_ShouldCallAuthenticationManager() {
            // Arrange
            when(jwtUtil.generateToken(anyString())).thenReturn(TEST_TOKEN);
            when(jwtUtil.getExpirationInSeconds()).thenReturn(EXPIRATION_SECONDS);

            // Act
            sut.login(loginRequest);

            // Assert - Verify interaction
            verify(authenticationManager).authenticate(
                    any(UsernamePasswordAuthenticationToken.class));
        }

        @Test
        @DisplayName("login should throw BadCredentialsException when authentication fails")
        void login_WithInvalidCredentials_ShouldThrowException() {
            // Arrange
            when(authenticationManager.authenticate(any()))
                    .thenThrow(new BadCredentialsException("Bad credentials"));

            // Act & Assert
            BadCredentialsException exception = assertThrows(
                    BadCredentialsException.class,
                    () -> sut.login(loginRequest));

            assertEquals("Invalid username or password", exception.getMessage());
        }

        @Test
        @DisplayName("login should generate token with correct username")
        void login_ShouldGenerateTokenWithCorrectUsername() {
            // Arrange
            when(jwtUtil.generateToken(TEST_USERNAME)).thenReturn(TEST_TOKEN);
            when(jwtUtil.getExpirationInSeconds()).thenReturn(EXPIRATION_SECONDS);

            // Act
            sut.login(loginRequest);

            // Assert
            verify(jwtUtil).generateToken(TEST_USERNAME);
        }
    }

    @Nested
    @DisplayName("Register Tests")
    class RegisterTests {

        @Test
        @DisplayName("register should save user with encoded password")
        void register_ShouldSaveUserWithEncodedPassword() {
            // Arrange
            when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(false);
            when(passwordEncoder.encode(TEST_PASSWORD)).thenReturn(ENCODED_PASSWORD);
            when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
                User user = invocation.getArgument(0);
                user.setId(1L);
                return user;
            });

            // Act
            User result = sut.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE);

            // Assert
            verify(userRepository).save(userCaptor.capture());
            User capturedUser = userCaptor.getValue();

            assertAll("User should be saved with correct values",
                    () -> assertEquals(TEST_USERNAME, capturedUser.getUsername()),
                    () -> assertEquals(ENCODED_PASSWORD, capturedUser.getPassword()),
                    () -> assertEquals(TEST_ROLE, capturedUser.getRole()));
        }

        @Test
        @DisplayName("register should throw exception when username exists")
        void register_WithExistingUsername_ShouldThrowException() {
            // Arrange
            when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(true);

            // Act & Assert
            RuntimeException exception = assertThrows(
                    RuntimeException.class,
                    () -> sut.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE));

            assertEquals("Username already exists", exception.getMessage());
        }

        @Test
        @DisplayName("register should not call save when username exists")
        void register_WithExistingUsername_ShouldNotSave() {
            // Arrange
            when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(true);

            // Act
            try {
                sut.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE);
            } catch (RuntimeException ignored) {
            }

            // Assert
            verify(userRepository, never()).save(any());
        }

        @Test
        @DisplayName("register should check username existence first")
        void register_ShouldCheckUsernameFirst() {
            // Arrange
            when(userRepository.existsByUsername(TEST_USERNAME)).thenReturn(false);
            when(passwordEncoder.encode(anyString())).thenReturn(ENCODED_PASSWORD);
            when(userRepository.save(any())).thenReturn(new User());

            // Act
            sut.register(TEST_USERNAME, TEST_PASSWORD, TEST_ROLE);

            // Assert - verify order of operations
            var inOrder = inOrder(userRepository, passwordEncoder);
            inOrder.verify(userRepository).existsByUsername(TEST_USERNAME);
            inOrder.verify(passwordEncoder).encode(TEST_PASSWORD);
            inOrder.verify(userRepository).save(any());
        }
    }
}

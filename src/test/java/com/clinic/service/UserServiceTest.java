package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.model.User;
import com.clinic.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserService Tests")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService sut;

    private static final Long TEST_ID = 1L;
    private static final String TEST_USERNAME = "testuser";
    private static final String TEST_EMAIL = "test@example.com";

    @BeforeEach
    void setUp() {
        sut = new UserService(userRepository);
    }

    @Nested
    @DisplayName("Find Tests")
    class FindTests {

        @Test
        @DisplayName("findById should return user when found")
        void findById_ShouldReturnUser_WhenFound() {
            User user = new User();
            user.setId(TEST_ID);
            when(userRepository.findById(TEST_ID)).thenReturn(Optional.of(user));

            Optional<User> result = sut.findById(TEST_ID);

            assertTrue(result.isPresent());
            assertEquals(TEST_ID, result.get().getId());
            verify(userRepository).findById(TEST_ID);
        }

        @Test
        @DisplayName("findByUsername should return user when found")
        void findByUsername_ShouldReturnUser_WhenFound() {
            User user = new User();
            user.setUsername(TEST_USERNAME);
            when(userRepository.findByUsername(TEST_USERNAME)).thenReturn(Optional.of(user));

            Optional<User> result = sut.findByUsername(TEST_USERNAME);

            assertTrue(result.isPresent());
            assertEquals(TEST_USERNAME, result.get().getUsername());
            verify(userRepository).findByUsername(TEST_USERNAME);
        }

        @Test
        @DisplayName("findByEmail should return user when found")
        void findByEmail_ShouldReturnUser_WhenFound() {
            User user = new User();
            user.setEmail(TEST_EMAIL);
            when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(Optional.of(user));

            Optional<User> result = sut.findByEmail(TEST_EMAIL);

            assertTrue(result.isPresent());
            assertEquals(TEST_EMAIL, result.get().getEmail());
            verify(userRepository).findByEmail(TEST_EMAIL);
        }

        @Test
        @DisplayName("findAll should return list of users")
        void findAll_ShouldReturnListOfUsers() {
            List<User> users = Arrays.asList(new User(), new User());
            when(userRepository.findAll()).thenReturn(users);

            List<User> result = sut.findAll();

            assertNotNull(result);
            assertEquals(2, result.size());
            verify(userRepository).findAll();
        }
    }
}

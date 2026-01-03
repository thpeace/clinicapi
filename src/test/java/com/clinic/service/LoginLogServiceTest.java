package com.clinic.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.clinic.model.LoginLog;
import com.clinic.repository.LoginLogRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("LoginLogService Tests")
class LoginLogServiceTest {

    @Mock
    private LoginLogRepository loginLogRepository;

    private LoginLogService sut;
    private static final String TEST_USER = "user";

    @BeforeEach
    void setUp() {
        sut = new LoginLogService(loginLogRepository);
    }

    @Test
    @DisplayName("logSuccessfulLogin should save log")
    void logSuccessfulLogin_ShouldSaveLog() {
        when(loginLogRepository.save(any(LoginLog.class))).thenAnswer(i -> i.getArgument(0));

        LoginLog log = sut.logSuccessfulLogin(TEST_USER, 1L, "127.0.0.1", "Agent");

        assertNotNull(log);
        assertEquals(TEST_USER, log.getUsername());
        verify(loginLogRepository).save(any(LoginLog.class));
    }

    @Test
    @DisplayName("logFailedLogin should save log")
    void logFailedLogin_ShouldSaveLog() {
        when(loginLogRepository.save(any(LoginLog.class))).thenAnswer(i -> i.getArgument(0));

        LoginLog log = sut.logFailedLogin(TEST_USER, 1L, "127.0.0.1", "Agent", "Reason");

        assertNotNull(log);
        assertEquals("Reason", log.getFailureReason());
    }

    @Test
    @DisplayName("getLoginHistory should return list")
    void getLoginHistory_ShouldReturnList() {
        when(loginLogRepository.findByUsernameOrderByLoginTimeDesc(TEST_USER))
                .thenReturn(Collections.emptyList());

        assertEquals(0, sut.getLoginHistory(TEST_USER).size());
    }

    @Test
    @DisplayName("getRecentLoginHistory should return list")
    void getRecentLoginHistory_ShouldReturnList() {
        when(loginLogRepository.findTop10ByUsernameOrderByLoginTimeDesc(TEST_USER))
                .thenReturn(Collections.emptyList());

        assertEquals(0, sut.getRecentLoginHistory(TEST_USER).size());
    }

    @Test
    @DisplayName("countRecentFailedAttempts should return count")
    void countRecentFailedAttempts_ShouldReturnCount() {
        when(loginLogRepository.countByUsernameAndSuccessAndLoginTimeAfter(
                eq(TEST_USER), eq(false), any(LocalDateTime.class)))
                .thenReturn(5L);

        long count = sut.countRecentFailedAttempts(TEST_USER, LocalDateTime.now());
        assertEquals(5L, count);
    }

    @Test
    @DisplayName("logLogout should save log")
    void logLogout_ShouldSaveLog() {
        sut.logLogout(TEST_USER, 1L, "127.0.0.1");
        verify(loginLogRepository).save(any(LoginLog.class));
    }
}

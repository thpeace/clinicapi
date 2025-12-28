package com.clinic.service;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.clinic.model.LoginLog;
import com.clinic.repository.LoginLogRepository;

@Service
public class LoginLogService {

    private static final Logger logger = LoggerFactory.getLogger(LoginLogService.class);

    private final LoginLogRepository loginLogRepository;

    public LoginLogService(LoginLogRepository loginLogRepository) {
        this.loginLogRepository = loginLogRepository;
    }

    /**
     * Log a successful login attempt
     */
    public LoginLog logSuccessfulLogin(String username, Long userId, String ipAddress, String userAgent) {
        LoginLog log = LoginLog.success(username, userId, ipAddress, userAgent);
        LoginLog saved = loginLogRepository.save(log);
        logger.info("Successful login for user '{}' from IP: {}", username, ipAddress);
        return saved;
    }

    /**
     * Log a failed login attempt
     */
    public LoginLog logFailedLogin(String username, Long userId, String ipAddress, String userAgent, String reason) {
        LoginLog log = LoginLog.failure(username, userId, ipAddress, userAgent, reason);
        LoginLog saved = loginLogRepository.save(log);
        logger.warn("Failed login attempt for user '{}' from IP: {} - Reason: {}", username, ipAddress, reason);
        return saved;
    }

    /**
     * Get login history for a user
     */
    public List<LoginLog> getLoginHistory(String username) {
        return loginLogRepository.findByUsernameOrderByLoginTimeDesc(username);
    }

    /**
     * Get recent login history (last 10) for a user
     */
    public List<LoginLog> getRecentLoginHistory(String username) {
        return loginLogRepository.findTop10ByUsernameOrderByLoginTimeDesc(username);
    }

    /**
     * Count failed login attempts since a given time
     */
    public long countRecentFailedAttempts(String username, LocalDateTime since) {
        return loginLogRepository.countByUsernameAndSuccessAndLoginTimeAfter(username, false, since);
    }
}

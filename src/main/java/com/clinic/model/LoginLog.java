package com.clinic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "login_logs")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String username;

    @Column
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column(nullable = false)
    private Boolean success;

    @Column(length = 50)
    private String ipAddress;

    @Column(length = 255)
    private String userAgent;

    @Column(length = 255)
    private String failureReason;

    // Default constructor
    public LoginLog() {
    }

    // Constructor for quick creation
    public LoginLog(String username, Long userId, Boolean success, String ipAddress, String userAgent) {
        this.username = username;
        this.userId = userId;
        this.loginTime = LocalDateTime.now();
        this.success = success;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
    }

    // Static factory methods
    public static LoginLog success(String username, Long userId, String ipAddress, String userAgent) {
        return new LoginLog(username, userId, true, ipAddress, userAgent);
    }

    public static LoginLog failure(String username, Long userId, String ipAddress, String userAgent, String reason) {
        LoginLog log = new LoginLog(username, userId, false, ipAddress, userAgent);
        log.setFailureReason(reason);
        return log;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}

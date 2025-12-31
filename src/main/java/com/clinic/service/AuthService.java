package com.clinic.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.dto.LoginRequest;
import com.clinic.dto.LoginResponse;
import com.clinic.model.Role;
import com.clinic.model.User;
import com.clinic.repository.UserRepository;
import com.clinic.security.JwtUtil;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LoginLogService loginLogService;
    private final com.clinic.repository.RoleRepository roleRepository;

    @Value("${security.lockout.max-attempts:5}")
    private int maxFailedAttempts;

    @Value("${security.lockout.duration-minutes:30}")
    private int lockDurationMinutes;

    public AuthService(AuthenticationManager authenticationManager,
            JwtUtil jwtUtil,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            LoginLogService loginLogService,
            com.clinic.repository.RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.loginLogService = loginLogService;
        this.roleRepository = roleRepository;
    }

    /**
     * Authenticate user and return JWT token
     */
    public LoginResponse login(LoginRequest loginRequest, String ipAddress, String userAgent) {
        String username = loginRequest.getUsername();
        Optional<User> userOpt = userRepository.findByUsername(username);

        // Check if account is locked
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (isAccountLocked(user)) {
                loginLogService.logFailedLogin(username, user.getId(), ipAddress, userAgent, "Account is locked");
                throw new LockedException(
                        "Account is locked. Please try again after " + lockDurationMinutes + " minutes.");
            }
        }

        try {
            // Authenticate using Spring Security
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            loginRequest.getPassword()));

            // Authentication successful - reset failed attempts
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                resetFailedAttempts(user);
                loginLogService.logSuccessfulLogin(username, user.getId(), ipAddress, userAgent);
            }

            // Generate JWT token
            String token = jwtUtil.generateToken(username);

            logger.info("User '{}' logged in successfully from IP: {}", username, ipAddress);

            return LoginResponse.of(
                    token,
                    jwtUtil.getExpirationInSeconds(),
                    username);

        } catch (AuthenticationException e) {
            // Authentication failed - increment failed attempts
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                handleFailedLogin(user, ipAddress, userAgent);
            } else {
                // Log attempt for non-existent user
                loginLogService.logFailedLogin(username, null, ipAddress, userAgent, "User not found");
            }

            throw new BadCredentialsException("Invalid username or password");
        }
    }

    /**
     * Overloaded login method for backward compatibility
     */
    public LoginResponse login(LoginRequest loginRequest) {
        return login(loginRequest, "unknown", "unknown");
    }

    /**
     * Check if account is locked
     */
    private boolean isAccountLocked(User user) {
        if (Boolean.TRUE.equals(user.getAccountLocked())) {
            // Check if lock duration has expired
            if (user.getLockTime() != null) {
                LocalDateTime unlockTime = user.getLockTime().plusMinutes(lockDurationMinutes);
                if (LocalDateTime.now().isAfter(unlockTime)) {
                    // Lock has expired, unlock the account
                    unlockAccount(user);
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Handle failed login attempt
     */
    private void handleFailedLogin(User user, String ipAddress, String userAgent) {
        int attempts = user.getFailedLoginAttempts() == null ? 0 : user.getFailedLoginAttempts();
        attempts++;
        user.setFailedLoginAttempts(attempts);

        if (attempts >= maxFailedAttempts) {
            lockAccount(user);
            loginLogService.logFailedLogin(user.getUsername(), user.getId(), ipAddress, userAgent,
                    "Account locked after " + maxFailedAttempts + " failed attempts");
            logger.warn("Account '{}' locked after {} failed login attempts", user.getUsername(), maxFailedAttempts);
        } else {
            userRepository.save(user);
            loginLogService.logFailedLogin(user.getUsername(), user.getId(), ipAddress, userAgent,
                    "Invalid password - Attempt " + attempts + "/" + maxFailedAttempts);
            logger.warn("Failed login attempt {} of {} for user '{}'", attempts, maxFailedAttempts, user.getUsername());
        }
    }

    /**
     * Lock user account
     */
    private void lockAccount(User user) {
        user.setAccountLocked(true);
        user.setLockTime(LocalDateTime.now());
        userRepository.save(user);
    }

    /**
     * Unlock user account
     */
    private void unlockAccount(User user) {
        user.setAccountLocked(false);
        user.setLockTime(null);
        user.setFailedLoginAttempts(0);
        userRepository.save(user);
        logger.info("Account '{}' has been unlocked (lock duration expired)", user.getUsername());
    }

    /**
     * Reset failed login attempts after successful login
     */
    private void resetFailedAttempts(User user) {
        if (user.getFailedLoginAttempts() != null && user.getFailedLoginAttempts() > 0) {
            user.setFailedLoginAttempts(0);
            user.setAccountLocked(false);
            user.setLockTime(null);
            userRepository.save(user);
        }
    }

    /**
     * Logout user - clear session date and log the action
     */
    public void logout(String username, String ipAddress) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setSessionDate(null);
            userRepository.save(user);
            loginLogService.logLogout(username, user.getId(), ipAddress);
            logger.info("User '{}' logged out from IP: {}", username, ipAddress);
        }
    }

    /**
     * Manually unlock a user account (admin function)
     */
    public void unlockUserAccount(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            unlockAccount(userOpt.get());
            logger.info("Account '{}' has been manually unlocked", username);
        }
    }

    /**
     * Register a new user
     */
    public User register(String username, String password, String roleName,
            String firstName, String lastName, String email, String phoneNumber,
            String country, String postalCode, String province) {

        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username already exists");
        }

        if (email != null && !email.isEmpty() && userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // Lookup role by name
        com.clinic.entity.RoleEntity role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setCountry(country);
        user.setPostalCode(postalCode);
        user.setProvince(province);

        return userRepository.save(user);
    }
}

package com.clinic.config;

import com.clinic.model.Role;
import com.clinic.model.User;
import com.clinic.repository.RoleRepository;
import com.clinic.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(UserDataInitializer.class);

    @Value("${app.default-user.password}")
    private String defaultPassword;

    @Bean
    @Order(2) // Ensure this runs after roles are likely initialized
    public CommandLineRunner initUsers(UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            logger.info("Initializing default users...");

            for (Role roleEnum : Role.values()) {
                String roleName = roleEnum.name(); // e.g., ADMIN
                String username = roleName.toLowerCase(); // e.g., admin

                if (!userRepository.existsByUsername(username)) {
                    // Try to find the role, log warning if not found
                    roleRepository.findByName(roleName).ifPresentOrElse(roleEntity -> {
                        User user = new User();
                        user.setUsername(username);
                        user.setPassword(passwordEncoder.encode(defaultPassword));
                        user.setRole(roleEntity);
                        // Optional fields, but good for completeness
                        user.setFirstName(roleName.substring(0, 1).toUpperCase() + roleName.substring(1).toLowerCase());
                        user.setLastName("User");
                        user.setEnabled(true);
                        user.setAccountLocked(false);
                        user.setFailedLoginAttempts(0);

                        userRepository.save(user);
                        logger.info("Created user: {} with role: {}", username, roleName);
                    }, () -> logger.warn("Role not found: {}. Skipping user creation for this role.", roleName));
                } else {
                    logger.debug("User already exists: {}", username);
                }
            }
            logger.info("User initialization completed.");
        };
    }
}

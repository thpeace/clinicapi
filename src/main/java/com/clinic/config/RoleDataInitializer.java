package com.clinic.config;

import static com.clinic.constant.ConfigConstants.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinic.model.RoleEntity;
import com.clinic.repository.RoleRepository;

@Configuration
public class RoleDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RoleDataInitializer.class);

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            logger.info("Initializing roles...");

            createRoleIfNotExists(roleRepository, ROLE_ADMIN, ROLE_ADMIN_DESC);
            createRoleIfNotExists(roleRepository, ROLE_DOCTOR, ROLE_DOCTOR_DESC);
            createRoleIfNotExists(roleRepository, ROLE_NURSE, ROLE_NURSE_DESC);
            createRoleIfNotExists(roleRepository, ROLE_RECEPTIONIST, ROLE_RECEPTIONIST_DESC);
            createRoleIfNotExists(roleRepository, ROLE_PHARMACIST, ROLE_PHARMACIST_DESC);
            createRoleIfNotExists(roleRepository, ROLE_LAB_TECHNICIAN, ROLE_LAB_TECHNICIAN_DESC);
            createRoleIfNotExists(roleRepository, ROLE_USER, ROLE_USER_DESC);

            logger.info("Roles initialization completed. Total roles: {}", roleRepository.count());
        };
    }

    private void createRoleIfNotExists(RoleRepository roleRepository, String name, String description) {
        if (!roleRepository.existsByName(name)) {
            RoleEntity role = new RoleEntity(name, description);
            roleRepository.save(role);
            logger.info("Created role: {}", name);
        } else {
            logger.debug("Role already exists: {}", name);
        }
    }
}

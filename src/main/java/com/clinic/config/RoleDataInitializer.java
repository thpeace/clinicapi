package com.clinic.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.clinic.entity.RoleEntity;
import com.clinic.repository.RoleRepository;

@Configuration
public class RoleDataInitializer {

    private static final Logger logger = LoggerFactory.getLogger(RoleDataInitializer.class);

    @Bean
    public CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            logger.info("Initializing roles...");

            createRoleIfNotExists(roleRepository, "ADMIN", "Administrator with full access");
            createRoleIfNotExists(roleRepository, "DOCTOR", "Medical doctor");
            createRoleIfNotExists(roleRepository, "NURSE", "Nursing staff");
            createRoleIfNotExists(roleRepository, "RECEPTIONIST", "Front desk receptionist");
            createRoleIfNotExists(roleRepository, "PHARMACIST", "Pharmacy staff");
            createRoleIfNotExists(roleRepository, "LAB_TECHNICIAN", "Laboratory technician");
            createRoleIfNotExists(roleRepository, "USER", "Standard user");

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

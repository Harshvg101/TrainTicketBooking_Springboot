package com.srts.config;

import com.srts.entity.Admin;
import com.srts.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminPreloadConfig {

    @Bean
    public CommandLineRunner preloadAdmin(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if an admin account already exists
            if (adminRepository.findByUsername("admin") == null) {
                // Create an admin account with username "admin" and password "adminPassword"
                Admin admin = new Admin("admin", passwordEncoder.encode("adminPassword"));
                adminRepository.save(admin);
                System.out.println("Admin account created: username=admin, password=adminPassword");
            } else {
                System.out.println("Admin account already exists.");
            }
        };
    }
}

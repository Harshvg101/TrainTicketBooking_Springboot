package com.srts.security;

import com.srts.repository.AdminRepository;
import com.srts.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public SecurityConfig(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(adminRepository, userRepository);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (not recommended for production)
                .authorizeHttpRequests(auth -> auth
                        // Public Endpoints
                        .requestMatchers("/auth/login", "/auth/signup").permitAll()

                        // Admin-Specific Endpoints
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")

                        // User-Specific Endpoints
                        .requestMatchers("/booking/**", "/contact/**", "/news/**", "/api/passengers/**").hasAuthority("USER")

                        // Require authentication for any other request
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // Updated to use withDefaults()

        return http.build();
    }
}

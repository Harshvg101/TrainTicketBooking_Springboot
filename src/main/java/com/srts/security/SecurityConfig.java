package com.srts.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import com.srts.repository.AdminRepository;
import com.srts.repository.UserRepository;

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
    /*
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                 .authenticationProvider(daoAuthenticationProvider())
                 .build();
    }
     */


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }





//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())  //  Disable CSRF
//                .cors(cors -> cors.configure(http))  //  Enable CORS
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll() //  Temporarily allow all requests
//                )
//                .httpBasic(httpBasic -> httpBasic.disable()); //  Disable Basic Auth
//
//        return http.build();
//    }





    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configure(http))
                .authorizeHttpRequests(auth -> auth
                        // Public Endpoints
                        .requestMatchers("/", "/auth/login", "/auth/signup", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/test/ping", "/admin/login").permitAll()
                        // User-Specific Endpoints
//                        .requestMatchers("/booking/**", "/api/passengers/**").hasRole("USER")
                        // Admin-Specific Endpoints
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**", "/contact/reply/**", "/contact/all", "/news/add", "/news/delete/**", "/api/train-stations/upload").hasRole("ADMIN")

                        //Common Endpoints
                        .requestMatchers("/booking/**", "/api/passengers/**").hasAnyRole("USER", "ADMIN")


                        // Public Endpoints
//                        .requestMatchers("/", "/auth/login", "/auth/signup", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/test/ping", "/admin/login").permitAll()
//                        // Admin-Specific Endpoints
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/contact/reply/**").hasRole("USER")
//
//                        // User-Specific Endpoints
//                        .requestMatchers("/booking/**", "/contact/**", "/news/**", "/api/passengers/**").hasAnyRole("USER", "ADMIN")

                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> httpBasic.disable());;

        // Add JWT filter before the UsernamePasswordAuthenticationFilter
        http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
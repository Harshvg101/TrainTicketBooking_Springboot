package com.srts.controller;

import com.srts.entity.User;
import org.springframework.http.HttpStatus;
import com.srts.repository.UserRepository;
import com.srts.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already registered.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER"); // Ensure regular users cannot register as admin
        userRepository.save(user);

        String jwt = jwtUtil.generateToken(user.getEmail());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", "USER");
        response.put("message", "User registered successfully.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
        }

//        try {
//            // This call may throw an exception if authentication fails
//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//        } catch (Exception ex) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + ex.getMessage());
//        }

//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        String jwt = jwtUtil.generateToken(email);

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("email", user.getEmail());
        response.put("role", user.getRole());
        response.put("firstName", user.getFirstName());
        response.put("lastName", user.getLastName());

        return ResponseEntity.ok(response);
    }

    // Fetch user details for homepage
    @GetMapping("/user/home")
    public ResponseEntity<?> getUserHomePage(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractUsername(token.substring(7)); // Remove "Bearer " prefix

        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Map<String, Object> response = new HashMap<>();
            response.put("firstName", user.get().getFirstName());
            response.put("lastName", user.get().getLastName());
            response.put("email", user.get().getEmail());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(404).body("User not found");
    }
}

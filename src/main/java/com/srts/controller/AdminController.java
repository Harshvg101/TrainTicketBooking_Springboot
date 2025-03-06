package com.srts.controller;

import com.srts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.srts.security.JwtUtil;
import java.util.Map;
import java.util.HashMap;



import java.util.Optional;
//@RestController
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private AdminService adminService;
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//        return adminService.authenticate(username, password)
//                ? ResponseEntity.ok("Login successful!")
//                : ResponseEntity.status(401).body("Invalid credentials!");
//    }
//}

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        boolean isAuthenticated = adminService.authenticate(username, password);

        if (!isAuthenticated) {
            return ResponseEntity.status(401).body("Invalid credentials!");
        }

        String jwt = jwtUtil.generateToken(username);
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("role", "ADMIN");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/dashboard")
    public ResponseEntity<?> getAdminDashboard(@RequestHeader("Authorization") String token) {
        String username = jwtUtil.extractUsername(token.substring(7));

        if (!adminService.isAdmin(username)) {
            return ResponseEntity.status(403).body("Access denied.");
        }

        return ResponseEntity.ok("Welcome to Admin Dashboard");
    }
}

package com.srts.controller;

import com.srts.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        return adminService.authenticate(username, password)
                ? ResponseEntity.ok("Login successful!")
                : ResponseEntity.status(401).body("Invalid credentials!");
    }
}

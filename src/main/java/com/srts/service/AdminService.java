package com.srts.service;

import com.srts.entity.Admin;
import com.srts.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String username, String password) {
        Admin admin = adminRepository.findByUsername(username);
        return admin != null && passwordEncoder.matches(password, admin.getPassword());
    }

    public boolean isAdmin(String username) {
        return adminRepository.findByUsername(username) != null;
    }
}

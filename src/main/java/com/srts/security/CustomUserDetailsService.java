package com.srts.security;

import com.srts.entity.Admin;
import com.srts.entity.User;
import com.srts.repository.AdminRepository;
import com.srts.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public CustomUserDetailsService(AdminRepository adminRepository, UserRepository userRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //  Fetch Admin by username
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) { // Check if admin exists
            return new CustomUserDetails(admin.getUsername(), admin.getPassword(), "ADMIN");
        }

        //  Fetch User by email
        User user = userRepository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username: " + username)
        );
        System.out.println("User found: " + user.getEmail());
        return new CustomUserDetails(user.getEmail(), user.getPassword(), "USER");
    }
}

package com.gym.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gym.entity.User;
import com.gym.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // CHANGE PASSWORD METHOD
    public ResponseEntity<?> changePassword(String email, String oldPassword, String newPassword) {

        // 1. Find user using email (your User table contains email field)
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // 2. Validate old password
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.badRequest().body("Old password is incorrect");
        }

        // 3. Validate new password length
        if (newPassword.length() < 6) {
            return ResponseEntity.badRequest().body("New password must be at least 6 characters long");
        }

        // 4. Update the encrypted password
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        return ResponseEntity.ok("Password updated successfully");
    }

}

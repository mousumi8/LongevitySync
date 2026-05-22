package com.example.longevitysync.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtils {

    // Create a BCryptPasswordEncoder instance
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Method to hash a password using BCrypt
    public static String hashPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // Method to verify a password using BCrypt
    public static boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}

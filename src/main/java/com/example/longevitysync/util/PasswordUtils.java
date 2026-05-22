package com.example.longevitysync.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordUtils {

    /**
     * Hash a password using SHA-256 algorithm
     * @param password the plain text password to hash
     * @return the hashed password encoded in Base64
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    /**
     * Verify a plain text password against a hashed password
     * @param password the plain text password to verify
     * @param hashedPassword the hashed password to compare against
     * @return true if the password matches the hash, false otherwise
     */
    public static boolean verifyPassword(String password, String hashedPassword) {
        String hash = hashPassword(password);
        return hash.equals(hashedPassword);
    }
}


package com.example.longevitysync.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String email;
    private String passwordHash;
    private LocalDate dob;
    private LocalDateTime registrationDate = LocalDateTime.now();
    private boolean isAdmin = false;
    private boolean isEditor = false;

    // Getters and setters omitted for brevity
}

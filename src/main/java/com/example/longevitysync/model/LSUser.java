package com.example.longevitysync.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "FIRST_NAME", nullable = false, length = 50)
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false, length = 50)
    private String lastName;

    @Column(name = "DOB", nullable = false)
    private String dob;  // If you want to use `Date`, replace with `private Date dob;` and add a date formatter.

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;  // Store the BCrypt hashed password

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;


    @ManyToOne
    @JoinColumn(name = "ROLE_ID", nullable = false)
    private LSRole role;
}

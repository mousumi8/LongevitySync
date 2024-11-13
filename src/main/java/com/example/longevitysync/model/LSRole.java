package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_ROLE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name", nullable = false, length = 50)
    private String name;
}

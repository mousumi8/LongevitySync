package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_CONTENT_TYPE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSContentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TYPE", nullable = false)
    private String type;

    @Column(name = "NAME", nullable = false)
    private String name;
}

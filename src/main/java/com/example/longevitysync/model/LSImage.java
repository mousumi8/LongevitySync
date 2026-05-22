package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_IMAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "IMAGE_TYPE", nullable = false)
    private String imageType;

    @Column(name = "IMAGE_LOCATION", nullable = false)
    private String imageLocation;
}

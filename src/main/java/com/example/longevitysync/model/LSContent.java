package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_CONTENT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSContent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CONTENT_TYPE_ID", nullable = false)
    private LSContentType contentType;

    @Column(name = "CONTENT_LOCATION", nullable = false)
    private String contentLocation;

    @ManyToOne
    @JoinColumn(name = "IMAGE_ID", nullable = false)
    private LSImage image;
}

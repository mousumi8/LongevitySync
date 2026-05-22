package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "LS_ALLERGY")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSAllergy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(name = "ALLERGY_TYPE", nullable = false)
    private String allergyType;

    @Column(name = "ALLERGY_NAME", nullable = false)
    private String allergyName;
}

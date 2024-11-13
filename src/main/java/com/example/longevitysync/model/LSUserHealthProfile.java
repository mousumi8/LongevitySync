package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "LS_USER_HEALTH_PROFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSUserHealthProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(name = "BODY_TYPE")
    private String bodyType;

    @Column(name = "FOOD_HABIT")
    private String foodHabit;

    @Column(name = "PHYSICAL_ACTIVITY")
    private String physicalActivity;

    @ElementCollection
    @CollectionTable(name = "USER_ALLERGY_IDS", joinColumns = @JoinColumn(name = "USER_ID"))
    @Column(name = "ALLERGY_ID")
    private List<Long> allergyIds;
}

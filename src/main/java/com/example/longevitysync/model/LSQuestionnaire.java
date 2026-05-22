package com.example.longevitysync.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LS_QUESTIONARE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LSQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uniqueID;

    @Column(name = "QUESTION_TEXT", nullable = false)
    private String questionText;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "UPDATED_DATE")
    private LocalDateTime updatedDate;

    @Column(name = "UPDATED_BY")
    private String updatedBy;

    @Column(name = "DELETED_DATE")
    private LocalDateTime deletedDate;

    @Column(name = "DELETED_BY")
    private String deletedBy;
}

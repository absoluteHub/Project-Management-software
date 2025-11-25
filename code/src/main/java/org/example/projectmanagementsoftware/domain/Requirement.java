package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.projectmanagementsoftware.domain.enums.RequirementStatus;
import org.example.projectmanagementsoftware.domain.enums.RequirementType;

@Entity
@Table(name = "requirements")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private RequirementType requirementType;

    @Enumerated(EnumType.STRING)
    private RequirementStatus status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;
}

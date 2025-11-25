package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.projectmanagementsoftware.domain.enums.IterationType;

import java.time.LocalDate;

@Entity
@Table(name = "iterations")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Iteration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private IterationType iterationType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;
}

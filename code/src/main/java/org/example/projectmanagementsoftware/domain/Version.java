package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "versions")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Version {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versionNumber;
    private String description;
    private LocalDate releaseDate;
    private String executablePath;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;
}


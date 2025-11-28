package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Version parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Version> children = new ArrayList<> ();
}


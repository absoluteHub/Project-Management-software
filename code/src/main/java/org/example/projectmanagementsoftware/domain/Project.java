package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private LocalDate deadline;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectTeam> team;

    @OneToMany(mappedBy = "project")
    private List<Requirement> requirements;

    @OneToMany(mappedBy = "project")
    private List<Iteration> iterations;

    @OneToMany(mappedBy = "project")
    private List<Version> versions;

}

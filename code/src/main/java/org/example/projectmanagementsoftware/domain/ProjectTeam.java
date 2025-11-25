package org.example.projectmanagementsoftware.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_team")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProjectTeam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}

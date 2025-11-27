package org.example.projectmanagementsoftware.pattern.facade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.ProjectTeam;
import org.example.projectmanagementsoftware.domain.User;
import org.example.projectmanagementsoftware.dto.ProjectTeamDto;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class TeamDto {

    private Project project;
    private List<ProjectTeam> team;
    private List<User> users;
    private ProjectTeamDto form;
}

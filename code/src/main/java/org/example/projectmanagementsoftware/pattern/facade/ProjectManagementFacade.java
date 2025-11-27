package org.example.projectmanagementsoftware.pattern.facade;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.ProjectTeam;
import org.example.projectmanagementsoftware.domain.User;
import org.example.projectmanagementsoftware.domain.enums.TaskStatus;
import org.example.projectmanagementsoftware.dto.ProjectTeamDto;
import org.example.projectmanagementsoftware.pattern.facade.dto.KanbanDto;
import org.example.projectmanagementsoftware.pattern.facade.dto.TeamDto;
import org.example.projectmanagementsoftware.service.*;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProjectManagementFacade {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final ProjectTeamService projectTeamService;
    private final UserService userService;

    public KanbanDto getKanban(Long projectId) {
        return new KanbanDto(
                projectService.getProjectById(projectId),
                taskService.getByProjectAndStatus(projectId, TaskStatus.TODO),
                taskService.getByProjectAndStatus(projectId, TaskStatus.IN_PROGRESS),
                taskService.getByProjectAndStatus(projectId, TaskStatus.DONE)
        );
    }

    public TeamDto getTeam(Long projectId) {

        Project project = projectService.getProjectById(projectId);
        List<ProjectTeam> team = projectTeamService.getTeam(projectId);
        List<User> users = userService.getAll();

        ProjectTeamDto form = new ProjectTeamDto ();
        form.setProjectId(projectId);

        return new TeamDto(project, team, users, form);
    }
}

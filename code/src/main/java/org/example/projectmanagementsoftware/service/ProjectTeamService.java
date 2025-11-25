package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.ProjectTeam;
import org.example.projectmanagementsoftware.domain.User;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.ProjectTeamRepository;
import org.example.projectmanagementsoftware.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectTeamService {

    private final ProjectTeamRepository projectTeamRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    public List<ProjectTeam> getTeam(Long projectId) {
        return projectTeamRepository.findByProjectId(projectId);
    }

    public void addMember(Long projectId, Long userId) {

        if (projectTeamRepository.existsByProjectIdAndUserId(projectId, userId)) {
            return;
        }

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not found: " + projectId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found: " + userId));

        ProjectTeam pt = new ProjectTeam();
        pt.setProject(project);
        pt.setUser(user);

        projectTeamRepository.save(pt);
    }

    public void removeMember(Long id) {
        projectTeamRepository.deleteById(id);
    }
}

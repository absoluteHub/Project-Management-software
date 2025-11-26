package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.pattern.observer.enums.ProjectEventType;
import org.example.projectmanagementsoftware.pattern.observer.interfaces.ObservableProject;
import org.example.projectmanagementsoftware.pattern.observer.interfaces.ProjectObserver;
import org.example.projectmanagementsoftware.pattern.observer.utils.ProjectUpdateChecker;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements ObservableProject {

    private final ProjectRepository projectRepository;
    private final List<ProjectObserver> observers;

    @Override
    public void registerObserver(ProjectObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ProjectObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(Project project, ProjectEventType event) {
        if (event == null) return;
        observers.forEach(o -> o.onProjectUpdated(project, event));
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found: " + id));
    }

    public Project update(Long id, Project updated) {
        Project existing = getProjectById(id);

        ProjectEventType event = ProjectUpdateChecker.detectChange(existing, updated);
        ProjectUpdateChecker.apply(existing, updated);

        Project saved = projectRepository.save(existing);

        notifyObservers(saved, event);

        return saved;
    }

    public Project save(Project project) {
        Project saved = projectRepository.save(project);
        return saved;
    }

    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new NotFoundException("Project not found: " + id);
        }
        projectRepository.deleteById(id);
    }
}

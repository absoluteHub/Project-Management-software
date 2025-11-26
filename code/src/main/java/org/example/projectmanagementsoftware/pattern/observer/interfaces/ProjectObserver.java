package org.example.projectmanagementsoftware.pattern.observer.interfaces;

import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.pattern.observer.enums.ProjectEventType;

public interface ProjectObserver {

    void onProjectUpdated(Project project, ProjectEventType eventType);
}


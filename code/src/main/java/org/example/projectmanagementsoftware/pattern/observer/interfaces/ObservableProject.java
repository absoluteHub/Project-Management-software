package org.example.projectmanagementsoftware.pattern.observer.interfaces;

import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.pattern.observer.enums.ProjectEventType;

public interface ObservableProject {

    void registerObserver(ProjectObserver observer);

    void removeObserver(ProjectObserver observer);

    void notifyObservers(Project project, ProjectEventType eventType);
}

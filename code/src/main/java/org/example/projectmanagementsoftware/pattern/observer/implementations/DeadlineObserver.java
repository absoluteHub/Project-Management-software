package org.example.projectmanagementsoftware.pattern.observer.implementations;

import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.pattern.observer.enums.ProjectEventType;
import org.example.projectmanagementsoftware.pattern.observer.interfaces.ProjectObserver;
import org.springframework.stereotype.Component;

@Component
public class DeadlineObserver implements ProjectObserver {

    @Override
    public void onProjectUpdated(Project project, ProjectEventType eventType) {
        if (eventType == ProjectEventType.DEADLINE_CHANGED) {
            System.out.println("Дедлайн змінено: " + project.getDeadline());
        }
    }
}

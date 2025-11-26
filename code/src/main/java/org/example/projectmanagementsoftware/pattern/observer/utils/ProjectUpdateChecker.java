package org.example.projectmanagementsoftware.pattern.observer.utils;

import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.pattern.observer.enums.ProjectEventType;

public class ProjectUpdateChecker {

    public static ProjectEventType detectChange(Project oldP, Project newP) {
        if (oldP.getDeadline() != null
                && newP.getDeadline() != null
                && !oldP.getDeadline().equals(newP.getDeadline())) {
            return ProjectEventType.DEADLINE_CHANGED;
        }
        return null;
    }

    public static void apply(Project oldP, Project newP) {
        oldP.setDeadline(newP.getDeadline());
    }
}

package org.example.projectmanagementsoftware.pattern.taskChain.config;

import org.example.projectmanagementsoftware.pattern.taskChain.handler.DeadlineValidationHandler;
import org.example.projectmanagementsoftware.pattern.taskChain.handler.NameValidationHandler;
import org.example.projectmanagementsoftware.pattern.taskChain.handler.PriorityChangeHandler;
import org.example.projectmanagementsoftware.pattern.taskChain.handler.TaskHandler;
import org.springframework.stereotype.Component;

@Component
public class TaskValidationChain {

    private final TaskHandler first;

    public TaskValidationChain() {

        TaskHandler name = new NameValidationHandler ();
        TaskHandler deadline = new DeadlineValidationHandler ();
        TaskHandler status = new PriorityChangeHandler ();

        name.setNext(deadline);
        deadline.setNext(status);

        this.first = name;
    }

    public TaskHandler getChain() {
        return first;
    }
}


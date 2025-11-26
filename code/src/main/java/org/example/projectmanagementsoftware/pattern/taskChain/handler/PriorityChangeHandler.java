package org.example.projectmanagementsoftware.pattern.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.domain.enums.Priority;
import org.example.projectmanagementsoftware.dto.TaskDto;
import org.example.projectmanagementsoftware.exception.TaskValidationException;

public class PriorityChangeHandler implements TaskHandler {

    private TaskHandler next;

    @Override
    public void setNext(TaskHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TaskDto dto, Task existingTask) {

        if (existingTask != null) {
            Priority oldP = existingTask.getPriority();
            Priority newP = dto.getPriority();

            if (oldP != null && newP != null && newP.ordinal() < oldP.ordinal()) {
                throw new TaskValidationException (
                        "Зниження пріоритету заборонено: " + oldP + " → " + newP
                );
            }
        }

        if (next != null) next.handle(dto, existingTask);
    }
}

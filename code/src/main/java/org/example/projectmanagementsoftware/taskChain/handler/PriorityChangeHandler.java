package org.example.projectmanagementsoftware.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.domain.enums.Priority;
import org.example.projectmanagementsoftware.dto.TaskDto;

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

            if (newP.ordinal() < oldP.ordinal()) {
                throw new RuntimeException("Зниження пріоритету заборонено: "
                        + oldP + " → " + newP);
            }
        }

        if (next != null) next.handle(dto, existingTask);
    }
}

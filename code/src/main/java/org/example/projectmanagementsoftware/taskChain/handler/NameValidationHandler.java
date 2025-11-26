package org.example.projectmanagementsoftware.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.TaskDto;

public class NameValidationHandler implements TaskHandler {

    private TaskHandler next;

    @Override
    public void setNext(TaskHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TaskDto dto, Task existingTask) {

        if (dto.getName() == null || dto.getName().trim().isEmpty()) {
            throw new RuntimeException("Назва задачі не може бути порожньою (Chain validation)");
        }

        if (next != null) next.handle(dto, existingTask);
    }
}


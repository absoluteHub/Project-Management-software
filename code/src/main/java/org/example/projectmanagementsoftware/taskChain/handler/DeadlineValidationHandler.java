package org.example.projectmanagementsoftware.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.TaskDto;

import java.time.LocalDate;

public class DeadlineValidationHandler implements TaskHandler {

    private TaskHandler next;

    @Override
    public void setNext(TaskHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TaskDto dto, Task existingTask) {

        if (dto.getDeadline().isBefore(LocalDate.now())) {
            throw new RuntimeException("Дедлайн задачі не може бути у минулому(Chain validation)");
        }

        if (next != null) next.handle(dto, existingTask);
    }
}


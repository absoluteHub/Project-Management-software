package org.example.projectmanagementsoftware.pattern.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.TaskDto;
import org.example.projectmanagementsoftware.exception.TaskValidationException;

import java.time.LocalDate;

public class DeadlineValidationHandler implements TaskHandler {

    private TaskHandler next;

    @Override
    public void setNext(TaskHandler next) {
        this.next = next;
    }

    @Override
    public void handle(TaskDto dto, Task existingTask) {

        if (dto.getDeadline() != null && dto.getDeadline().isBefore(LocalDate.now())) {
            throw new TaskValidationException("Дедлайн задачі не може бути у минулому");
        }

        if (next != null) next.handle(dto, existingTask);
    }
}

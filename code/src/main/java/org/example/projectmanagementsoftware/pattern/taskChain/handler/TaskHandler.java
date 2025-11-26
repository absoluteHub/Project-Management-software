package org.example.projectmanagementsoftware.pattern.taskChain.handler;

import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.TaskDto;

public interface TaskHandler {

    void setNext(TaskHandler next);

    void handle(TaskDto dto, Task existingTask);
}

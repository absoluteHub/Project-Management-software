package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.enums.Priority;
import org.example.projectmanagementsoftware.domain.enums.TaskStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class TaskDto {

    private Long id;

    @NotBlank(message = "Назва задачі не може бути порожньою")
    private String name;

    @NotBlank(message = "Опис не може бути порожнім")
    private String description;

    @NotNull(message = "Дедлайн є обов’язковим")
    @Future(message = "Дата дедлайну повинна бути у майбутньому")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

    @NotNull(message = "Виберіть пріоритет")
    private Priority priority;

    private TaskStatus status = TaskStatus.TODO;
}

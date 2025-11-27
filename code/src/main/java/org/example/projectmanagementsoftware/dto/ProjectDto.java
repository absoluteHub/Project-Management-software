package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class ProjectDto {

    @NotBlank(message = "Назва не може бути порожньою")
    private String name;

    @NotBlank(message = "Опис не може бути порожнім")
    private String description;

    @NotNull(message = "Дата дедлайну є обов’язковою")
    @Future(message = "Дедлайн має бути у майбутньому")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadline;

}

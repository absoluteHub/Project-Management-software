package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.enums.IterationType;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
public class IterationDto {

    private Long id;

    @NotBlank(message = "Назва ітерації не може бути порожньою")
    private String name;

    @NotNull(message = "Дата початку є обов’язковою")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @NotNull(message = "Дата кінця є обов’язковою")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "Дата кінця має бути у майбутньому")
    private LocalDate endDate;

    @NotNull(message = "Оберіть тип ітерації")
    private IterationType iterationType;

    @NotNull
    private Long projectId;
}

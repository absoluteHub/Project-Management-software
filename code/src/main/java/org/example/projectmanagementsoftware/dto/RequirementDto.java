package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.example.projectmanagementsoftware.domain.enums.RequirementType;
import org.example.projectmanagementsoftware.domain.enums.RequirementStatus;

@Getter
@Setter
public class RequirementDto {

    private Long id;

    @NotBlank(message = "Назва вимоги не може бути порожньою")
    private String title;

    @NotBlank(message = "Опис не може бути порожнім")
    private String description;

    @NotNull(message = "Оберіть тип вимоги")
    private RequirementType requirementType;

    private RequirementStatus status = RequirementStatus.DRAFT;

    @NotNull
    private Long projectId;
}

package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
public class VersionDto {

    private Long id;

    @NotBlank(message = "Версія не може бути порожньою")
    private String versionNumber;

    @NotBlank(message = "Опис не може бути порожнім")
    private String description;

    @NotNull(message = "Оберіть дату релізу")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    private MultipartFile executableFile;

    @NotNull
    private Long projectId;
}

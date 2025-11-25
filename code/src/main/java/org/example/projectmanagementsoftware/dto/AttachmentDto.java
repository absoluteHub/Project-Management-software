package org.example.projectmanagementsoftware.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
public class AttachmentDto {

    @NotNull(message = "Файл обов'язковий")
    private MultipartFile file;

    @NotNull(message = "Task ID обов'язковий")
    private Long taskId;

}

package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Attachment;
import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.AttachmentDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.AttachmentRepository;
import org.example.projectmanagementsoftware.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final TaskRepository taskRepository;

    private final String uploadDir = "uploads/";

    public Attachment createAttachment(AttachmentDto dto) {

        Task task = taskRepository.findById(dto.getTaskId())
                .orElseThrow(() -> new NotFoundException("Task not found: " + dto.getTaskId()));

        var file = dto.getFile();
        if (file.isEmpty()) throw new RuntimeException("Файл порожній");

        try {
            Files.createDirectories(Path.of(uploadDir));
        } catch (IOException e) {
            throw new RuntimeException("Не можу створити папку uploads/");
        }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path savePath = Path.of(uploadDir + filename);

        try {
            file.transferTo(savePath);
        } catch (IOException e) {
            throw new RuntimeException("Помилка збереження файлу");
        }

        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setPath("/uploads/" + filename); // шлях для браузера!
        attachment.setTask(task);

        return attachmentRepository.save(attachment);
    }

    public Long delete(Long id) {

        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment not found"));

        Path filePath = Path.of(uploadDir + attachment.getPath().replace("/uploads/", ""));
        try {
            Files.deleteIfExists(filePath);
        } catch (Exception ignored) {}

        Long taskId = attachment.getTask().getId();

        attachmentRepository.delete(attachment);

        return taskId;
    }
}

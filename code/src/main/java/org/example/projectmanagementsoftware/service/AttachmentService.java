package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Attachment;
import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.dto.AttachmentDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.AttachmentRepository;
import org.example.projectmanagementsoftware.repository.TaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

        MultipartFile file = dto.getFile();
        if (file.isEmpty()) throw new RuntimeException("Файл порожній");

        String savedPath = saveFile(file);

        Attachment attachment = new Attachment();
        attachment.setFileName(file.getOriginalFilename());
        attachment.setFileType(file.getContentType());
        attachment.setPath(savedPath);
        attachment.setTask(task);

        return attachmentRepository.save(attachment);
    }

    public Long delete(Long id) {

        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Attachment not found"));

        try {
            Files.deleteIfExists(Path.of(uploadDir + attachment.getPath().replace("/uploads/", "")));
        } catch (IOException ignored) {}

        Long taskId = attachment.getTask().getId();
        attachmentRepository.delete(attachment);

        return taskId;
    }

    private String saveFile(MultipartFile file) {

        try { Files.createDirectories(Path.of(uploadDir)); }
        catch (IOException e) { throw new RuntimeException("Не можу створити uploads/"); }

        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path path = Path.of(uploadDir + filename);

        try { file.transferTo(path); }
        catch (IOException e) { throw new RuntimeException("Помилка збереження файлу"); }

        return "/uploads/" + filename;
    }
}

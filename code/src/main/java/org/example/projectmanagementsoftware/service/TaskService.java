package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.domain.enums.TaskStatus;
import org.example.projectmanagementsoftware.dto.TaskDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.TaskRepository;
import org.example.projectmanagementsoftware.pattern.taskChain.config.TaskValidationChain;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskValidationChain taskValidationChain;

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Task not found: " + id));
    }

    public TaskDto getTaskDtoById(Long id) {
        Task t = getTaskById(id);
        TaskDto dto = new TaskDto();

        dto.setId(t.getId());

        dto.setName(t.getName());
        dto.setDescription(t.getDescription());
        dto.setDeadline(t.getDeadline());
        dto.setPriority(t.getPriority());
        dto.setStatus(t.getStatus());

        return dto;
    }

    public Task createTask(TaskDto dto, Long projectId) {

        taskValidationChain.getChain().handle(dto, null);

        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new NotFoundException("Project not found"));

        Task t = new Task();
        t.setProject(project);
        t.setName(dto.getName());
        t.setDescription(dto.getDescription());
        t.setDeadline(dto.getDeadline());
        t.setPriority(dto.getPriority());
        t.setStatus(dto.getStatus());

        return taskRepository.save(t);
    }

    public Long update(Long id, TaskDto dto) {

        Task existing = getTaskById(id);

        taskValidationChain.getChain().handle(dto, existing);

        existing.setName(dto.getName());
        existing.setDescription(dto.getDescription());
        existing.setDeadline(dto.getDeadline());
        existing.setPriority(dto.getPriority());
        existing.setStatus(dto.getStatus());

        taskRepository.save(existing);
        return existing.getProject().getId();
    }

    public Long delete(Long id) {
        Task t = getTaskById(id);
        Long projectId = t.getProject().getId();
        taskRepository.delete(t);
        return projectId;
    }

    public void markDone(Long id) {
        Task t = getTaskById(id);
        t.setStatus(TaskStatus.DONE);
        taskRepository.save(t);
    }

    public List<Task> getByProjectAndStatus(Long projectId, TaskStatus status) {
        return taskRepository.findByProjectIdAndStatus(projectId, status);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }
}

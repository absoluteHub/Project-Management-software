package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.domain.enums.TaskStatus;
import org.example.projectmanagementsoftware.dto.TaskDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.TaskRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;

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
        Task t = getTaskById(id);

        t.setName(dto.getName());
        t.setDescription(dto.getDescription());
        t.setDeadline(dto.getDeadline());
        t.setPriority(dto.getPriority());
        t.setStatus(dto.getStatus());

        taskRepository.save(t);
        return t.getProject().getId();
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

}

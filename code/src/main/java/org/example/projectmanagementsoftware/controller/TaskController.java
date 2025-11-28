package org.example.projectmanagementsoftware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Task;
import org.example.projectmanagementsoftware.domain.enums.TaskStatus;
import org.example.projectmanagementsoftware.dto.TaskDto;
import org.example.projectmanagementsoftware.exception.TaskValidationException;
import org.example.projectmanagementsoftware.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskById(id));
        return "tasks/view";
    }

    @GetMapping("/create/{projectId}")
    public String createForm(@PathVariable Long projectId, Model model) {
        TaskDto dto = new TaskDto();
        model.addAttribute("task", dto);
        model.addAttribute("projectId", projectId);
        return "tasks/create";
    }

    @PostMapping("/create/{projectId}")
    public String create(@PathVariable Long projectId,
                         @Valid @ModelAttribute("task") TaskDto dto,
                         BindingResult result) {

        if (result.hasErrors()) return "tasks/create";

        taskService.createTask(dto, projectId);
        return "redirect:/projects/" + projectId;
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("task", taskService.getTaskDtoById(id));
        return "tasks/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("task") TaskDto dto,
                         BindingResult result) {

        if (result.hasErrors()) return "tasks/edit";

        try {
            Long projectId = taskService.update(id, dto);
            return "redirect:/projects/" + projectId;

        } catch (TaskValidationException e) {
            result.reject("validation", e.getMessage());
            return "tasks/edit";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Long projectId = taskService.delete(id);
        return "redirect:/projects/" + projectId;
    }

    @PostMapping("/{id}/done")
    public String markDone(@PathVariable Long id) {
        taskService.markDone(id);
        return "redirect:/tasks/" + id;
    }

    @GetMapping("/{id}/move/{status}")
    public String move(@PathVariable Long id,
                       @PathVariable TaskStatus status) {

        Task task = taskService.getTaskById(id);
        task.setStatus(status);
        taskService.save(task);

        return "redirect:/projects/" + task.getProject().getId() + "/board";
    }
}

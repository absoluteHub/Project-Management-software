package org.example.projectmanagementsoftware.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.dto.AttachmentDto;
import org.example.projectmanagementsoftware.service.AttachmentService;
import org.example.projectmanagementsoftware.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/attachments")
@RequiredArgsConstructor
public class AttachmentController {

    private final AttachmentService attachmentService;
    private final TaskService taskService;

    @GetMapping("/create/{taskId}")
    public String createForm(@PathVariable Long taskId, Model model) {
        AttachmentDto dto = new AttachmentDto();
        dto.setTaskId(taskId);

        model.addAttribute("attachment", dto);
        model.addAttribute("task", taskService.getTaskById(taskId));
        return "attachments/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("attachment") AttachmentDto dto,
                         BindingResult result) {

        if (result.hasErrors()) return "attachments/create";

        attachmentService.createAttachment(dto);
        return "redirect:/tasks/" + dto.getTaskId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        return "redirect:/tasks/" + attachmentService.delete(id);
    }
}

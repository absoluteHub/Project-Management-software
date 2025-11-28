package org.example.projectmanagementsoftware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.dto.IterationDto;
import org.example.projectmanagementsoftware.service.IterationService;
import org.example.projectmanagementsoftware.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/iterations")
@RequiredArgsConstructor
public class IterationController {

    private final IterationService iterationService;
    private final ProjectService projectService;

    @GetMapping("/project/{projectId}")
    public String list(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectService.getProjectById(projectId));
        model.addAttribute("iterations", iterationService.getByProject(projectId));
        return "iterations/list";
    }

    @GetMapping("/create/{projectId}")
    public String createForm(@PathVariable Long projectId, Model model) {
        IterationDto dto = new IterationDto();
        dto.setProjectId(projectId);
        model.addAttribute("iteration", dto);
        return "iterations/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("iteration") IterationDto dto,
                         BindingResult result) {

        if (result.hasErrors()) return "iterations/create";

        iterationService.saveNew(dto);
        return "redirect:/iterations/project/" + dto.getProjectId();
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        model.addAttribute("iteration", iterationService.toDto(id));
        return "iterations/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("iteration") IterationDto dto,
                         BindingResult result) {

        if (result.hasErrors()) return "iterations/edit";

        iterationService.update(id, dto);
        return "redirect:/iterations/project/" + dto.getProjectId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam Long projectId) {
        iterationService.delete(id);
        return "redirect:/iterations/project/" + projectId;
    }
}

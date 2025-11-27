package org.example.projectmanagementsoftware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.dto.ProjectDto;
import org.example.projectmanagementsoftware.pattern.facade.ProjectManagementFacade;
import org.example.projectmanagementsoftware.pattern.facade.dto.KanbanDto;
import org.example.projectmanagementsoftware.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectManagementFacade projectFacade;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("projects", projectService.getAllProjects());
        return "projects/list";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);

        model.addAttribute("project", project);
        model.addAttribute("tasks", project.getTasks());
        return "projects/view";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("project", new ProjectDto());
        return "projects/create";
    }

    @PostMapping("/create")
    public String create(
            @Valid @ModelAttribute("project") ProjectDto dto,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "projects/create";
        }

        Project p = new Project();
        p.setName(dto.getName());
        p.setDescription(dto.getDescription());
        p.setDeadline(dto.getDeadline());

        projectService.save(p);
        return "redirect:/projects";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.getProjectById(id));
        return "projects/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Project project) {
        projectService.update(id, project);
        return "redirect:/projects/" + id;
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        projectService.delete(id);
        return "redirect:/projects";
    }

    @GetMapping("/{projectId}/board")
    public String kanban(@PathVariable Long projectId, Model model) {
        KanbanDto board = projectFacade.getKanban(projectId);
        model.addAttribute("board", board);
        return "projects/board";
    }
}

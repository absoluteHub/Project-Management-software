package org.example.projectmanagementsoftware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.dto.RequirementDto;
import org.example.projectmanagementsoftware.service.RequirementService;
import org.example.projectmanagementsoftware.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/requirements")
@RequiredArgsConstructor
public class RequirementController {

    private final RequirementService requirementService;
    private final ProjectService projectService;

    @GetMapping("/project/{projectId}")
    public String list(@PathVariable Long projectId, Model model) {
        model.addAttribute("project", projectService.getProjectById(projectId));
        model.addAttribute("requirements", requirementService.getByProject(projectId));
        return "requirements/list";
    }

    @GetMapping("/create/{projectId}")
    public String createForm(@PathVariable Long projectId, Model model) {

        RequirementDto dto = new RequirementDto();
        dto.setProjectId(projectId);

        model.addAttribute("req", dto);
        return "requirements/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("req") RequirementDto dto,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "requirements/create";
        }

        requirementService.create(dto);
        return "redirect:/requirements/project/" + dto.getProjectId();
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        var req = requirementService.getById(id);
        RequirementDto dto = new RequirementDto();

        dto.setId(req.getId());
        dto.setTitle(req.getTitle());
        dto.setDescription(req.getDescription());
        dto.setRequirementType(req.getRequirementType());
        dto.setStatus(req.getStatus());
        dto.setProjectId(req.getProject().getId());

        model.addAttribute("req", dto);

        return "requirements/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       @Valid @ModelAttribute("req") RequirementDto dto,
                       BindingResult result) {

        if (result.hasErrors()) {
            return "requirements/edit";
        }

        requirementService.update(id, dto);
        return "redirect:/requirements/project/" + dto.getProjectId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam Long projectId) {

        requirementService.delete(id);
        return "redirect:/requirements/project/" + projectId;
    }
}

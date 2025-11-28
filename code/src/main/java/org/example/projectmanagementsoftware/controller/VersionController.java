package org.example.projectmanagementsoftware.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.dto.VersionDto;
import org.example.projectmanagementsoftware.pattern.composite.interfaces.VersionComponent;
import org.example.projectmanagementsoftware.service.ProjectService;
import org.example.projectmanagementsoftware.service.VersionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/versions")
@RequiredArgsConstructor
public class VersionController {

    private final VersionService versionService;
    private final ProjectService projectService;

    @GetMapping("/project/{projectId}")
    public String list(@PathVariable Long projectId, Model model) {

        model.addAttribute("project", projectService.getProjectById(projectId));
        model.addAttribute("versions", versionService.getByProject(projectId));

        return "versions/list";
    }

    @GetMapping("/create/{projectId}")
    public String createForm(@PathVariable Long projectId, Model model) {

        VersionDto dto = new VersionDto();
        dto.setProjectId(projectId);

        model.addAttribute("version", dto);
        model.addAttribute("versions", versionService.getByProject(projectId));
        return "versions/create";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("version") VersionDto dto,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "versions/create";
        }

        versionService.create(dto);
        return "redirect:/versions/project/" + dto.getProjectId();
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {

        var v = versionService.getById(id);

        VersionDto dto = new VersionDto();
        dto.setId(v.getId());
        dto.setVersionNumber(v.getVersionNumber());
        dto.setDescription(v.getDescription());
        dto.setReleaseDate(v.getReleaseDate());
        dto.setProjectId(v.getProject().getId());

        model.addAttribute("version", dto);
        model.addAttribute("versions", versionService.getByProject(id));

        return "versions/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("version") VersionDto dto,
                         BindingResult result) {

        if (result.hasErrors()) {
            return "versions/edit";
        }

        versionService.update(id, dto);
        return "redirect:/versions/project/" + dto.getProjectId();
    }


    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id, @RequestParam Long projectId) {

        versionService.delete(id);
        return "redirect:/versions/project/" + projectId;
    }

    @GetMapping("/{id}/tree")
    public String tree(@PathVariable Long id, Model model) {
        VersionComponent tree = versionService.getVersionTree(id);
        model.addAttribute("tree", tree);
        return "versions/tree";
    }
}

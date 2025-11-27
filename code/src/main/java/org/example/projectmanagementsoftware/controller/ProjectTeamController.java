package org.example.projectmanagementsoftware.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.dto.ProjectTeamDto;
import org.example.projectmanagementsoftware.pattern.facade.ProjectManagementFacade;
import org.example.projectmanagementsoftware.pattern.facade.dto.TeamDto;
import org.example.projectmanagementsoftware.service.ProjectTeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/projects/team")
@RequiredArgsConstructor
public class ProjectTeamController {

    private final ProjectTeamService projectTeamService;
    private final ProjectManagementFacade facade;

    @GetMapping("/{projectId}")
    public String team(@PathVariable Long projectId, Model model) {
        TeamDto teamDto = facade.getTeam (projectId);
        model.addAttribute ("team",teamDto);
        return "team/manage";
    }

    @PostMapping("/add")
    public String addMember(@ModelAttribute ProjectTeamDto dto) {
        projectTeamService.addMember(dto.getProjectId(), dto.getUserId());
        return "redirect:/projects/team/" + dto.getProjectId();
    }

    @PostMapping("/remove/{id}")
    public String remove(@PathVariable Long id, @RequestParam Long projectId) {
        projectTeamService.removeMember(id);
        return "redirect:/projects/team/" + projectId;
    }
}

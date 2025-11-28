package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Requirement;
import org.example.projectmanagementsoftware.dto.RequirementDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.pattern.strategy.implementations.RequirementStrategyContext;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.RequirementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final ProjectRepository projectRepository;
    private final RequirementStrategyContext strategyContext;

    public List<Requirement> getByProject(Long projectId) {
        return requirementRepository.findByProjectId(projectId);
    }

    public Requirement getById(Long id) {
        return requirementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requirement not found: " + id));
    }

    public Requirement create(RequirementDto dto) {
        Project project = getProject(dto.getProjectId());
        var strategy = strategyContext.getStrategy(dto.getRequirementType());

        Requirement r = new Requirement();
        applyDto(r, dto);
        r.setProject(project);

        strategy.enrichRequirement(r);
        r.setDescription(strategy.generateDescription(dto.getDescription()));

        return requirementRepository.save(r);
    }

    public Requirement update(Long id, RequirementDto dto) {
        Requirement r = getById(id);
        applyDto(r, dto);
        return requirementRepository.save(r);
    }

    public void delete(Long id) {
        if (!requirementRepository.existsById(id)) {
            throw new NotFoundException("Requirement not found: " + id);
        }
        requirementRepository.deleteById(id);
    }

    public RequirementDto toDto(Long id) {
        Requirement r = getById(id);

        RequirementDto dto = new RequirementDto();
        dto.setId(r.getId());
        dto.setTitle(r.getTitle());
        dto.setDescription(r.getDescription());
        dto.setRequirementType(r.getRequirementType());
        dto.setStatus(r.getStatus());
        dto.setProjectId(r.getProject().getId());

        return dto;
    }

    private void applyDto(Requirement r, RequirementDto dto) {
        r.setTitle(dto.getTitle());
        r.setDescription(dto.getDescription());
        r.setRequirementType(dto.getRequirementType());
        r.setStatus(dto.getStatus());
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found: " + id));
    }
}


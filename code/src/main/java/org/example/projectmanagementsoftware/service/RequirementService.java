package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Requirement;
import org.example.projectmanagementsoftware.dto.RequirementDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.RequirementRepository;
import org.example.projectmanagementsoftware.strategy.implementations.RequirementStrategyFactory;
import org.example.projectmanagementsoftware.strategy.intefraces.RequirementStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequirementService {

    private final RequirementRepository requirementRepository;
    private final ProjectRepository projectRepository;

    public List<Requirement> getByProject(Long projectId) {
        return requirementRepository.findByProjectId(projectId);
    }

    public Requirement getById(Long id) {
        return requirementRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Requirement not found: " + id));
    }

    public Requirement create(RequirementDto dto) {

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found"));

        RequirementStrategy strategy = RequirementStrategyFactory.getStrategy(dto.getRequirementType());
        String processedDescription = strategy.generateDescription(dto.getDescription());

        Requirement r = new Requirement();
        r.setTitle(dto.getTitle());
        r.setDescription(processedDescription);
        r.setRequirementType(dto.getRequirementType());
        r.setStatus(dto.getStatus());
        r.setProject(project);

        return requirementRepository.save(r);
    }

    public Requirement update(Long id, RequirementDto dto) {
        Requirement r = getById(id);

        var strategy = RequirementStrategyFactory.getStrategy(dto.getRequirementType());
        String processedDescription = strategy.generateDescription(dto.getDescription());

        r.setTitle(dto.getTitle());
        r.setDescription(processedDescription);
        r.setRequirementType(dto.getRequirementType());
        r.setStatus(dto.getStatus());

        return requirementRepository.save(r);
    }

    public void delete(Long id) {
        if (!requirementRepository.existsById(id)) {
            throw new NotFoundException("Requirement not found: " + id);
        }
        requirementRepository.deleteById(id);
    }
}

package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Iteration;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.dto.IterationDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.IterationRepository;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IterationService {

    private final IterationRepository iterationRepository;
    private final ProjectRepository projectRepository;

    public List<Iteration> getByProject(Long projectId) {
        return iterationRepository.findByProjectId(projectId);
    }

    public Iteration getById(Long id) {
        return iterationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Iteration not found: " + id));
    }

    public Iteration saveNew(IterationDto dto) {
        Iteration it = new Iteration();
        it.setProject(getProject(dto.getProjectId()));
        applyDto(it, dto);
        return iterationRepository.save(it);
    }

    public Iteration update(Long id, IterationDto dto) {
        Iteration it = getById(id);
        applyDto(it, dto);
        return iterationRepository.save(it);
    }

    public void delete(Long id) {
        iterationRepository.deleteById(id);
    }

    public IterationDto toDto(Long id) {
        Iteration it = getById(id);

        IterationDto dto = new IterationDto();
        dto.setId(it.getId());
        dto.setName(it.getName());
        dto.setStartDate(it.getStartDate());
        dto.setEndDate(it.getEndDate());
        dto.setIterationType(it.getIterationType());
        dto.setProjectId(it.getProject().getId());

        return dto;
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found: " + id));
    }

    private void applyDto(Iteration it, IterationDto dto) {
        it.setName(dto.getName());
        it.setStartDate(dto.getStartDate());
        it.setEndDate(dto.getEndDate());
        it.setIterationType(dto.getIterationType());
    }
}


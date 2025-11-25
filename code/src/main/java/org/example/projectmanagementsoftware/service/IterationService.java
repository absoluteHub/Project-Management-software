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

    public Iteration create(IterationDto dto) {
        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found"));

        Iteration it = new Iteration();
        it.setName(dto.getName());
        it.setStartDate(dto.getStartDate());
        it.setEndDate(dto.getEndDate());
        it.setIterationType(dto.getIterationType());
        it.setProject(project);

        return iterationRepository.save(it);
    }

    public Iteration update(Long id, IterationDto dto) {
        Iteration it = getById(id);

        it.setName(dto.getName());
        it.setStartDate(dto.getStartDate());
        it.setEndDate(dto.getEndDate());
        it.setIterationType(dto.getIterationType());

        return iterationRepository.save(it);
    }

    public void delete(Long id) {
        iterationRepository.deleteById(id);
    }
}

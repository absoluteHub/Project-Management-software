package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Version;
import org.example.projectmanagementsoftware.dto.VersionDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.pattern.composite.VersionTreeBuilder;
import org.example.projectmanagementsoftware.pattern.composite.interfaces.VersionComponent;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.VersionRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VersionService {

    private final VersionRepository versionRepository;
    private final ProjectRepository projectRepository;

    private final String uploadDir = "uploads/versions/";

    public List<Version> getByProject(Long projectId) {
        return versionRepository.findByProjectId(projectId);
    }

    public Version getById(Long id) {
        return versionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Version not found: " + id));
    }

    public Version create(VersionDto dto) {
        Version v = new Version();
        v.setProject(getProject(dto.getProjectId()));
        applyDtoToEntity(v, dto);
        return versionRepository.save(v);
    }

    public Version update(Long id, VersionDto dto) {
        Version v = getById(id);
        applyDtoToEntity(v, dto);
        return versionRepository.save(v);
    }

    public void delete(Long id) {
        versionRepository.deleteById(id);
    }

    public VersionComponent getVersionTree(Long rootId) {
        return new VersionTreeBuilder().buildTree(getById(rootId));
    }

    private Project getProject(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Project not found: " + id));
    }

    private void applyDtoToEntity(Version v, VersionDto dto) {
        v.setVersionNumber(dto.getVersionNumber());
        v.setDescription(dto.getDescription());
        v.setReleaseDate(dto.getReleaseDate());

        if (dto.getParentId() != null) {
            v.setParent(getById(dto.getParentId()));
        }

        if (dto.getExecutableFile() != null && !dto.getExecutableFile().isEmpty()) {
            v.setExecutablePath(saveFile(dto));
        }
    }

    private String saveFile(VersionDto dto) {
        try { Files.createDirectories(Path.of(uploadDir)); } catch (IOException ignored) {}

        String filename = System.currentTimeMillis() + "_" + dto.getExecutableFile().getOriginalFilename();
        Path path = Path.of(uploadDir + filename);

        try {
            dto.getExecutableFile().transferTo(path);
        } catch (IOException e) {
            throw new RuntimeException("Помилка збереження файлу");
        }

        return "/uploads/versions/" + filename;
    }
}

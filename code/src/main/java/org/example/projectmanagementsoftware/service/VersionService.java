package org.example.projectmanagementsoftware.service;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagementsoftware.domain.Project;
import org.example.projectmanagementsoftware.domain.Version;
import org.example.projectmanagementsoftware.dto.VersionDto;
import org.example.projectmanagementsoftware.exception.NotFoundException;
import org.example.projectmanagementsoftware.repository.ProjectRepository;
import org.example.projectmanagementsoftware.repository.VersionRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
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

        Project project = projectRepository.findById(dto.getProjectId())
                .orElseThrow(() -> new NotFoundException("Project not found"));

        Version v = new Version();
        v.setVersionNumber(dto.getVersionNumber());
        v.setDescription(dto.getDescription());
        v.setReleaseDate(dto.getReleaseDate());
        v.setProject(project);

        if (dto.getExecutableFile() != null && !dto.getExecutableFile().isEmpty()) {

            try { Files.createDirectories(Path.of(uploadDir)); } catch (IOException ignored) {}

            String filename = System.currentTimeMillis() + "_" + dto.getExecutableFile().getOriginalFilename();
            Path path = Path.of(uploadDir + filename);

            try {
                dto.getExecutableFile().transferTo(path);
            } catch (IOException e) {
                throw new RuntimeException("Помилка збереження виконуваного файлу");
            }

            v.setExecutablePath("/uploads/versions/" + filename);
        }

        return versionRepository.save(v);
    }

    public Version update(Long id, VersionDto dto) {

        Version v = versionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Version not found: " + id));

        v.setVersionNumber(dto.getVersionNumber());
        v.setDescription(dto.getDescription());
        v.setReleaseDate(dto.getReleaseDate());

        if (dto.getExecutableFile() != null && !dto.getExecutableFile().isEmpty()) {

            try { Files.createDirectories(Path.of(uploadDir)); } catch (IOException ignored) {}

            String filename = System.currentTimeMillis() + "_" + dto.getExecutableFile().getOriginalFilename();
            Path path = Path.of(uploadDir + filename);

            try {
                dto.getExecutableFile().transferTo(path);
            } catch (IOException e) {
                throw new RuntimeException("Помилка збереження виконуваного файлу");
            }

            v.setExecutablePath("/uploads/versions/" + filename);
        }

        return versionRepository.save(v);
    }

    public void delete(Long id) {
        versionRepository.deleteById(id);
    }
}

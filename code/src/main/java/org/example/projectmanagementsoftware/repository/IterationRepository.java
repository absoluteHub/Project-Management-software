package org.example.projectmanagementsoftware.repository;

import org.example.projectmanagementsoftware.domain.Iteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IterationRepository extends JpaRepository<Iteration, Long> {

    List<Iteration> findByProjectId(Long projectId);
}

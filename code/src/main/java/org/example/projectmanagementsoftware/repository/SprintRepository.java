package org.example.projectmanagementsoftware.repository;

import org.example.projectmanagementsoftware.domain.Sprint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {

}
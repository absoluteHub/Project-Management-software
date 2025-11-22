package org.example.projectmanagementsoftware.repository;

import org.example.projectmanagementsoftware.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}

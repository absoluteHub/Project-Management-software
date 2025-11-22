package org.example.projectmanagementsoftware.repository;

import org.example.projectmanagementsoftware.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
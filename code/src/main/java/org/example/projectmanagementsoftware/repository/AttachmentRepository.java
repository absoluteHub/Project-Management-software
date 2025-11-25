package org.example.projectmanagementsoftware.repository;

import org.example.projectmanagementsoftware.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachmentRepository extends JpaRepository<Attachment, Long> {}

package server.example.assignment.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.example.assignment.file.domain.File;

public interface FileRepository extends JpaRepository<File, Long> {
}

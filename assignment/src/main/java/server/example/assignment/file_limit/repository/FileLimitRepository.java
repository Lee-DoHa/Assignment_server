package server.example.assignment.file_limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.example.assignment.file_limit.domain.FileLimit;

public interface FileLimitRepository extends JpaRepository<FileLimit, Long> {
}

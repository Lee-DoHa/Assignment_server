package server.example.assignment.file_limit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import server.example.assignment.file_limit.domain.FileLimit;

import java.util.Optional;

public interface FileLimitRepository extends JpaRepository<FileLimit, Long> {
    Optional<FileLimit> findByName(String name);
}

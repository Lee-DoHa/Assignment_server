package server.example.assignment.file_limit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.example.assignment.file_limit.domain.FileLimit;
import server.example.assignment.file_limit.repository.FileLimitRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileLimitService {
    private final FileLimitRepository fileLimitRepository;

    public void create(String type) {
        FileLimit createLimit = FileLimit.builder().type(type).build();
        fileLimitRepository.save(createLimit);
    }

    public void delete(Long id) {
        Optional<FileLimit> fileLimit = fileLimitRepository.findById(id);
        fileLimit.ifPresent(fileLimitRepository::delete);
    }
}

package server.example.assignment.file_limit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import server.example.assignment.file_limit.domain.FileLimit;
import server.example.assignment.file_limit.domain.LimitType;
import server.example.assignment.file_limit.exception.FileLimitException;
import server.example.assignment.file_limit.exception.FileLimitExceptionType;
import server.example.assignment.file_limit.repository.FileLimitRepository;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileLimitService {
    private final FileLimitRepository fileLimitRepository;
    private String[] fixed = {"bat", "cmd", "com", "cpl", "exe", "scr", "js"};

    public void create(String name) {
        if(fileLimitRepository.findByName(name).isPresent()) {
            throw new FileLimitException(FileLimitExceptionType.ALREADY_EXIST_NAME);
        }
        FileLimit createLimit = FileLimit.builder()
                .name(name)
                .limitType(Arrays.asList(fixed).contains(name) ? LimitType.FIXED : LimitType.CUSTOM)
                .build();
        fileLimitRepository.save(createLimit);
    }

    public void delete(Long id) {
        Optional<FileLimit> fileLimit = fileLimitRepository.findById(id);
        fileLimit.ifPresent(fileLimitRepository::delete);
    }
}

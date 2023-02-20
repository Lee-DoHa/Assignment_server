package server.example.assignment.file_limit.service;

import antlr.MismatchedCharException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import server.example.assignment.file_limit.domain.FileLimit;
import server.example.assignment.file_limit.domain.FileLimitResponse;
import server.example.assignment.file_limit.domain.LimitType;
import server.example.assignment.file_limit.exception.*;
import server.example.assignment.file_limit.repository.FileLimitRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileLimitService {
    private final FileLimitRepository fileLimitRepository;
    private String[] fixed = {"bat", "cmd", "com", "cpl", "exe", "scr", "js"};

    public void create(String name) {
        if(fileLimitRepository.findByName(name).isPresent()) {
            throw new AlreadyExistName("name", "이미 제한된 확장자입니다.");
        }
        if(name.length() > 20) {
            throw new NameOverLoad("name", "확장자 이름은 20자를 초과할 수 없습니다.");
        }
        if(fileLimitRepository.countCustom() == 200) {
            throw new LimitOverload("count", "커스텀 확장자는 200개를 초과할 수 없습니다.");
        }
        FileLimit createLimit = FileLimit.builder()
                .name(name)
                .limitType(Arrays.asList(fixed).contains(name) ? LimitType.FIXED : LimitType.CUSTOM)
                .build();
        fileLimitRepository.save(createLimit);
    }

    public void delete(Long id) {
        FileLimit fileLimit = fileLimitRepository.findById(id).orElseThrow(LimitNotFound::new);
        fileLimitRepository.delete(fileLimit);
    }

    public List<FileLimitResponse> get() {
        List<FileLimitResponse> result = fileLimitRepository.findAll().stream()
                .map(FileLimitResponse::new)
                .collect(Collectors.toList());

        return result;
    }
}

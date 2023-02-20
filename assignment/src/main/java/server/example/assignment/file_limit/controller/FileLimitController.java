package server.example.assignment.file_limit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import server.example.assignment.file_limit.domain.FileLimitResponse;
import server.example.assignment.file_limit.service.FileLimitService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class FileLimitController {

    private final FileLimitService fileLimitService;

    @PostMapping(value = "/fileLimit")
    public void limit(@RequestBody String name) {
        fileLimitService.create(name);
    }

    @GetMapping(value = "/fileLimit")
    public List<FileLimitResponse> get() {
        return fileLimitService.get();
    }

    @DeleteMapping(value = "/fileLimit/{id}")
    public void delete(@PathVariable Long id) {
        fileLimitService.delete(id);
    }



}

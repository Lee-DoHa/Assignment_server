package server.example.assignment.file_limit.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import server.example.assignment.file_limit.service.FileLimitService;

@RestController
@RequiredArgsConstructor
public class FileLimitController {

    private final FileLimitService fileLimitService;

    @PostMapping(value = "/fileLimit")
    public void limit(@RequestBody String type) {
        fileLimitService.create(type);
    }

    @DeleteMapping(value = "/fileLimit")
    public void delete(@RequestBody Long id) {
        fileLimitService.delete(id);
    }

}

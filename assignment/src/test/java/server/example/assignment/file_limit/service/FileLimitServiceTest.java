package server.example.assignment.file_limit.service;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import server.example.assignment.file_limit.domain.FileLimit;
import server.example.assignment.file_limit.domain.FileLimitResponse;
import server.example.assignment.file_limit.domain.LimitType;
import server.example.assignment.file_limit.exception.AlreadyExistName;
import server.example.assignment.file_limit.exception.LimitNotFound;
import server.example.assignment.file_limit.exception.LimitOverload;
import server.example.assignment.file_limit.exception.NameOverLoad;
import server.example.assignment.file_limit.repository.FileLimitRepository;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class FileLimitServiceTest {

    @Autowired
    private FileLimitService fileLimitService;

    @Autowired
    private FileLimitRepository fileLimitRepository;

    @BeforeEach
    void each() {
        fileLimitRepository.deleteAll();
    }

    @AfterEach
    void after() {
        fileLimitRepository.deleteAll();
    }

    @Test
    @DisplayName("확장자 제한(생성)")
    void create() {
        // given
        String name = "jpg";

        // when
        fileLimitService.create(name);

        // then
        Assertions.assertEquals(1L, fileLimitRepository.count());

        FileLimit fileLimit = fileLimitRepository.findAll().get(0);
        assertEquals("jpg", fileLimit.getName());
    }

    @Test
    @DisplayName("확장자 제한 - 고정 확장자 생성시")
    void fixed_create() {
        // given
        String name = "js";

        // when
        fileLimitService.create(name);

        // then
        Assertions.assertEquals(1L, fileLimitRepository.count());
        FileLimit fileLimit = fileLimitRepository.findAll().get(0);
        assertEquals("js", fileLimit.getName());
        assertEquals("FIXED", fileLimit.getLimitType().toString());

    }

    @Test
    @DisplayName("확장자 제한 - 같은 이름 생성시")
    void name_duplicate() {
        // given
        FileLimit jpg = FileLimit.builder()
                .name("jpg")
                .limitType(LimitType.CUSTOM)
                .build();
        fileLimitRepository.save(jpg);
        String name = "jpg";

        // expected
        assertThrows(AlreadyExistName.class, () -> {
            fileLimitService.create(name);
        });

    }
    @Test
    @DisplayName("확장자 제한 - 20자 초과되는 이름으로 생성시")
    void name_overload() {
        // given
        String name = "aaaaaaaaaaaaaaaaaaaaa";

        // expected
        assertThrows(NameOverLoad.class, () -> {
            fileLimitService.create(name);
        });

    }

    @Test
    @DisplayName("확장자 제한 - 커스텀 확장자 200개 이상 생성시")
    void custom_overload() {
        // given
        List<FileLimit> requestLimits = IntStream.range(0, 200)
                .mapToObj(i ->
                        FileLimit.builder()
                                .name("test - " + i)
                                .limitType(LimitType.CUSTOM)
                                .build()).toList();
        fileLimitRepository.saveAll(requestLimits);
        String name = "201번째";

        // expected
        assertThrows(LimitOverload.class, () -> {
            fileLimitService.create(name);
        });

    }

    @Test
    @DisplayName("확장자 제한(삭제)")
    void delete() {
        // given
        FileLimit jpg = FileLimit.builder()
                .name("jpg")
                .limitType(LimitType.CUSTOM)
                .build();
        fileLimitRepository.save(jpg);

        // when
        fileLimitService.delete(jpg.getId());

        // then
        Assertions.assertEquals(0L, fileLimitRepository.count());

    }

    @Test
    @DisplayName("확장자 제한 - 존재하지않는 확장자 삭제시")
    void delete_not_found() {
        // given

        // expected
        assertThrows(LimitNotFound.class, () -> {
            fileLimitService.delete(1L);
        });

    }

    @Test
    @DisplayName("확장자 제한(전체조회)")
    void get() {
        // given
        List<FileLimit> requestLimits = IntStream.range(0, 10)
                .mapToObj(i ->
                        FileLimit.builder()
                                .name("test - " + i)
                                .limitType(LimitType.CUSTOM)
                                .build()).toList();
        fileLimitRepository.saveAll(requestLimits);

        // expected
        List<FileLimitResponse> fileLimitResponses = fileLimitService.get();
        Assertions.assertEquals(10, fileLimitResponses.size());
        Assertions.assertEquals("test - 0", fileLimitResponses.get(0).getName());

    }
}
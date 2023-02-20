package server.example.assignment.file_limit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import server.example.assignment.file_limit.domain.FileLimit;
import server.example.assignment.file_limit.domain.LimitType;
import server.example.assignment.file_limit.repository.FileLimitRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class FileLimitControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

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
    @DisplayName("/fileLimit POST 요청시 DB에 값이 저장된다.")
    void create() throws Exception{
        // given
        String name = "jpg";

        // when
        mockMvc.perform(post("/fileLimit")
                        .content(name)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());

        // then
        Assertions.assertEquals(1L, fileLimitRepository.count());

        FileLimit fileLimit = fileLimitRepository.findAll().get(0);
        assertEquals("jpg", fileLimit.getName());
    }

    @Test
    @DisplayName("/fileLimit GET 요청시 DB에 전체값이 반환된다.")
    void get() throws Exception{
        // given
        List<FileLimit> requestLimits = IntStream.range(0, 10)
                .mapToObj(i ->
                        FileLimit.builder()
                                .name("test - " + i)
                                .limitType(LimitType.CUSTOM)
                                .build()).toList();
        fileLimitRepository.saveAll(requestLimits);

        // expected
        mockMvc.perform(MockMvcRequestBuilders.get("/fileLimit"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", Matchers.is(10)))
                .andExpect(jsonPath("$[0].name").value("test - 0"))
                .andDo(print());

    }

    @Test
    void delete() throws Exception {
        // given
        FileLimit jpg = FileLimit.builder()
                .name("jpg")
                .limitType(LimitType.CUSTOM)
                .build();
        fileLimitRepository.save(jpg);
        FileLimit fileLimit = fileLimitRepository.findByName(jpg.getName()).orElseThrow();

        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/fileLimit/{id}", fileLimit.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andDo(print());

        // then
        Assertions.assertEquals(0L, fileLimitRepository.count());

    }
}
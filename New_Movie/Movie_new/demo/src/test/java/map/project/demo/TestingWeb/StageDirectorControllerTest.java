package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.StageDirectorController;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;
import map.project.demo.Repository.IStageDirectorRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StageDirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IStageDirectorRepo stageDirectorRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddDirector() throws Exception {
        StageDirector stageDirector = new StageDirector();
        stageDirector.setId("1AB");
        stageDirector.setFirstName("John");
        stageDirector.setLastName("Doe");

        mockMvc.perform(post("/api/stagedirector")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stageDirector)))
                .andExpect(status().isOk());

        Optional<StageDirector> savedDirector = stageDirectorRepository.findById("1AB");
        assertTrue(savedDirector.isPresent());

        StageDirector retrievedDirector = savedDirector.get();
        assertEquals("1AB", retrievedDirector.getId());

        stageDirectorRepository.deleteById("1AB");
    }


}

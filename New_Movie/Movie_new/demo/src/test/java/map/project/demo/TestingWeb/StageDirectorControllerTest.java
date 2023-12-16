package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.StageDirectorController;
import map.project.demo.Domain.StageDirector;
import map.project.demo.Repository.StageDirectorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StageDirectorController.class)
public class StageDirectorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StageDirectorRepository stageDirectorRepository;

    @InjectMocks
    private StageDirectorController stageDirectorController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddDirector() throws Exception {
        StageDirector stageDirector = new StageDirector();
        stageDirector.setFirstName("John");
        stageDirector.setLastName("Doe");

        doNothing().when(stageDirectorRepository).addDirectorToTable(any(StageDirector.class));

        mockMvc.perform(post("/api/stagedirector")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(stageDirector)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindDirectorById() throws Exception {
        StageDirector stageDirector = new StageDirector();
        stageDirector.setFirstName("John");
        stageDirector.setLastName("Doe");

        when(stageDirectorRepository.getDirectorWithIdFromTable("1")).thenReturn(stageDirector);

        mockMvc.perform(get("/api/stagedirector/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testDeleteDirector() throws Exception {
        doNothing().when(stageDirectorRepository).deleteDirectorWithIdFromTable(anyString());

        mockMvc.perform(delete("/api/stagedirector/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllDirectors() throws Exception {
        doNothing().when(stageDirectorRepository).deleteAllFromDirectorTable();

        mockMvc.perform(delete("/api/stagedirector"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateFirstName() throws Exception {
        doNothing().when(stageDirectorRepository).deleteDirectorWithIdFromTable(anyString());

        mockMvc.perform(put("/api/stagedirector/1/updateFirstName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"NewFirstName\""))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateLastName() throws Exception {
        doNothing().when(stageDirectorRepository).deleteDirectorWithIdFromTable(anyString());

        mockMvc.perform(put("/api/stagedirector/1/updateLastName")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"NewLastName\""))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(stageDirectorRepository).deleteMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/stagedirector/1/movies/2"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddMovie() throws Exception {
        doNothing().when(stageDirectorRepository).addMovie(anyString(), anyString());

        mockMvc.perform(post("/api/stagedirector/1/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"2\""))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAward() throws Exception {
        doNothing().when(stageDirectorRepository).addAward(anyString(), anyString());

        mockMvc.perform(post("/api/stagedirector/1/awards")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"3\""))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAward() throws Exception {
        doNothing().when(stageDirectorRepository).deleteAward(anyString(), anyString());

        mockMvc.perform(delete("/api/stagedirector/1/awards/3"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindAllDirectors() throws Exception {
        StageDirector director1 = new StageDirector();
        director1.setFirstName("John");
        director1.setLastName("Doe");

        StageDirector director2 = new StageDirector();
        director2.setFirstName("Jane");
        director2.setLastName("Smith");

        Vector<StageDirector> directors = new Vector<>(Arrays.asList(director1, director2));

        when(stageDirectorRepository.getDirectorsFromTable()).thenReturn(directors);

        mockMvc.perform(get("/api/stagedirector"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));
    }
}

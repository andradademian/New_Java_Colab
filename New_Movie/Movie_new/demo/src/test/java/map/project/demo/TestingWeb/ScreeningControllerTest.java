package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.ScreeningController;
import map.project.demo.Domain.*;
import map.project.demo.Repository.IScreeningRepo;
import map.project.demo.Strategy.Screening;
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
import java.sql.Time;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ScreeningControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IScreeningRepo screeningRepository;

    @Autowired
    private ScreeningController screeningController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddScreening4DX() throws Exception {
        Screening4DX screening4DX = new Screening4DX();
        Room room1 = new Room("1", 5, 50);
        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());

        screening4DX.setRoom(room1);
        screening4DX.setStartTime(Time.valueOf("12:00:00"));
        screening4DX.setMovie(movie1);
        screening4DX.setId("KX");

        screeningController.addScreening4DX(screening4DX);
        Screening screening = screeningController.findScreeningById("KX");
        assertEquals(screening.getId(), "KX");
        screeningRepository.deleteById("KX");
    }

    @Test
    void testAddScreening3D() throws Exception {
        Screening3D screening3D = new Screening3D();
        Room room1 = new Room("1", 5, 50);
        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());

        screening3D.setRoom(room1);
        screening3D.setStartTime(Time.valueOf("14:00:00"));
        screening3D.setMovie(movie1);
        screening3D.setId("KX");

        screeningController.addScreening3D(screening3D);
        Screening screening3D1 = screeningController.findScreeningById("KX");
        assertEquals(screening3D1.getId(), "KX");

        screeningRepository.deleteById("KX");
    }

    @Test
    void testAddScreening2D() throws Exception {
        Screening2D screening2D = new Screening2D();
        Room room1 = new Room("1", 5, 50);
        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());

        screening2D.setRoom(room1);
        screening2D.setStartTime(Time.valueOf("16:00:00"));
        screening2D.setMovie(movie1);
        screening2D.setId("KX");

        screeningController.addScreening(screening2D);
        Screening screening2D1 = screeningController.findScreeningById("KX");
        assertEquals(screening2D1.getId(), "KX");
        screeningRepository.deleteById("KX");
    }

    @Test
    void testDeleteSpectator() throws Exception {
        Screening2D screening2D = new Screening2D();
        Room room1 = new Room("1", 5, 50);
        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());

        screening2D.setRoom(room1);
        screening2D.setStartTime(Time.valueOf("16:00:00"));
        screening2D.setMovie(movie1);
        screening2D.setId("KX");

        mockMvc.perform(post("/api/screening/2D")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(screening2D)));

        mockMvc.perform(delete("/api/screening/{id}", "KX"))
                .andExpect(status().isOk());

        assertFalse(screeningRepository.existsById("KX"));
    }

}

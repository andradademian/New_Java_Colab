package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.ActorController;
import map.project.demo.Controller.CinemaController;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Cinema;
import map.project.demo.Repository.IActorRepository;
import map.project.demo.Repository.ICinemaRepo;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ICinemaRepo cinemaRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddCinema() throws Exception {
        Cinema cinema = new Cinema("1KX", "Cinema City", "Str. Unirii", new Vector<>());

        mockMvc.perform(post("/api/cinema")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinema)))
                .andExpect(status().isOk());

        Optional<Cinema> savedCinema = cinemaRepository.findById("1KX");
        assertTrue(savedCinema.isPresent());

        Cinema retrievedCinema = savedCinema.get();
        assertEquals("1KX", retrievedCinema.getId());

        cinemaRepository.deleteById("1KX");
    }

    @Test
    void testFindCinemaById() throws Exception {
        Cinema cinema = new Cinema("1KX", "Cinema City", "Str. Unirii", new Vector<>());

        mockMvc.perform(post("/api/cinema")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinema)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/cinema/1KX"))
                .andExpect(status().isOk());

        cinemaRepository.deleteById("1KX");
    }

    @Test
    void testDeleteCinemaWithId() throws Exception {
        Cinema cinema = new Cinema();
        cinema.setId("1KX");

        mockMvc.perform(post("/api/cinema")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinema)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/cinema/{id}", "1KX"))
                .andExpect(status().isOk());

        assertFalse(cinemaRepository.existsById("1KX"));
    }


}

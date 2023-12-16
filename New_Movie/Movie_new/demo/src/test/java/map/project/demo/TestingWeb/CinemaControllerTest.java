package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.ActorController;
import map.project.demo.Controller.CinemaController;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Repository.CinemaRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CinemaController.class)
public class CinemaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CinemaRepository cinemaRepository;

    @InjectMocks
    private CinemaController cinemaController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddCinema() throws Exception {
        Cinema cinema = new Cinema("1KX", "Cinema City", "Str. Unirii", new Vector<>());

        doNothing().when(cinemaRepository).addCinemaToTable(any(Cinema.class));

        mockMvc.perform(post("/api/cinema")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cinema)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindCinemaById() throws Exception {
        Cinema cinema = new Cinema("1KX", "Cinema City", "Str. Unirii", new Vector<>());

        when(cinemaRepository.getCinemaWithIdFromTable("1")).thenReturn(cinema);

        mockMvc.perform(get("/api/cinema/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cinema City"))
                .andExpect(jsonPath("$.address").value("Str. Unirii"));
    }

    @Test
    void testDeleteCinemaWithId() throws Exception {
        doNothing().when(cinemaRepository).deleteCinemaWithIdFromTable(anyString());
        mockMvc.perform(delete("/api/cinema/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllCinemas() throws Exception {
        doNothing().when(cinemaRepository).deleteAllCinemasFromTable();

        mockMvc.perform(delete("/api/cinema"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCinemas() throws Exception {
        Cinema cinema1 = new Cinema("1KX", "Cinema City", "Str. Unirii", new Vector<>());
        Cinema cinema2 = new Cinema("2KX", "Cinema Republica", "Piata Mihai Viteazu", new Vector<>());

        Vector<Cinema> cinemas = new Vector<>(Arrays.asList(cinema1, cinema2));

        when(cinemaRepository.getCinemasFromTable()).thenReturn(cinemas);

        mockMvc.perform(get("/api/cinema"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Cinema City"))
                .andExpect(jsonPath("$[1].name").value("Cinema Republica"));
    }
}

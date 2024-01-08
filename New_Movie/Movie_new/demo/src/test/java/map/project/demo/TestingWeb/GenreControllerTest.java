package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.GenreController;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Genre;
import map.project.demo.Repository.IGenreRepo;
import org.junit.jupiter.api.Test;
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
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IGenreRepo genreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddGenre() throws Exception {
        Genre genre = new Genre();
        genre.setId("1AB");
        genre.setName("Drama");

        mockMvc.perform(post("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(genre)))
                .andExpect(status().isOk());

        Optional<Genre> savedGenre = genreRepository.findById("1AB");
        assertTrue(savedGenre.isPresent());

        Genre retrievedGenre = savedGenre.get();
        assertEquals("1AB", retrievedGenre.getId());

        genreRepository.deleteById("1AB");
    }

    @Test
    void testFindGenreById() throws Exception {
        Genre genre = new Genre();
        genre.setId("1AB");
        genre.setName("Drama");

        mockMvc.perform(post("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(genre)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/genre/1AB"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Drama"));

        genreRepository.deleteById("1AB");


    }

    @Test
    void testDeleteGenreWithIdFromTable() throws Exception {
        Genre genre = new Genre();
        genre.setId("1");

        mockMvc.perform(post("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(genre)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/genre/{id}", "1"))
                .andExpect(status().isOk());

        assertFalse(genreRepository.existsById("1"));
        genreRepository.deleteById("1");
    }

}

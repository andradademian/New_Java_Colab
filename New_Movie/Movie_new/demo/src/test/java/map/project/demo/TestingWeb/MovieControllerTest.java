package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.MovieController;
import map.project.demo.Domain.Genre;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.IMovieRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IMovieRepo movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId("1AB");
        movie.setTitle("Titanic");

        mockMvc.perform(post("/api/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());

        Optional<Movie> savedMovie = movieRepository.findById("1AB");
        assertTrue(savedMovie.isPresent());

        Movie retrievedMovie = savedMovie.get();
        assertEquals("1AB", retrievedMovie.getId());

        movieRepository.deleteById("1AB");
    }

    @Test
    void testGetMovieWithId() throws Exception {
        Movie movie = new Movie();
        movie.setId("1AB");
        movie.setTitle("The Shawshank Redemption");

        mockMvc.perform(post("/api/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/movie/1AB"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Shawshank Redemption"));

        movieRepository.deleteById("1AB");


    }

    @Test
    void testDeleteMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId("1AB");

        mockMvc.perform(post("/api/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/movie/{id}", "1AB"))
                .andExpect(status().isOk());

        assertFalse(movieRepository.existsById("1AB"));
        movieRepository.deleteById("1AB");
    }

}

package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.MovieController;
import map.project.demo.Domain.Genre;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieRepository movieRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddActor() throws Exception {
        Movie movie = new Movie();
        movie.setId("1");
        movie.setTitle("Titanic");

        doNothing().when(movieRepository).addMovieToTable(any(Movie.class));

        mockMvc.perform(post("/api/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(movie)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetMovieWithId() throws Exception {
        Movie movie = new Movie();
        movie.setId("1");
        movie.setTitle("The Shawshank Redemption");

        when(movieRepository.getMovieWithIdFromTable("1")).thenReturn(movie);

        mockMvc.perform(get("/api/movie/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("The Shawshank Redemption"));
    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(movieRepository).deleteMovieWithIdFromTable(anyString());

        mockMvc.perform(delete("/api/movie/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllMovies() throws Exception {
        doNothing().when(movieRepository).deleteAllFromMovieTable();

        mockMvc.perform(delete("/api/movie"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllMovies() throws Exception {
        Movie movie1 = new Movie();
        movie1.setId("1");
        movie1.setTitle("The Dark Knight");

        Movie movie2 = new Movie();
        movie2.setId("2");
        movie2.setTitle("Pulp Fiction");

        Vector<Movie> movies = new Vector<>(Arrays.asList(movie1, movie2));

        when(movieRepository.getMoviesFromTable()).thenReturn(movies);

        mockMvc.perform(get("/api/movie"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("The Dark Knight"))
                .andExpect(jsonPath("$[1].title").value("Pulp Fiction"));
    }

    @Test
    void testDeleteStageDirectorFromMovie() throws Exception {
        doNothing().when(movieRepository).deleteDirectorFromMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/movie/{movieId}/directors/{directorId}", "1", "director123"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddStageDirectorToMovie() throws Exception {
        doNothing().when(movieRepository).addDirectorToMovie(anyString(), anyString());

        mockMvc.perform(post("/api/movie/{movieId}/directors", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"director123\""))
                .andExpect(status().isOk());
    }

    @Test
    void testAddGenreToMovie() throws Exception {
        doNothing().when(movieRepository).addGenreToMovie(anyString(), anyString());

        mockMvc.perform(post("/api/movie/{movieId}/genres", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Action\""))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteGenreFromMovie() throws Exception {
        doNothing().when(movieRepository).deleteGenreFromMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/movie/{movieId}/genres/{genreId}", "1", "genre123"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddActorToMovie() throws Exception {
        doNothing().when(movieRepository).addActorToMovie(anyString(), anyString());

        mockMvc.perform(post("/api/movie/{movieId}/actors", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"actor123\""))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteActorFromMovie() throws Exception {
        doNothing().when(movieRepository).deleteActorFromMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/movie/{movieId}/actors/{actorId}", "1", "actor123"))
                .andExpect(status().isOk());
    }
}

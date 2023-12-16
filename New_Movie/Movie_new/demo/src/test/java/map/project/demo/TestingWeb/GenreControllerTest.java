package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.GenreController;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Genre;
import map.project.demo.Repository.GenreRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.mockito.Mockito.when;

@WebMvcTest(GenreController.class)
public class GenreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreRepository genreRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddActor() throws Exception {
        Genre genre = new Genre();
        genre.setId("1");
        genre.setName("Drama");

        doNothing().when(genreRepository).addGenreToTable(any(Genre.class));

        mockMvc.perform(post("/api/genre")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(genre)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindGenreById() throws Exception {
        Genre genre = new Genre();
        genre.setId("1");
        genre.setName("Drama");

        when(genreRepository.getGenreWithIdFromTable("1")).thenReturn(genre);

        mockMvc.perform(get("/api/genre/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Drama"));
    }

    @Test
    void testDeleteGenreWithIdFromTable() throws Exception {
        doNothing().when(genreRepository).deleteGenreWithIdFromTable(anyString());

        mockMvc.perform(delete("/api/genre/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllGenres() throws Exception {
        doNothing().when(genreRepository).deleteAllFromGenreTable();

        mockMvc.perform(delete("/api/genre"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllFromMovieGenreTable() throws Exception {
        doNothing().when(genreRepository).deleteAllMoviesFromGenreWithId(anyString());

        mockMvc.perform(delete("/api/genre/{id}/movies", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(genreRepository).deleteMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/genre/{genreId}/movies/{movieId}", "1", "Movie1"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddMovie() throws Exception {
        doNothing().when(genreRepository).addMovie(anyString(), anyString());

        mockMvc.perform(post("/api/genre/{genreId}/movies", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"Movie1\""))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllGenres() throws Exception {
        Genre genre1 = new Genre();
        genre1.setId("1");
        genre1.setName("Action");

        Genre genre2 = new Genre();
        genre2.setId("2");
        genre2.setName("Comedy");

        Vector<Genre> genres = new Vector<>(Arrays.asList(genre1, genre2));

        when(genreRepository.getGenresFromTable()).thenReturn(genres);

        mockMvc.perform(get("/api/genre"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Action"))
                .andExpect(jsonPath("$[1].name").value("Comedy"));
    }
}

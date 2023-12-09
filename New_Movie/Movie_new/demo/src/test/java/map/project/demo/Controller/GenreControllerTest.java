package map.project.demo.Controller;

import map.project.demo.Controller.GenreController;
import map.project.demo.Domain.Genre;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

public class GenreControllerTest {
    GenreRepository genreRepository = new GenreRepository();
    GenreController genreController = new GenreController();

    public GenreControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() {
        genreRepository.add(new Genre("1", "Action", new Vector<String>()));
        genreRepository.add(new Genre("2", "Drama", new Vector<String>()));
        genreRepository.add(new Genre("3", "Comedy", new Vector<String>()));
    }


    @Test
    public void shouldFindGenreById() throws SQLException {
        String genreId = "2";

        Genre foundGenre = genreController.findGenreById(genreId);

        assertNotNull(foundGenre);
        assertEquals(genreId, foundGenre.getId());
    }

    @Test
    public void shouldNotFindInvalidGenreById() throws SQLException {
        String invalidGenreId = "InvalidId";

        Genre foundGenre = genreController.findGenreById(invalidGenreId);

        assertNull(foundGenre);
    }

    @Test
    public void expectCorrectFindingOfTheMovieById() throws SQLException {
        genreRepository.addMovie(String.valueOf(genreController.getAllGenres().get(0)), "111");
        boolean movieExists = genreController.findMovieById(genreController.getAllGenres().get(0), "111");
        assertTrue(movieExists);
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() throws SQLException {
        genreRepository.addMovie(String.valueOf(genreController.getAllGenres().get(0)), "111");
        boolean movieExists = genreController.findMovieById(genreController.getAllGenres().get(0), "112");
        assertFalse(movieExists);
    }

}

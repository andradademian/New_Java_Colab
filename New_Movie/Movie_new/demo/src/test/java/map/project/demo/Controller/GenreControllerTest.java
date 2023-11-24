package map.project.demo.Controller;

import map.project.demo.Controller.GenreController;
import map.project.demo.Domain.Genre;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.GenreRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class GenreControllerTest {
    GenreRepository genreRepository = GenreRepository.getInstance();
    GenreController genreController = new GenreController(genreRepository);

    @BeforeEach
    public void setUp() {
        genreRepository.add(new Genre("1", "Action", new Vector<Movie>()));
        genreRepository.add(new Genre("2", "Drama", new Vector<Movie>()));
        genreRepository.add(new Genre("3", "Comedy", new Vector<Movie>()));
    }


    @Test
    public void shouldFindGenreById() {
        String genreId = "2";

        Genre foundGenre = genreController.findGenreById(genreId);

        assertNotNull(foundGenre);
        assertEquals(genreId, foundGenre.getId());
    }

    @Test
    public void shouldNotFindInvalidGenreById() {
        String invalidGenreId = "InvalidId";

        Genre foundGenre = genreController.findGenreById(invalidGenreId);

        assertNull(foundGenre);
    }

    @Test
    public void expectCorrectFindingOfTheMovieById() {
        genreRepository.addMovie(genreController.getAllGenres().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = genreController.findMovieById(genreController.getAllGenres().get(0), "111");
        assertEquals(movie.getId(), "111");
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() {
        genreRepository.addMovie(genreController.getAllGenres().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        genreRepository.addMovie(genreController.getAllGenres().get(0), new Movie("112", "Movie2", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = genreController.findMovieById(genreController.getAllGenres().get(0), "112");
        assertNotEquals(movie.getId(), "111");
    }

}

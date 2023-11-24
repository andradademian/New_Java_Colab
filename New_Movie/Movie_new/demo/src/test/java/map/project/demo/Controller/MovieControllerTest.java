package map.project.demo.Controller;

import map.project.demo.Controller.MovieController;
import map.project.demo.Domain.*;
import map.project.demo.Repository.MovieRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class MovieControllerTest {
    MovieRepository movieRepository = MovieRepository.getInstance();
    MovieController movieController = new MovieController(movieRepository);

    @BeforeEach
    public void setUp() {
        movieRepository.add(new Movie("1", "Movie1", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        movieRepository.add(new Movie("2", "Movie2", 150, new Vector<>(), new Vector<>(), new Vector<>()));
        movieRepository.add(new Movie("3", "Movie3", 110, new Vector<>(), new Vector<>(), new Vector<>()));
    }

    @Test
    public void shouldFindMovieById() {
        // Arrange
        String movieId = "1";

        // Act
        Movie foundMovie = movieController.findMovieById(movieId);

        // Assert
        assertNotNull(foundMovie);
        assertEquals(movieId, foundMovie.getId());
    }

    @Test
    public void shouldNotFindMovieByInvalidId() {
        // Arrange
        String invalidMovieId = "InvalidId";

        // Act
        Movie foundMovie = movieController.findMovieById(invalidMovieId);

        // Assert
        assertNull(foundMovie);
    }

    @Test
    public void shouldFindDirectorById() {
        //public StageDirector(String id, String firstName, String lastName, Vector<Movie> listOfMovies, Vector<Award> awards) {
        Movie movie = movieController.getAllMovies().get(0);
        StageDirector stageDirector = new StageDirector("1", "FirstName1", "LastName1", new Vector<Movie>(), new Vector<Award>());
        movieRepository.addStageDirector(movie, stageDirector);

        StageDirector foundDirector = movieController.findDirectorById(movie, "1");

        assertNotNull(foundDirector);
        assertEquals("1", foundDirector.getId());
    }

    @Test
    public void shouldNotFindDirectorByInvalidId() {
        Movie movie = movieController.getAllMovies().get(0);
        StageDirector stageDirector = new StageDirector("1", "FirstName1", "LastName1", new Vector<Movie>(), new Vector<Award>());
        movieRepository.addStageDirector(movie, stageDirector);

        StageDirector foundDirector = movieController.findDirectorById(movie, "2");

        assertNull(foundDirector);
    }

    @Test
    public void shouldFindActorById() {
        Movie movie = movieController.getAllMovies().get(0);
        Actor actor = new Actor("1", "FirstName1", "LastName1", new Vector<>(), null, new Vector<Award>());
        movieRepository.addActor(movie, actor);

        Actor foundActor = movieController.findActorById(movie, "1");

        assertNotNull(foundActor);
        assertEquals("1", foundActor.getId());
    }

    @Test
    public void shouldNotFindActorByInvalidId() {
        Movie movie = movieController.getAllMovies().get(0);
        Actor actor = new Actor("1", "FirstName1", "LastName1", new Vector<>(), null, new Vector<Award>());
        movieRepository.addActor(movie, actor);

        Actor foundActor = movieController.findActorById(movie, "2");

        assertNull(foundActor);
    }

    @Test
    public void shouldFindGenreById() {
        Movie movie = movieController.getAllMovies().get(0);
        Genre genre = new Genre("1", "Action", new Vector<>());
        movieRepository.addGenre(movie, genre);

        Genre foundGenre = movieController.findGenreById(movie, "1");

        assertNotNull(foundGenre);
        assertEquals("1", foundGenre.getId());
    }

    @Test
    public void shouldNotFindGenreByInvalidId() {
        Movie movie = movieController.getAllMovies().get(0);
        Genre genre = new Genre("1", "Action", new Vector<>());
        movieRepository.addGenre(movie, genre);

        Genre foundGenre = movieController.findGenreById(movie, "2");

        assertNull(foundGenre);
    }

}

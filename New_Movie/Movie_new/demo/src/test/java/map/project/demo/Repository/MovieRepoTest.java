package map.project.demo.Repository;

import map.project.demo.Domain.*;

import map.project.demo.Repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class MovieRepoTest {
    MovieRepository movieRepository;

    @BeforeEach
    public void setUp() {
        movieRepository = MovieRepository.getInstance();
    }

    @Test
    public void expectMovieAddedSuccessfully() {
        movieIsAddedToTheList();
        assertEquals(movieRepository.getAll().size(), 1);
    }

    @Test
    public void expectMovieNotAddedSuccessfully() {
        movieIsAddedToTheList();
        assertNotEquals(movieRepository.getAll().size(), 0);
    }

    @Test
    public void expectMovieRemovedSuccessfully() {
        movieIsAddedToTheList();
        Movie movie = movieRepository.getAll().get(0);
        movieRepository.delete(movie);
        assertEquals(movieRepository.getAll().size(), 0);
    }

    @Test
    public void expectMovieNotRemovedSuccessfully() {
        movieIsAddedToTheList();
        Movie movie = movieRepository.getAll().get(0);
        movieRepository.delete(movie);
        assertNotEquals(movieRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllMovie() {
        movieIsAddedToTheList();
        movieRepository.deleteAll();
        assertEquals(movieRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllMovie() {
        movieIsAddedToTheList();
        movieRepository.deleteAll();
        assertNotEquals(movieRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfTheName() {
        movieIsAddedToTheList();
        movieRepository.updateTitle(movieRepository.getAll().get(0), "Other Title");
        String expected = "Other Title";
        String actual = movieRepository.getAll().get(0).getTitle();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheName() {
        movieIsAddedToTheList();
        movieRepository.updateTitle(movieRepository.getAll().get(0), "Other Title");
        String expected = "Title";
        String actual = movieRepository.getAll().get(0).getTitle();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectUpdateOfTheDuration() {
        movieIsAddedToTheList();
        movieRepository.updateDuration(movieRepository.getAll().get(0), 120);
        int expected = 120;
        int actual = movieRepository.getAll().get(0).getDurationInMinutes();
        assertEquals(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheDuration() {
        movieIsAddedToTheList();
        movieRepository.updateDuration(movieRepository.getAll().get(0), 120);
        int expected = 121;
        int actual = movieRepository.getAll().get(0).getDurationInMinutes();
        assertNotEquals(actual, expected);
    }

    @Test
    public void expectCorrectAddingOfTheGenre() {
        movieIsAddedToTheList();
        genreIsAddedToTheMovie();
        assertEquals(movieRepository.getAll().get(0).getGenres().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheGenre() {
        movieIsAddedToTheList();
        genreIsAddedToTheMovie();
        assertNotEquals(movieRepository.getAll().get(0).getGenres().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheGenre() {
        movieIsAddedToTheList();
        genreIsAddedToTheMovie();
        movieRepository.deleteGenre(movieRepository.getAll().get(0), movieRepository.getAll().get(0).getGenres().get(0));
        assertEquals(movieRepository.getAll().get(0).getGenres().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheGenre() {
        movieIsAddedToTheList();
        genreIsAddedToTheMovie();
        movieRepository.deleteGenre(movieRepository.getAll().get(0), movieRepository.getAll().get(0).getGenres().get(0));
        assertNotEquals(movieRepository.getAll().get(0).getGenres().size(), 1);
    }

    @Test
    public void expectCorrectAddingOfTheDirector() {
        movieIsAddedToTheList();
        stageDirectorIsAddedToTheMovie();
        assertEquals(movieRepository.getAll().get(0).getStageDirectors().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheDirector() {
        movieIsAddedToTheList();
        stageDirectorIsAddedToTheMovie();
        assertNotEquals(movieRepository.getAll().get(0).getStageDirectors().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheDirector() {
        movieIsAddedToTheList();
        stageDirectorIsAddedToTheMovie();
        movieRepository.deleteStageDirector(movieRepository.getAll().get(0), movieRepository.getAll().get(0).getStageDirectors().get(0));
        assertEquals(movieRepository.getAll().get(0).getStageDirectors().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheDirector() {
        movieIsAddedToTheList();
        stageDirectorIsAddedToTheMovie();
        movieRepository.deleteStageDirector(movieRepository.getAll().get(0), movieRepository.getAll().get(0).getStageDirectors().get(0));
        assertNotEquals(movieRepository.getAll().get(0).getStageDirectors().size(), 1);
    }

    public void movieIsAddedToTheList() {
        movieRepository.deleteAll();
        Movie movie = new Movie("1", "Title", 120, new Vector<>(), new Vector<>(), new Vector<>());
        movieRepository.add(movie);
    }

    public void genreIsAddedToTheMovie() {
        Genre genre = new Genre("1", "Title", new Vector<>());
        movieRepository.addGenre(movieRepository.getAll().get(0), genre);
    }

    public void stageDirectorIsAddedToTheMovie() {
        StageDirector stageDirector = new StageDirector("1", "Mel", "Gibson", new Vector<>(), new Vector<>());
        movieRepository.addStageDirector(movieRepository.getAll().get(0), stageDirector);
    }
}

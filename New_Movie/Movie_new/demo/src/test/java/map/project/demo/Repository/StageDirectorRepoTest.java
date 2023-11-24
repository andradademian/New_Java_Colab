package map.project.demo.Repository;

import map.project.demo.Domain.*;
import map.project.demo.Repository.StageDirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class StageDirectorRepoTest {
    StageDirectorRepository stageDirectorRepository;

    @BeforeEach
    public void setUp() {
        stageDirectorRepository = StageDirectorRepository.getInstance();
    }

    @Test
    public void expectStageDirectorAddedSuccessfully() {
        stageDirectorIsAddedToTheList();
        assertEquals(stageDirectorRepository.getAll().size(), 1);
    }

    @Test
    public void expectStageDirectorNotAddedSuccessfully() {
        stageDirectorIsAddedToTheList();
        assertNotEquals(stageDirectorRepository.getAll().size(), 0);
    }

    @Test
    public void expectStageDirectorRemovedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        stageDirectorRepository.delete(stageDirector);
        assertEquals(stageDirectorRepository.getAll().size(), 0);
    }

    @Test
    public void expectStageDirectorNotRemovedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        stageDirectorRepository.delete(stageDirector);
        assertNotEquals(stageDirectorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllStageDirectors() {
        stageDirectorIsAddedToTheList();
        stageDirectorRepository.deleteAll();
        assertEquals(stageDirectorRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllStageDirectors() {
        stageDirectorIsAddedToTheList();
        stageDirectorRepository.deleteAll();
        assertNotEquals(stageDirectorRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdateFirstNameSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        String newFirstName = "NewFirstName";
        stageDirectorRepository.updateFirstName(stageDirector, newFirstName);
        assertEquals(stageDirector.getFirstName(), newFirstName);
    }

    @Test
    public void expectUpdateLastNameSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        String newLastName = "NewLastName";
        stageDirectorRepository.updateLastName(stageDirector, newLastName);
        assertEquals(stageDirector.getLastName(), newLastName);
    }

    @Test
    public void expectMovieAddedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        Movie movie = createMovie();
        stageDirectorRepository.addMovie(stageDirector, movie);
        assertTrue(stageDirector.getListOfMovies().contains(movie), "Movie was not added to the stage director");
    }

    @Test
    public void expectMovieNotAddedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        Movie movie = createMovie();
        assertTrue(stageDirector.getListOfMovies().isEmpty(), "Movies should be empty initially");
    }

    @Test
    public void expectMovieRemovedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        Movie movie = createMovie();
        stageDirector.addMovie(movie);
        stageDirectorRepository.deleteMovie(stageDirector, movie);
        assertFalse(stageDirector.getListOfMovies().contains(movie), "Movie was not removed from the stage director");
    }

    @Test
    public void expectMovieNotRemovedSuccessfully() {
        stageDirectorIsAddedToTheList();
        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
        Movie movie = createMovie();
        stageDirectorRepository.deleteMovie(stageDirector, movie);
        assertFalse(stageDirector.getListOfMovies().contains(movie), "Movies should be empty initially");
    }

    public void stageDirectorIsAddedToTheList() {
        stageDirectorRepository.deleteAll();
        StageDirector stageDirector = new StageDirector("1", "FirstName", "LastName", new Vector<>(), new Vector<>());
        stageDirectorRepository.add(stageDirector);
    }

    public Movie createMovie() {
        return new Movie("1", "MovieTitle", 90, new Vector<>(), new Vector<>(), new Vector<>());
    }
}

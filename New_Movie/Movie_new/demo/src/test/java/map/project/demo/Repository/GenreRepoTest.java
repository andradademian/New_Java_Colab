package map.project.demo.Repository;

import map.project.demo.Domain.*;

import map.project.demo.Repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class GenreRepoTest {
    GenreRepository genreRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        genreRepository = GenreRepository.getInstance();
    }

    @Test
    public void expectGenreAddedSuccessfully() {
        genreIsAddedToTheList();
        assertEquals(genreRepository.getAll().size(), 1);
    }

    @Test
    public void expectGenreNotAddedSuccessfully() {
        genreIsAddedToTheList();
        assertNotEquals(genreRepository.getAll().size(), 0);
    }

    @Test
    public void expectGenreRemovedSuccessfully() {
        genreIsAddedToTheList();
        Genre genre = genreRepository.getAll().get(0);
        genreRepository.delete(genre);
        assertEquals(genreRepository.getAll().size(), 0);
    }

    @Test
    public void expectGenreNotRemovedSuccessfully() {
        genreIsAddedToTheList();
        Genre genre = genreRepository.getAll().get(0);
        genreRepository.delete(genre);
        assertNotEquals(genreRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllGenres() {
        genreIsAddedToTheList();
        genreRepository.deleteAll();
        assertEquals(genreRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllGenres() {
        genreIsAddedToTheList();
        genreRepository.deleteAll();
        assertNotEquals(genreRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfTheName() {
        genreIsAddedToTheList();
        genreRepository.updateName(genreRepository.getAll().get(0), "Other Name");
        String expected = "Other Name";
        String actual = genreRepository.getAll().get(0).getName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheName() {
        genreIsAddedToTheList();
        genreRepository.updateName(genreRepository.getAll().get(0), "Other Name");
        String expected = "Name";
        String actual = genreRepository.getAll().get(0).getName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectAddingOfTheMovie() {
        genreIsAddedToTheList();
        movieIsAddedToTheGenre();
        assertEquals(genreRepository.getAll().get(0).getListOfMovies().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheMovie() {
        genreIsAddedToTheList();
        movieIsAddedToTheGenre();
        assertNotEquals(genreRepository.getAll().get(0).getListOfMovies().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheMovie() {
        genreIsAddedToTheList();
        movieIsAddedToTheGenre();
        genreRepository.deleteMovie(genreRepository.getAll().get(0), genreRepository.getAll().get(0).getListOfMovies().get(0));
        assertEquals(genreRepository.getAll().get(0).getListOfMovies().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheMovie() {
        genreIsAddedToTheList();
        movieIsAddedToTheGenre();
        genreRepository.deleteMovie(genreRepository.getAll().get(0), genreRepository.getAll().get(0).getListOfMovies().get(0));
        assertNotEquals(genreRepository.getAll().get(0).getListOfMovies().size(), 1);
    }

    public void genreIsAddedToTheList() {
        genreRepository.deleteAll();
        Genre genre = new Genre("1", "Comedy", new Vector<>());
        genreRepository.add(genre);
    }

    public void movieIsAddedToTheGenre() {
        Movie movie = new Movie("1", "Title", 120, new Vector<StageDirector>(), new Vector<Actor>(), new Vector<Genre>());
        genreRepository.addMovie(genreRepository.getAll().get(0), movie);
    }
}

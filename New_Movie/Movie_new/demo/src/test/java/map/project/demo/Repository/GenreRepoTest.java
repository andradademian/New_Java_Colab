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
        genreRepository = new GenreRepository();
    }

    @Test
    public void expectGenreAddedSuccessfully() throws SQLException {
        int size = genreRepository.getGenresFromTable().size();
        genreIsAddedToTheList();
        assertEquals(genreRepository.getGenresFromTable().size(), size + 1);
        genreRepository.deleteGenreWithIdFromTable("1K");
    }

    @Test
    public void expectGenreNotAddedSuccessfully() throws SQLException {
        int size = genreRepository.getGenresFromTable().size();
        genreIsAddedToTheList();
        assertNotEquals(genreRepository.getGenresFromTable().size(), size);
        genreRepository.deleteGenreWithIdFromTable("1K");
    }

    @Test
    public void expectGenreRemovedSuccessfully() throws SQLException {
        genreIsAddedToTheList();
        int size = genreRepository.getGenresFromTable().size();
        genreRepository.deleteGenreWithIdFromTable("1K");
        assertEquals(genreRepository.getGenresFromTable().size(), size - 1);
    }

    @Test
    public void expectGenreNotRemovedSuccessfully() throws SQLException {
        genreIsAddedToTheList();
        int size = genreRepository.getGenresFromTable().size();
        genreRepository.deleteGenreWithIdFromTable("1K");
        assertNotEquals(genreRepository.getGenresFromTable().size(), size);
    }

    @Test
    public void expectCorrectRemovalOfAllGenres() throws SQLException {
        genreIsAddedToTheList();
        genreRepository.deleteAll();
        assertEquals(genreRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllGenres() throws SQLException {
        genreIsAddedToTheList();
        genreRepository.deleteAll();
        assertNotEquals(genreRepository.getGenresFromTable().size(), 1);
        genreRepository.deleteGenreWithIdFromTable("1K");
    }

    public void genreIsAddedToTheList() throws SQLException {
        genreRepository.deleteAll();
        Genre genre = new Genre("1K", "Comedy", new Vector<>());
        genreRepository.addGenreToTable(genre);
    }

    public void movieIsAddedToTheGenre() throws SQLException {
        Movie movie = new Movie("1K", "Title", 120, new Vector<String>(), new Vector<String>(), new Vector<String>());
        genreRepository.addMovie("1K", movie.getId());
    }
}

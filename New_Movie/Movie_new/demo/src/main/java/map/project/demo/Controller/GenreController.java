package map.project.demo.Controller;

import map.project.demo.Domain.*;

import map.project.demo.Repository.GenreRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class GenreController {
    private final GenreRepository genreRepo;

    public GenreController(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    public void addGenre(Genre genre) {
        genreRepo.add(genre);
    }

    public void deleteGenre(Genre genre) {
        genreRepo.delete(genre);
    }

    public Genre findGenreById(String id) {
        for (Genre genre : genreRepo.getAll()) {
            if (genre.getId().equals(id)) {
                return genre;
            }
        }
        System.out.println("No genre with that ID");
        return null;
    }

    public void deleteGenre(String genreId) {
        Genre genre = findGenreById(genreId);
        if (genre != null) {
            genreRepo.delete(genre);
        }
    }

    public Movie findMovieById(Genre genre, String id) {
        for (Movie movie : genre.getListOfMovies()) {
            if (Objects.equals(movie.getId(), id)) {
                return movie;
            }
        }
        System.out.println("No movie with that id");
        return null;
    }

    public Vector<String> getMoviesFromMovieGenreTable(Genre genre) throws SQLException {
        return genreRepo.getMoviesFromMovieGenreTable(genre.getId());
    }

    public void deleteAllGenres() {
        genreRepo.deleteAll();
    }

    public void updateGenreName(Genre genre, String name) {
        genreRepo.updateName(genre, name);
    }

    public void deleteMovieFromGenre(Genre genre, Movie movie) {
        genreRepo.deleteMovie(genre, movie);
    }

    public void addMovieToGenre(Genre genre, Movie movie) {
        genreRepo.addMovie(genre, movie);
    }

    public Vector<Genre> getAllGenres() {
        return genreRepo.getAll();
    }

    public void printAllGenres() {
        genreRepo.printAll();
    }
}

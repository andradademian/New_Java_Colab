package map.project.demo.Repository;

import map.project.demo.*;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Genre;

import java.util.Vector;

public class GenreRepository {
    private static GenreRepository instance;
    private final Vector<Genre> genres;

    public GenreRepository() {
        genres = new Vector<>();
    }
    public static GenreRepository getInstance() {
        if (instance == null) {
            instance = new GenreRepository();
        }
        return instance;
    }
    public void add(Genre genre) {
        genres.add(genre);
    }

    public void delete(Genre genre) {
        genres.remove(genre);
    }

    public void deleteAll() {
        genres.clear();
    }

    public void printAll() {
        System.out.println(genres);
    }

    public void updateName(Genre genre, String name) {
        genres.get(getAll().indexOf(genre)).setName(name);
    }

    public void deleteMovie(Genre genre, Movie movie) {
        genres.get(getAll().indexOf(genre)).deleteMovie(movie);
    }

    public void addMovie(Genre genre, Movie movie) {
        genres.get(getAll().indexOf(genre)).addMovie(movie);
    }

    public Vector<Genre> getAll() {
        return this.genres;
    }
}

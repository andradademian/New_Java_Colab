package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Genre;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;

import java.util.Vector;

public class MovieRepository {
    private static MovieRepository instance;
    private final Vector<Movie> movies;

    public MovieRepository() {
        movies = new Vector<>();
    }
    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }
    public void add(Movie movie) {
        movies.add(movie);
    }

    public void delete(Movie movie) {
        movies.remove(movie);
    }

    public void deleteAll() {
        movies.clear();
    }

    public void printAll() {
        System.out.println(movies);
    }

    public void updateTitle(Movie movie, String title) {
        movies.get(getAll().indexOf(movie)).setTitle(title);
    }

    public void updateDuration(Movie movie, int duration) {
        movies.get(getAll().indexOf(movie)).setDurationInMinutes(duration);
    }

    public void deleteStageDirector(Movie movie, StageDirector stageDirector) {
        movies.get(getAll().indexOf(movie)).deleteStageDirector(stageDirector);
    }

    public void addStageDirector(Movie movie, StageDirector stageDirector) {
        movies.get(getAll().indexOf(movie)).addStageDirector(stageDirector);
    }

    public void addActor(Movie movie, Actor actor) {
        movies.get(getAll().indexOf(movie)).addActor(actor);
    }

    public void deleteActor(Movie movie, Actor actor) {
        movies.get(getAll().indexOf(movie)).deleteActor(actor);
    }

    public void addGenre(Movie movie, Genre genre) {
        movies.get(getAll().indexOf(movie)).addGenre(genre);
    }

    public void deleteGenre(Movie movie, Genre genre) {
        movies.get(getAll().indexOf(movie)).deleteGenre(genre);
    }

    public Vector<Movie> getAll() {
        return this.movies;
    }
}

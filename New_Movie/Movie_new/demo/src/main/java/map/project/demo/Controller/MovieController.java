package map.project.demo.Controller;

import map.project.demo.Domain.*;

import map.project.demo.Repository.MovieRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class MovieController {
    private MovieRepository movieRepo;
    private final Vector<Movie> listOfMovies = new Vector<>();

    public MovieController(MovieRepository movieRepo) throws SQLException {
        this.movieRepo = movieRepo;
    }

    public Movie findMovieById(String id) {
        for (Movie movie : movieRepo.getAll()) {
            if (movie.getId().equals(id)) {
                return movie;
            }
        }
        System.out.println("No movie with that ID");
        return null;
    }

    public void deleteMovie(String id) {
        Movie movie = findMovieById(id);
        if (movie != null) {
            movieRepo.delete(movie);
        }
    }

    public Vector<String> getGenresFromMovieGenreTable(Movie movie) throws SQLException {
        return movieRepo.getGenresFromMovieGenreTable(movie.getId());
    }

    public Vector<String> getActorsFromActorMovieTable(Movie movie) throws SQLException {
        return movieRepo.getActorsFromActorMovieTable(movie.getId());
    }

    public Vector<String> getDirectorsFromMovieDirectorTable(Movie movie) throws SQLException {
        return movieRepo.getDirectorsFromMovieDirectorTable(movie.getId());
    }

    public StageDirector findDirectorById(Movie movie, String id) {
        for (StageDirector stageDirector : movie.getStageDirectors()) {
            if (Objects.equals(movie.getId(), id)) {
                return stageDirector;
            }
        }
        System.out.println("No stage director with that id");
        return null;
    }

    public Actor findActorById(Movie movie, String id) {
        for (Actor actor : movie.getActors()) {
            if (Objects.equals(movie.getId(), id)) {
                return actor;
            }
        }
        System.out.println("No actor with that id");
        return null;
    }

    public Genre findGenreById(Movie movie, String id) {
        for (Genre genre : movie.getGenres()) {
            if (Objects.equals(movie.getId(), id)) {
                return genre;
            }
        }
        System.out.println("No genre with that id");
        return null;
    }

    public void addMovie(Movie movie) {
        movieRepo.add(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepo.delete(movie);
    }

    public void deleteAllMovies() {
        movieRepo.deleteAll();
    }

    public void updateMovieTitle(Movie movie, String title) {
        movieRepo.updateTitle(movie, title);
    }

    public void updateMovieDuration(Movie movie, int duration) {
        movieRepo.updateDuration(movie, duration);
    }

    public void deleteStageDirectorFromMovie(Movie movie, StageDirector stageDirector) {
        movieRepo.deleteStageDirector(movie, stageDirector);
    }

    public void addStageDirectorToMovie(Movie movie, StageDirector stageDirector) {
        movieRepo.addStageDirector(movie, stageDirector);
    }

    public void addGenreToMovie(Movie movie, Genre genre) {
        movieRepo.addGenre(movie, genre);
    }

    public void deleteGenreFromMovie(Movie movie, Genre genre) {
        movieRepo.deleteGenre(movie, genre);
    }

    public void addActorToMovie(Movie movie, Actor actor) {
        movieRepo.addActor(movie, actor);
    }

    public void deleteActorFromMovie(Movie movie, Actor actor) {
        movieRepo.deleteActor(movie, actor);
    }

    public Vector<Movie> getAllMovies() {
        return movieRepo.getAll();
    }

    public void printAllMovies() {
        movieRepo.printAll();
    }

}

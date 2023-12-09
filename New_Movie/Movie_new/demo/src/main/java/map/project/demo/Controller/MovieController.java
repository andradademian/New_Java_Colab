package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.*;

import map.project.demo.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

@RestController
@RequestMapping("/api/movie")
@Getter
@Setter
@NoArgsConstructor
public class MovieController {
    @Autowired
    private MovieRepository movieRepo;

    @PostMapping
    public void addMovie(@RequestBody Movie movie) throws SQLException {
        movieRepo.addMovieToTable(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovieWithId(@PathVariable String id) throws SQLException {
        return movieRepo.getMovieWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) throws SQLException {
        movieRepo.deleteMovieWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllMovies() throws SQLException {
        movieRepo.deleteAllFromMovieTable();
    }

    @PutMapping("/{movieId}/title")
    public void updateMovieTitle(@PathVariable String movieId, @RequestBody String title) throws SQLException {
        movieRepo.updateTitleForMovieWithId(movieId, title);
    }

    @PutMapping("/{movieId}/duration")
    public void updateMovieDuration(@PathVariable String movieId, @RequestBody int duration) throws SQLException {
        movieRepo.updateDurationForMovieWithId(movieId, duration);
    }

    @DeleteMapping("/{movieId}/directors/{directorId}")
    public void deleteStageDirectorFromMovie(@PathVariable String movieId, @PathVariable String directorId) throws SQLException {
        movieRepo.deleteDirectorFromMovie(movieId, directorId);
    }

    @PostMapping("/{movieId}/directors")
    public void addStageDirectorToMovie(@PathVariable String movieId, @RequestBody String stageDirector) throws SQLException {
        movieRepo.addDirectorToMovie(movieId, stageDirector);
    }

    @PostMapping("/{movieId}/genres")
    public void addGenreToMovie(@PathVariable String movieId, @RequestBody String genre) throws SQLException {
        movieRepo.addGenreToMovie(movieId, genre);
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")
    public void deleteGenreFromMovie(@PathVariable String movieId, @PathVariable String genreId) throws SQLException {
        movieRepo.deleteGenreFromMovie(movieId, genreId);
    }

    @PostMapping("/{movieId}/actors")
    public void addActorToMovie(@PathVariable String movieId, @RequestBody String actor) throws SQLException {
        movieRepo.addActorToMovie(movieId, actor);
    }

    @DeleteMapping("/{movieId}/actors/{actorId}")
    public void deleteActorFromMovie(@PathVariable String movieId, @PathVariable String actorId) throws SQLException {
        movieRepo.deleteActorFromMovie(movieId, actorId);
    }

    @GetMapping
    public Vector<Movie> getAllMovies() throws SQLException {
        return movieRepo.getMoviesFromTable();
    }

}

package map.project.demo.Controller;

import map.project.demo.Domain.*;

import map.project.demo.Repository.GenreRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genre")
@Getter
@Setter
@NoArgsConstructor
public class GenreController {
    @Autowired
    private GenreRepository genreRepo;

    @PostMapping
    public void addGenre(@RequestBody Genre genre) throws SQLException {
        genreRepo.addGenreToTable(genre);
    }

    @GetMapping("/{id}")
    public Genre findGenreById(@PathVariable String id) throws SQLException {
        return genreRepo.getGenreWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGenreWithIdFromTable(@PathVariable String id) throws SQLException {
        genreRepo.deleteGenreWithIdFromTable(id);
    }

    public boolean findMovieById(Genre genre, String id) {
        for (String movie : genre.getListOfMovies()) {
            if (Objects.equals(movie, id)) {
                return true;
            }
        }
        System.out.println("No movie with that id");
        return false;
    }

    @DeleteMapping
    public void deleteAllGenres() throws SQLException {
        genreRepo.deleteAllFromGenreTable();
    }

    @DeleteMapping("/{id}/movies")
    public void deleteAllFromMovieGenreTable(@PathVariable String id) throws SQLException {
        genreRepo.deleteAllMoviesFromGenreWithId(id);
    }

    @PutMapping("/{id}/updateGenreName")
    public void updateGenreName(@PathVariable String id, @RequestBody String genreName) throws SQLException {
        Genre genre = genreRepo.getGenreWithIdFromTable(id);
        genreRepo.deleteGenreWithIdFromTable(id);
        genre.setName(genreName);
        genreRepo.addGenreToTable(genre);
    }

    @DeleteMapping("/{genreId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String genreId, @PathVariable String movieId) throws SQLException {
        genreRepo.deleteMovie(genreId, movieId);
    }

    @PostMapping("{genreId}/movies")
    public void addMovie(@PathVariable String genreId, @RequestBody String movieId) throws SQLException {
        genreRepo.addMovie(genreId, movieId);
    }

    @GetMapping
    public Vector<Genre> getAllGenres() throws SQLException {
        return genreRepo.getGenresFromTable();
    }

}

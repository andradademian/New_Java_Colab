package map.project.demo.Controller;

import map.project.demo.Domain.*;


import java.util.List;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import map.project.demo.Repository.IGenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/genre")
@Getter
@Setter
@NoArgsConstructor
public class GenreController {
    @Autowired
    private IGenreRepo genreRepo;

    @PostMapping
    public Genre addGenre(@RequestBody Genre genre) {
        genreRepo.save(genre);
        return genre;
    }

    @GetMapping("/{id}")
    public Genre findGenreById(@PathVariable String id) {
        return genreRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteGenreWithIdFromTable(@PathVariable String id) {
        genreRepo.deleteById(id);
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
    public void deleteAllGenres() {
        genreRepo.deleteAll();
    }

//    @DeleteMapping
//    public void deleteAllFromMovieGenreTable()  {
//        genreRepo.deleteAll();
//    }

    @PutMapping("/{id}/updateGenreName")
    public void updateGenreName(@PathVariable String id, @RequestBody String genreName) {
        Genre genre = genreRepo.findById(id).get();
        genre.setName(genreName);
        genreRepo.deleteById(id);
        genreRepo.save(genre);
    }

    @DeleteMapping("/{genreId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String genreId, @PathVariable String movieId) {
        Genre genre = genreRepo.findById(genreId).get();
        genreRepo.deleteById(genreId);
        genre.deleteMovie(movieId);
        genreRepo.save(genre);
    }

    @PostMapping("{genreId}/movies")
    public void addMovie(@PathVariable String genreId, @RequestBody String movieId) {
        Genre genre = genreRepo.findById(genreId).get();
        genreRepo.deleteById(genreId);
        genre.addMovie(movieId);
        genreRepo.save(genre);
    }

    @GetMapping
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }

}

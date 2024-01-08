package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.*;

import map.project.demo.Repository.IMovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/movie")
@Getter
@Setter
@NoArgsConstructor
public class MovieController {
    @Autowired
    private IMovieRepo movieRepo;

    @PostMapping
    public void addMovie(@RequestBody Movie movie) {
        movieRepo.save(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovieWithId(@PathVariable String id) {
        return movieRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id)  {
        movieRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllMovies()  {
        movieRepo.deleteAll();
    }

    @PutMapping("/{movieId}/title")
    public void updateMovieTitle(@PathVariable String movieId, @RequestBody String title){
        Movie movie = movieRepo.findById(movieId).get();
        movieRepo.deleteById(movieId);
        movie.setTitle(title);
        movieRepo.save(movie);
    }

    @PutMapping("/{movieId}/duration")
    public void updateMovieDuration(@PathVariable String movieId, @RequestBody int duration) {
        Movie movie = movieRepo.findById(movieId).get();
        movieRepo.deleteById(movieId);
        movie.setDurationInMinutes(duration);
        movieRepo.save(movie);
    }

//    @DeleteMapping("/{movieId}/directors/{directorId}")
//    public void deleteStageDirectorFromMovie(@PathVariable String movieId, @PathVariable String directorId){
//        movieRepo.deleteDirectorFromMovie(movieId, directorId);
//    }
//
//    @PostMapping("/{movieId}/directors")
//    public void addStageDirectorToMovie(@PathVariable String movieId, @RequestBody String stageDirector) {
//        movieRepo.addDirectorToMovie(movieId, stageDirector);
//    }

//    @PostMapping("/{movieId}/genres")
//    public void addGenreToMovie(@PathVariable String movieId, @RequestBody String genre)  {
//        movieRepo.addGenreToMovie(movieId, genre);
//    }
//
//    @DeleteMapping("/{movieId}/genres/{genreId}")
//    public void deleteGenreFromMovie(@PathVariable String movieId, @PathVariable String genreId)  {
//        movieRepo.deleteGenreFromMovie(movieId, genreId);
//    }

//    @PostMapping("/{movieId}/actors")
//    public void addActorToMovie(@PathVariable String movieId, @RequestBody String actor)  {
//        movieRepo.addActorToMovie(movieId, actor);
//    }
//
//    @DeleteMapping("/{movieId}/actors/{actorId}")
//    public void deleteActorFromMovie(@PathVariable String movieId, @PathVariable String actorId)  {
//        movieRepo.deleteActorFromMovie(movieId, actorId);
//    }

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepo.findAll();
    }

}

package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.*;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/actor")
@Getter
@Setter
@NoArgsConstructor
public class ActorController {
    @Autowired
    private IActorRepository actorRepository;

    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    @PostMapping
    public ResponseEntity<Void> addActor(@RequestBody Actor actor) {
        System.out.println("Received actor: " + actor.toString());
        actorRepository.save(actor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<Actor> findActorById(@PathVariable String id) {
        return actorRepository.findById(id);
    }

    public boolean findMovieById(Actor actor, String id) {
        for (String movieId : actor.getListOfMovies()) {
            if (Objects.equals(movieId, id)) {
                return true;
            }
        }
        System.out.println("No movie with that id");
        return false;
    }

    public boolean findAwardById(Actor actor, String id) {
        for (String awardId : actor.getListOfAwards()) {
            if (Objects.equals(awardId, id)) {
                return true;
            }
        }
        System.out.println("No award with that id");
        return false;
    }

    @DeleteMapping
    public void deleteAllActors() {
        actorRepository.deleteAll();
    }


    @DeleteMapping("/{id}")
    public void deleteActorWithIdFromTable(@PathVariable String id) {
        actorRepository.deleteById(id);
    }

    @PutMapping("/{id}/updateFirstName")
    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) {
        Actor actor = actorRepository.findById(id).get();
        actorRepository.deleteById(id);
        actor.setFirstName(firstName);
        actorRepository.save(actor);
    }

    @PutMapping("/{id}/updateLastName")
    public void updateLastName(@PathVariable String id, @RequestBody String lastName) {
        Actor actor = actorRepository.findById(id).get();
        actorRepository.deleteById(id);
        actor.setLastName(lastName);
        actorRepository.save(actor);
    }

    @DeleteMapping("/{actorId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String actorId, @PathVariable String movieId) throws SQLException {
        Actor actor = actorRepository.findById(actorId).get();
        actorRepository.deleteById(actorId);
        actor.removeMovie(movieId);
        actorRepository.save(actor);
    }

    @PostMapping("{actorId}/movies")
    public void addMovie(@PathVariable String actorId, @RequestBody String movieId) throws SQLException {
        Actor actor = actorRepository.findById(actorId).get();
        actorRepository.deleteById(actorId);
        actor.addMovie(movieId);
        actorRepository.save(actor);
    }

    @DeleteMapping("/{actorId}/awards/{awardId}")
    public void deleteAward(@PathVariable String actorId, @PathVariable String awardId) throws SQLException {
        Actor actor = actorRepository.findById(actorId).get();
        actorRepository.deleteById(actorId);
        actor.removeAward(awardId);
        actorRepository.save(actor);
    }

    @PostMapping("{actorId}/awards")
    public void addAward(@PathVariable String actorId, @RequestBody String awardId) throws SQLException {
        Actor actor = actorRepository.findById(actorId).get();
        actorRepository.deleteById(actorId);
        actor.addAward(awardId);
        actorRepository.save(actor);
    }

    @GetMapping
    public List<Actor> getAllActors() {
        List<Actor> actors = actorRepository.findAll();
        logger.info("Retrieved {} actors from the database.", actors.size());
        return actors;
    }
}
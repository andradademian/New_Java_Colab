package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IActorRepository;
import map.project.demo.Service.ActorService;
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
    private ActorService actorService;

    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    @PostMapping
    public ResponseEntity<Void> addActor(@RequestBody Actor actor) {
        actorService.saveActor(actor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @GetMapping("/{id}")
//    public Optional<Actor> findActorById(@PathVariable String id) {
//        return actorService.findById(id);
//    }

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

//    @DeleteMapping
//    public void deleteAllActors() {
//        actorService.deleteAll();
//    }
//
//
//    @DeleteMapping("/{id}")
//    public void deleteActorWithIdFromTable(@PathVariable String id) {
//        actorService.deleteById(id);
//    }
//
//    @PutMapping("/{id}/updateFirstName")
//    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) {
//        Actor actor = actorService.findById(id).get();
//        actorService.deleteById(id);
//        actor.setFirstName(firstName);
//        actorService.save(actor);
//    }
//
//    @PutMapping("/{id}/updateLastName")
//    public void updateLastName(@PathVariable String id, @RequestBody String lastName) {
//        Actor actor = actorService.findById(id).get();
//        actorService.deleteById(id);
//        actor.setLastName(lastName);
//        actorService.save(actor);
//    }
//
//    @DeleteMapping("/{actorId}/movies/{movieId}")
//    public void deleteMovie(@PathVariable String actorId, @PathVariable String movieId) throws SQLException {
//        Actor actor = actorService.findById(actorId).get();
//        actorService.deleteById(actorId);
//        actor.removeMovie(movieId);
//        actorService.save(actor);
//    }
//
//    @PostMapping("{actorId}/movies")
//    public void addMovie(@PathVariable String actorId, @RequestBody String movieId) throws SQLException {
//        Actor actor = actorService.findById(actorId).get();
//        actorService.deleteById(actorId);
//        actor.addMovie(movieId);
//        actorService.save(actor);
//    }
//
//    @DeleteMapping("/{actorId}/awards/{awardId}")
//    public void deleteAward(@PathVariable String actorId, @PathVariable String awardId) throws SQLException {
//        Actor actor = actorService.findById(actorId).get();
//        actorService.deleteById(actorId);
//        actor.removeAward(awardId);
//        actorService.save(actor);
//    }
//
//    @PostMapping("{actorId}/awards")
//    public void addAward(@PathVariable String actorId, @RequestBody String awardId) throws SQLException {
//        Actor actor = actorService.findById(actorId).get();
//        actorService.deleteById(actorId);
//        actor.addAward(awardId);
//        actorService.save(actor);
//    }

    @GetMapping
    public List<Actor> getAllActors() {
        List<Actor> actors = actorService.findAll();
        logger.info("Retrieved {} actors from the database.", actors.size());
        return actors;
    }
}
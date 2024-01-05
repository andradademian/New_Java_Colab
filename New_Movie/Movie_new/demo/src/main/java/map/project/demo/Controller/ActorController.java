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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/actor")
@Getter
@Setter
@NoArgsConstructor
public class ActorController {
    @Autowired
    private IActorRepository actorRepo;
    private static final Logger logger = LoggerFactory.getLogger(ActorController.class);

    @PostMapping
    public ResponseEntity<Void> addActor(@RequestBody Actor actor) {
        actorRepo.save(actor);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> findActorById(@PathVariable String id) {
        Optional<Actor> actorOptional = actorRepo.findById(id);
        return actorOptional.map(actor -> new ResponseEntity<>(actor, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
        actorRepo.deleteAll();
    }

//    @DeleteMapping("/movies")
//    public void deleteAllFromActorMovieTable()  {
//        Actor actor=actorRepo.findById(actorId).get();
//        actorRepo.deleteById(actorId);
//        actor.removeAward(awardId);
//        actorRepo.save(actor);
//    }
//
//    @DeleteMapping("/awards")
//    public void deleteAllFromActorAwardTable() {
//        actorRepo.deleteAllFromActorAwardTable();
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActorWithIdFromTable(@PathVariable String id) {
        actorRepo.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}/updateFirstName")
    public ResponseEntity<Void> updateFirstName(@PathVariable String id, @RequestBody String firstName) {
        actorRepo.findById(id).ifPresent(actor -> {
            actor.setFirstName(firstName);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/updateLastName")
    public ResponseEntity<Void> updateLastName(@PathVariable String id, @RequestBody String lastName) {
        actorRepo.findById(id).ifPresent(actor -> {
            actor.setLastName(lastName);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{actorId}/movies/{movieId}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String actorId, @PathVariable String movieId) {
        actorRepo.findById(actorId).ifPresent(actor -> {
            actor.removeMovie(movieId);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{actorId}/movies")
    public ResponseEntity<Void> addMovie(@PathVariable String actorId, @RequestBody String movieId) {
        actorRepo.findById(actorId).ifPresent(actor -> {
            actor.addMovie(movieId);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{actorId}/awards/{awardId}")
    public ResponseEntity<Void> deleteAward(@PathVariable String actorId, @PathVariable String awardId) {
        actorRepo.findById(actorId).ifPresent(actor -> {
            actor.removeAward(awardId);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("{actorId}/awards")
    public ResponseEntity<Void> addAward(@PathVariable String actorId, @RequestBody String awardId) {
        actorRepo.findById(actorId).ifPresent(actor -> {
            actor.addAward(awardId);
            actorRepo.save(actor);
        });
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> actors = actorRepo.findAll();
        return new ResponseEntity<>(actors, HttpStatus.OK);
    }
}
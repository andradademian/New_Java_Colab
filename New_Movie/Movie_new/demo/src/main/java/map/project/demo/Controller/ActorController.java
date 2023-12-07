package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;

@RestController
@RequestMapping("/api/actor")
@Getter
@Setter
@NoArgsConstructor
public class ActorController {
    @Autowired
    private ActorRepository actorRepo;

    @PostMapping
    public void addActor(@RequestBody Actor actor) throws SQLException {
        actorRepo.addActorToTable(actor);
    }

    @GetMapping("/{id}")
    public Actor findActorById(@PathVariable String id) throws SQLException {
        return actorRepo.getActorWithIdFromTable(id);
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
    public void deleteAllActors() throws SQLException {
        actorRepo.deleteAllFromActorTable();
    }

    @DeleteMapping("/movies")
    public void deleteAllFromActorMovieTable() throws SQLException {
        actorRepo.deleteAllFromActorMovieTable();
    }

    @DeleteMapping("/awards")
    public void deleteAllFromActorAwardTable() throws SQLException {
        actorRepo.deleteAllFromActorAwardTable();
    }

    @DeleteMapping("/{id}")
    public void deleteActorWithIdFromTable(@PathVariable String id) throws SQLException {
        actorRepo.deleteActorWithIdFromTable(id);
    }

    @PutMapping("/{id}/updateFirstName")
    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) throws SQLException {
        Actor actor = actorRepo.getActorWithIdFromTable(id);
        actorRepo.deleteActorWithIdFromTable(id);
        actor.setFirstName(firstName);
        actorRepo.addActorToTable(actor);
    }

    @PutMapping("/{id}/updateLastName")
    public void updateLastName(@PathVariable String id, @RequestBody String lastName) throws SQLException {
        Actor actor = actorRepo.getActorWithIdFromTable(id);
        actorRepo.deleteActorWithIdFromTable(id);
        actor.setLastName(lastName);
        actorRepo.addActorToTable(actor);
    }

    @DeleteMapping("/{actorId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String actorId, @PathVariable String movieId) throws SQLException {
        actorRepo.deleteMovie(actorId, movieId);
    }

    @PostMapping("{actorId}/movies")
    public void addMovie(@PathVariable String actorId, @RequestBody String movieId) throws SQLException {
        actorRepo.addMovie(actorId, movieId);
    }

    @DeleteMapping("/{actorId}/awards/{awardId}")
    public void deleteAward(@PathVariable String actorId, @PathVariable String awardId) throws SQLException {
        actorRepo.deleteAward(actorId, awardId);
    }

    @PostMapping("{actorId}/awards")
    public void addAward(@PathVariable String actorId, @RequestBody String awardId) throws SQLException {
        actorRepo.addAward(actorId, awardId);
    }

    @GetMapping
    public Vector<Actor> getAllActors() throws SQLException {
        return actorRepo.getActorsFromTable();
    }
}
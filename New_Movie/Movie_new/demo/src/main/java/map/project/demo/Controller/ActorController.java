package map.project.demo.Controller;

import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IActorRepository;
import map.project.demo.Service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/actor")
public class ActorController {

    @Autowired
    private IActorRepository actorRepo;

    @Autowired
    private ActorService actorService;

    @PostMapping
    public void addActor(@RequestBody Actor actor) {
        actorRepo.save(actor);
    }

    @GetMapping("/{id}")
    public Optional<Actor> findActorById(@PathVariable String id) {
        return actorRepo.findById(id);
    }

    @DeleteMapping
    public void deleteAllActors() {
        actorRepo.deleteAll();
    }

    @DeleteMapping("/{id}")
    public void deleteActorWithIdFromTable(@PathVariable String id) {
        actorRepo.deleteById(id);
    }

    @PutMapping("/{id}/updateFirstName")
    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) {
        actorService.updateFirstName(id, firstName);
    }

    @PutMapping("/{id}/updateLastName")
    public void updateLastName(@PathVariable String id, @RequestBody String lastName) {
        actorService.updateLastName(id, lastName);
    }

    @DeleteMapping("/{actorId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String actorId, @PathVariable String movieId) throws SQLException {
        actorService.deleteMovie(actorId, movieId);
    }

    @PostMapping("{actorId}/movies")
    public void addMovie(@PathVariable String actorId, @RequestBody String movieId) throws SQLException {
        actorService.addMovie(actorId, movieId);
    }

    @DeleteMapping("/{actorId}/awards/{awardId}")
    public void deleteAward(@PathVariable String actorId, @PathVariable String awardId) throws SQLException {
        actorService.deleteAward(actorId, awardId);
    }

    @PostMapping("{actorId}/awards")
    public void addAward(@PathVariable String actorId, @RequestBody String awardId) throws SQLException {
        actorService.addAward(actorId, awardId);
    }

    @GetMapping("/all")
    public List<Actor> getAllActors() {
        return actorService.getAll();
    }
}

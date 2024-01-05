package map.project.demo.Service;

import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IActorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class ActorService {

    @Autowired
    private IActorRepository actorRepo;

    @Transactional
    public void updateFirstName(String id, String firstName) {
        Actor actor = actorRepo.findById(id).orElseThrow();
        actor.setFirstName(firstName);
        actorRepo.save(actor);
    }

    @Transactional
    public void updateLastName(String id, String lastName) {
        Actor actor = actorRepo.findById(id).orElseThrow();
        actor.setLastName(lastName);
        actorRepo.save(actor);
    }

    @Transactional
    public void deleteMovie(String actorId, String movieId) throws SQLException {
        Actor actor = actorRepo.findById(actorId).orElseThrow();
        actor.removeMovie(movieId);
        actorRepo.save(actor);
    }

    @Transactional
    public void addMovie(String actorId, String movieId) throws SQLException {
        Actor actor = actorRepo.findById(actorId).orElseThrow();
        actor.addMovie(movieId);
        actorRepo.save(actor);
    }

    @Transactional
    public void deleteAward(String actorId, String awardId) throws SQLException {
        Actor actor = actorRepo.findById(actorId).orElseThrow();
        actor.removeAward(awardId);
        actorRepo.save(actor);
    }

    @Transactional
    public void addAward(String actorId, String awardId) throws SQLException {
        Actor actor = actorRepo.findById(actorId).orElseThrow();
        actor.addAward(awardId);
        actorRepo.save(actor);
    }

    @Transactional
    public List<Actor> getAll() {
        return actorRepo.findAll();
    }
}

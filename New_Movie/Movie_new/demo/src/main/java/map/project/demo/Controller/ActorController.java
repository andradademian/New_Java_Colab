package map.project.demo.Controller;

import map.project.demo.Repository.ActorRepository;
import map.project.demo.Domain.Actor;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Vector;


public class ActorController {
    private final ActorRepository actorRepo;

    public ActorController(ActorRepository actorRepo) {
        this.actorRepo = actorRepo;
    }

    public void addActor(Actor actor) {
        actorRepo.add(actor);
    }

    public Actor findActorById(String actorId) {
        for (Actor actor : actorRepo.getAll()) {
            if (actor.getId().equals(actorId)) {
                return actor;
            }
        }
        System.out.println("No actor with that ID");
        return null;
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

    public void deleteActor(String actorId) {
        Actor actorToDelete = findActorById(actorId);
        if (actorToDelete != null) {
            actorRepo.delete(actorToDelete);
        }
    }

    public void deleteAllActors() {
        actorRepo.deleteAll();
    }

    public void showAllActors() {
        actorRepo.printAll();
    }

    public void updateFirstName(Actor actor, String firstName) {
        actorRepo.updateFirstName(actor, firstName);
    }

    public void updateLastName(Actor actor, String lastName) {
        actorRepo.updateLastName(actor, lastName);
    }

    public void deleteMovie(Actor actor, String movie) throws SQLException {
        actorRepo.deleteMovie(actor, movie);
    }

    public void addMovie(Actor actor, String movie) {
        actorRepo.addMovie(actor, movie);
    }

    public void deleteAward(Actor actor, String award) {
        actorRepo.deleteAward(actor, award);
    }

    public void addAward(Actor actor, String award) {
        actorRepo.addAward(actor, award);
    }

    public Vector<Actor> getAllActors() {
        return actorRepo.getAll();
    }
}
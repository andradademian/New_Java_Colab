package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;

import java.util.Vector;

public class ActorRepository {
    private static ActorRepository instance;
    private final Vector<Actor> listOfActors;

    private ActorRepository() {
        listOfActors = new Vector<>();
    }

    public static ActorRepository getInstance() {
        if (instance == null) {
            instance = new ActorRepository();
        }
        return instance;
    }

    public void add(Actor actor) {
        listOfActors.add(actor);
    }

    public void delete(Actor actor) {
        listOfActors.remove(actor);
    }

    public void deleteAll() {
        listOfActors.clear();
    }

    public void printAll() {
        System.out.println(listOfActors);
    }

    public void updateFirstName(Actor actor, String firstName) {
        listOfActors.get(getAll().indexOf(actor)).setFirstName(firstName);
    }

    public void updateLastName(Actor actor, String lastName) {
        listOfActors.get(getAll().indexOf(actor)).setLastName(lastName);
    }

    public void deleteMovie(Actor actor, Movie movie) {
        listOfActors.get(getAll().indexOf(actor)).removeMovie(movie);
    }

    public void addMovie(Actor actor, Movie movie) {
        listOfActors.get(getAll().indexOf(actor)).addMovie(movie);
    }

    public void deleteAward(Actor actor, Award award) {
        listOfActors.get(getAll().indexOf(actor)).removeAward(award);
    }

    public void addAward(Actor actor, Award award) {
        listOfActors.get(getAll().indexOf(actor)).addAward(award);
    }

    public Vector<Actor> getAll() {
        return this.listOfActors;
    }
}

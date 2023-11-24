package map.project.demo.Repository;

import map.project.demo.Domain.Spectator;

import java.util.Vector;

public class SpectatorRepository {
    private static SpectatorRepository instance;
    private final Vector<Spectator> spectators;

    public SpectatorRepository() {
        spectators = new Vector<>();
    }
    public static SpectatorRepository getInstance() {
        if (instance == null) {
            instance = new SpectatorRepository();
        }
        return instance;
    }
    public void add(Spectator spectator) {
        spectators.add(spectator);
    }

    public void delete(Spectator spectator) {
        spectators.remove(spectator);
    }

    public void deleteAll() {
        spectators.clear();
    }

    public void printAll() {
        System.out.println(spectators);
    }

    public void updateFirstName(Spectator spectator, String firstName) {
        spectators.get(getAll().indexOf(spectator)).setFirstName(firstName);
    }

    public void updateLastName(Spectator spectator, String lastName) {
        spectators.get(getAll().indexOf(spectator)).setLastName(lastName);
    }

    public Vector<Spectator> getAll() {
        return this.spectators;
    }
}

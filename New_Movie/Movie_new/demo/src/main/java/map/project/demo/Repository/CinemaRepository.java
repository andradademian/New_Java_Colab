package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;

import java.util.Vector;

public class CinemaRepository {
    private static CinemaRepository instance;
    private final Vector<Cinema> cinemas;

    public CinemaRepository() {
        cinemas = new Vector<>();
    }
    public static CinemaRepository getInstance() {
        if (instance == null) {
            instance = new CinemaRepository();
        }
        return instance;
    }
    public void add(Cinema cinema) {
        cinemas.add(cinema);
    }

    public void delete(Cinema cinema) {
        cinemas.remove(cinema);
    }

    public void deleteAll() {
        cinemas.clear();
    }

    public void printAll() {
        System.out.println(cinemas);
    }

    public Vector<Cinema> getAll() {
        return this.cinemas;
    }

    public void updateName(Cinema cinema, String name) {
        cinemas.get(getAll().indexOf(cinema)).setName(name);
    }

    public void updateAddress(Cinema cinema, String address) {
        cinemas.get(getAll().indexOf(cinema)).setAddress(address);
    }

    public void addRoom(Cinema cinema, Room room) {
        cinemas.get(getAll().indexOf(cinema)).addRoom(room);
    }

    public void deleteRoom(Cinema cinema, Room room) {
        cinemas.get(getAll().indexOf(cinema)).removeRoom(room);
    }
}

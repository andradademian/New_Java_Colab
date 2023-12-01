package map.project.demo.Repository;

import map.project.demo.Domain.Cinema;

import java.util.Vector;

public interface CinemaRepositoryComponent {
    Vector<Cinema> getAll();
    void add(Cinema cinema);
    void delete(Cinema cinema);
    void deleteAll();
    void printAll();

    void addRoom(Cinema cinema, String roomId);

    void deleteRoom(Cinema cinema, String roomId);
}

package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Room;

import java.util.Vector;

public class RoomRepository {
    private static RoomRepository instance;
    private final Vector<Room> rooms;

    public RoomRepository() {
        rooms = new Vector<>();
    }
    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }
    public void add(Room room) {
        rooms.add(room);
    }

    public void delete(Room room) {
        rooms.remove(room);
    }

    public void deleteAll() {
        rooms.clear();
    }

    public void printAll() {
        System.out.println(rooms);
    }

    public void updateRoomNumber(Room room, int roomNumber) {
        rooms.get(getAll().indexOf(room)).setRoomNumber(roomNumber);
    }

    public void updateNumberOfSeats(Room room, int numberOfSeats) {
        rooms.get(getAll().indexOf(room)).setNumberOfSeats(numberOfSeats);
    }

    public Vector<Room> getAll() {
        return this.rooms;
    }
}

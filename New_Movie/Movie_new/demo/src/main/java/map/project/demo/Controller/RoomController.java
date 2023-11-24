package map.project.demo.Controller;

import map.project.demo.Domain.Room;
import map.project.demo.Repository.RoomRepository;

import java.util.Scanner;
import java.util.Vector;

public class RoomController {
    private final RoomRepository roomRepo;

    public RoomController(RoomRepository roomRepo) {
        this.roomRepo = roomRepo;
    }

    public void addRoom(Room room) {
        roomRepo.add(room);
    }

    public Room findRoomById(String roomId) {
        for (Room room : roomRepo.getAll()) {
            if (room.getId().equals(roomId)) {
                return room;
            }
        }
        System.out.println("No room with that ID");
        return null;
    }

    public void deleteRoom(String roomId) {
        Room roomToDelete = findRoomById(roomId);
        if (roomToDelete != null) {
            roomRepo.delete(roomToDelete);
        }
    }

    public void deleteAllRooms() {
        roomRepo.deleteAll();
    }

    public void updateRoomNumber(Room room, int roomNumber) {
        roomRepo.updateRoomNumber(room, roomNumber);
    }

    public void updateNumberOfSeats(Room room, int numberOfSeats) {
        roomRepo.updateNumberOfSeats(room, numberOfSeats);
    }

    public Vector<Room> getAllRooms() {
        return roomRepo.getAll();
    }

    public void printAllRooms() {
        roomRepo.printAll();
    }
}

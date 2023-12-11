package map.project.demo.Controller;

import map.project.demo.Domain.Room;
import map.project.demo.Repository.RoomRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.sql.SQLException;
import java.util.Vector;

@RestController
@RequestMapping("/api/room")
@Getter
@Setter
@NoArgsConstructor
public class RoomController {
    @Autowired
    private RoomRepository roomRepo;

    @PostMapping
    public void addRoom(@RequestBody Room room) throws SQLException {
        roomRepo.addRoomToTable(room);
    }

    @GetMapping("/{id}")
    public Room findRoomById(@PathVariable String id) throws SQLException {
        return roomRepo.getRoomWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteRoomWithIdFromTable(@PathVariable String id) throws SQLException {
        roomRepo.deleteRoomWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllRooms() throws SQLException {
        roomRepo.deleteAllFromRoomTable();
    }

    @PutMapping("/{id}/updateRoomNumber")
    public void updateRoomNumber(@PathVariable String id, @RequestBody int roomNumber) throws SQLException {
        Room room = roomRepo.getRoomWithIdFromTable(id);
        roomRepo.deleteRoomWithIdFromTable(id);
        room.setRoomNumber(roomNumber);
        roomRepo.addRoomToTable(room);
    }

    @PutMapping("/{id}/updateNumberOfSeats")
    public void updateNumberOfSeats(@PathVariable String id, @RequestBody int numberOfSeats) throws SQLException {
        Room room = roomRepo.getRoomWithIdFromTable(id);
        roomRepo.deleteRoomWithIdFromTable(id);
        room.setNumberOfSeats(numberOfSeats);
        roomRepo.addRoomToTable(room);
    }

    @GetMapping
    public Vector<Room> getAllRooms() throws SQLException {
        return roomRepo.getRoomsFromTable();
    }
}

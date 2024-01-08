package map.project.demo.Controller;

import map.project.demo.Domain.Room;
import map.project.demo.Repository.IRoomRepo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/room")
@Getter
@Setter
@NoArgsConstructor
public class RoomController {
    @Autowired
    private IRoomRepo roomRepo;

    @PostMapping
    public Room addRoom(@RequestBody Room room) {
        roomRepo.save(room);
        return room;
    }

    @GetMapping("/{id}")
    public Room findRoomById(@PathVariable String id) {
        return roomRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteRoomWithIdFromTable(@PathVariable String id)  {
        roomRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllRooms() {
        roomRepo.deleteAll();
    }

    @PutMapping("/{id}/updateRoomNumber")
    public void updateRoomNumber(@PathVariable String id, @RequestBody int roomNumber) {
        Room room = roomRepo.findById(id).get();
        roomRepo.deleteById(id);
        room.setRoomNumber(roomNumber);
        roomRepo.save(room);
    }

    @PutMapping("/{id}/updateNumberOfSeats")
    public void updateNumberOfSeats(@PathVariable String id, @RequestBody int numberOfSeats){
        Room room = roomRepo.findById(id).get();
        roomRepo.deleteById(id);
        room.setNumberOfSeats(numberOfSeats);
        roomRepo.save(room);
    }

    @GetMapping
    public List<Room> getAllRooms() {
        return roomRepo.findAll();
    }
}

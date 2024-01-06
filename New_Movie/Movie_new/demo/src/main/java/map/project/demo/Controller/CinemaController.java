package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import map.project.demo.Domain.*;

import map.project.demo.Repository.ICinemaRepo;


import java.sql.SQLException;
import java.util.List;
import java.util.Objects;



@RestController
@RequestMapping("/api/cinema")
@Getter
@Setter
@NoArgsConstructor
public class CinemaController {
    @Autowired
    private ICinemaRepo cinemaRepo;

    @PostMapping
    public Cinema addCinema(@RequestBody Cinema cinema) {
        cinemaRepo.save(cinema);
        return cinema;
    }

    @GetMapping("/{id}")
    public Cinema findCinemaById(@PathVariable String id) {
        return cinemaRepo.findById(id).get();
    }


    @DeleteMapping("/{id}")
    public void deleteCinemaWithIdFromTable(@PathVariable String id) {
        cinemaRepo.deleteById(id);
    }


    public boolean findRoomById(Cinema cinema, String id) {
        for (String room : cinema.getListOfRooms()) {
            if (Objects.equals(room, id)) {
                return true;
            }
        }
        System.out.println("No room with that id");
        return false;
    }

    @DeleteMapping("/rooms")
    public void deleteAllFromCinemaRoomTable() {
        cinemaRepo.deleteAll();
    }

    @DeleteMapping
    public void deleteAllCinemas() {
        cinemaRepo.deleteAll();
    }

    @PutMapping("/{id}/updateCinemaName")
    public void updateCinemaName(@PathVariable String id, @RequestBody String cinemaName){
        Cinema cinema = cinemaRepo.findById(id).get();
        cinema.setName(cinemaName);
        cinemaRepo.deleteById(id);
        cinemaRepo.save(cinema);
    }

    @PutMapping("/{id}/updateCinemaAddress")
    public void updateCinemaAddress(@PathVariable String id, @RequestBody String cinemaAddress) {
        Cinema cinema = cinemaRepo.findById(id).get();
        cinema.setAddress(cinemaAddress);
        cinemaRepo.deleteById(id);
        cinemaRepo.save(cinema);
    }

    @PostMapping("{cinemaId}/rooms")
    public void addRoom(@PathVariable String cinemaId, @RequestBody String roomId) {
        Cinema cinema = cinemaRepo.findById(cinemaId).get();
        cinemaRepo.deleteById(cinemaId);
        cinema.addRoom(roomId);
        cinemaRepo.save(cinema);
    }

    @DeleteMapping("{/{cinemaId}/rooms/{roomId}")
    public void deleteRoom(@PathVariable String cinemaId, @RequestBody String roomId) {
        Cinema cinema = cinemaRepo.findById(cinemaId).get();
        cinemaRepo.deleteById(cinemaId);
        cinema.removeRoom(roomId);
        cinemaRepo.save(cinema);
    }
    @GetMapping
    public List<Cinema> getAllCinemas() {
        return cinemaRepo.findAll();
    }

}

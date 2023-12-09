package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;


import map.project.demo.Domain.*;

import map.project.demo.Repository.CinemaRepository;

import java.sql.SQLException;
import java.util.Objects;

import java.util.Vector;

@RestController
@RequestMapping("/api/cinema")
@Getter
@Setter
@NoArgsConstructor
public class CinemaController {
    @Autowired
    private CinemaRepository cinemaRepo;

    @PostMapping
    public void addCinema(@RequestBody Cinema cinema) throws SQLException {
        cinemaRepo.addCinemaToTable(cinema);
    }

    @GetMapping("/{id}")
    public Cinema findCinemaById(@PathVariable String id) throws SQLException {
        return cinemaRepo.getCinemaWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteCinemaWithIdFromTable(@PathVariable String id) throws SQLException {
        cinemaRepo.deleteCinemaWithIdFromTable(id);
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
    public void deleteAllFromCinemaRoomTable() throws SQLException {
        cinemaRepo.deleteAllFromCinemaRoomTable();
    }

    @DeleteMapping
    public void deleteAllCinemas() throws SQLException {
        cinemaRepo.deleteAllFromCinemaTable();
    }

    @PutMapping("/{id}/updateCinemaName")
    public void updateCinemaName(@PathVariable String id, @RequestBody String cinemaName) throws SQLException {
        Cinema cinema = cinemaRepo.getCinemaWithIdFromTable(id);
        cinemaRepo.deleteCinemaWithIdFromTable(id);
        cinema.setName(cinemaName);
        cinemaRepo.addCinemaToTable(cinema);
    }

    @PutMapping("/{id}/updateCinemaName")
    public void updateCinemaAddress(@PathVariable String id, @RequestBody String cinemaAddress) throws SQLException {
        Cinema cinema = cinemaRepo.getCinemaWithIdFromTable(id);
        cinemaRepo.deleteCinemaWithIdFromTable(id);
        cinema.setAddress(cinemaAddress);
        cinemaRepo.addCinemaToTable(cinema);
    }
    @PostMapping("{cinemaId}/rooms")
    public void addRoom(@PathVariable String cinemaId, @RequestBody String roomId) throws SQLException {
        cinemaRepo.addRoom(cinemaId, roomId);
    }

    @DeleteMapping("/{cinemaId}/rooms/{roomId}")
    public void deleteRoom(@PathVariable String cinemaId, @PathVariable String roomId) throws SQLException {
        cinemaRepo.deleteRoom(cinemaId, roomId);
    }
    @GetMapping
    public Vector<Cinema> getAllCinemas() {
        return cinemaRepo.getAll();
    }

}

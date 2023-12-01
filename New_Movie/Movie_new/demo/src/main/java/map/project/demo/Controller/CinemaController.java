package map.project.demo.Controller;

import map.project.demo.Decorator.RoomDecorator;
import map.project.demo.Domain.*;
import map.project.demo.Repository.AwardRepository;
import map.project.demo.Repository.CinemaRepository;
import map.project.demo.Repository.CinemaRepositoryComponent;

import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class CinemaController {
    //private final CinemaRepository cinemaRepo;
    private final CinemaRepositoryComponent cinemaRepo;

    public CinemaController(CinemaRepository cinemaRepo) {
        this.cinemaRepo = cinemaRepo;
    }

    public void addCinema(Cinema cinema) {
        cinemaRepo.add(cinema);
    }

    public Cinema findCinemaById(String cinemaId) {
        for (Cinema cinema : cinemaRepo.getAll()) {
            if (cinema.getId().equals(cinemaId)) {
                return cinema;
            }
        }
        System.out.println("No cinema with that ID");
        return null;
    }

    public void deleteCinema(String cinemaId) {
        Cinema cinemaToDelete = findCinemaById(cinemaId);
        if (cinemaToDelete != null) {
            cinemaRepo.delete(cinemaToDelete);
        }
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

    public void deleteCinema(Cinema cinema) {
        cinemaRepo.delete(cinema);
    }

    public void deleteAllCinemas() {
        cinemaRepo.deleteAll();
    }

    public void updateCinemaName(Cinema cinema, String name) {
        cinemaRepo.updateName(cinema, name);
    }

    public void updateCinemaAddress(Cinema cinema, String address) {
        cinemaRepo.updateAddress(cinema, address);
    }

    public void addRoomToCinema(Cinema cinema, String room) {
        cinemaRepo.addRoom(cinema, room);
    }

    public void deleteRoomFromCinema(Cinema cinema, String room) {
        cinemaRepo.deleteRoom(cinema, room);
    }

    public Vector<Cinema> getAllCinemas() {
        return cinemaRepo.getAll();
    }

    public void printAllCinemas() {
        cinemaRepo.printAll();
    }

}

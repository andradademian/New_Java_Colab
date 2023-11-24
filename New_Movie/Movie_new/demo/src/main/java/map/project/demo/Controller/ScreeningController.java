package map.project.demo.Controller;

import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.ScreeningRepository;
import map.project.demo.Strategy.Screening;

import java.sql.Time;
import java.util.Vector;

public class ScreeningController {
    private final ScreeningRepository screeningRepo;

    public ScreeningController(ScreeningRepository screeningRepo) {
        this.screeningRepo = screeningRepo;
    }

    public void addScreening(Screening screening) {
        screeningRepo.add(screening);
    }

    public Screening findScreeningById(String screeningId) {
        for (Screening screening : screeningRepo.getAll()) {
            if (screening.getId().equals(screeningId)) {
                return screening;
            }
        }
        System.out.println("No screening with that ID");
        return null;
    }

    public void deleteScreening(String screeningId) {
        Screening screeningToDelete = findScreeningById(screeningId);
        if (screeningToDelete != null) {
            screeningRepo.delete(screeningToDelete);
        }
    }

    public void deleteAllScreenings() {
        screeningRepo.deleteAll();
    }


    public void updateScreeningRoom(Screening screening, Room room) {
        screeningRepo.updateRoom(screening, room);
    }

    public void updateScreeningStartTime(Screening screening, Time startTime) {
        screeningRepo.updateStartTime(screening, startTime);
    }

    public void updateScreeningMovie(Screening screening, Movie movie) {
        screeningRepo.updateMovie(screening, movie);
    }

    public Vector<Screening> getAllScreenings() {
        return screeningRepo.getAll();
    }

    public void printAllScreenings() {
        screeningRepo.printAll();
    }
}

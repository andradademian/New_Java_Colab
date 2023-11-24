package map.project.demo.Repository;

import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;

import java.sql.Time;
import java.util.Vector;

public class ScreeningRepository {
    private static ScreeningRepository instance;
    private final Vector<Screening> screenings;

    public ScreeningRepository() {
        screenings = new Vector<>();
    }

    public static ScreeningRepository getInstance() {
        if (instance == null) {
            instance = new ScreeningRepository();
        }
        return instance;
    }

    public void add(Screening screening) {
        screenings.add(screening);
    }

    public void delete(Screening screening) {
        screenings.remove(screening);
    }

    public void deleteAll() {
        screenings.clear();
    }

    public void printAll() {
        System.out.println(screenings);
    }

    public void updateMovie(Screening screening, Movie movie) {
        screenings.get(getAll().indexOf(screening)).setMovie(movie);
    }

    public void updateRoom(Screening screening, Room room) {
        screenings.get(getAll().indexOf(screening)).setIdRoom(room);
    }

    public void updateStartTime(Screening screening, Time startTime) {
        screenings.get(getAll().indexOf(screening)).setStartTime(startTime);
    }

    public Vector<Screening> getAll() {
        return this.screenings;
    }
}

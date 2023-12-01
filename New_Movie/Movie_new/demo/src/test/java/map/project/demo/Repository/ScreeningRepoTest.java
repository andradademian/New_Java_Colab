package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Repository.ScreeningRepository;
import map.project.demo.Repository.RoomRepository;
import map.project.demo.Repository.MovieRepository;
import map.project.demo.Strategy.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ScreeningRepoTest {
    ScreeningRepository screeningRepository;
    MovieRepository movieRepository;
    RoomRepository roomRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        screeningRepository = ScreeningRepository.getInstance();
        movieRepository = MovieRepository.getInstance();
        roomRepository = RoomRepository.getInstance();
    }

    @Test
    public void expectScreeningAddedSuccessfully() {
        screeningIsAddedToTheList();
        assertEquals(screeningRepository.getAll().size(), 1);
    }

    @Test
    public void expectScreeningNotAddedSuccessfully() {
        screeningIsAddedToTheList();
        assertNotEquals(screeningRepository.getAll().size(), 0);
    }

    @Test
    public void expectScreeningRemovedSuccessfully() {
        screeningIsAddedToTheList();
        Screening screening = screeningRepository.getAll().get(0);
        screeningRepository.delete(screening);
        assertEquals(screeningRepository.getAll().size(), 0);
    }

    @Test
    public void expectScreeningNotRemovedSuccessfully() {
        screeningIsAddedToTheList();
        Screening screening = screeningRepository.getAll().get(0);
        screeningRepository.delete(screening);
        assertNotEquals(screeningRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllScreenings() {
        screeningIsAddedToTheList();
        screeningRepository.deleteAll();
        assertEquals(screeningRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllScreenings() {
        screeningIsAddedToTheList();
        screeningRepository.deleteAll();
        assertNotEquals(screeningRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdateMovieSuccessfully() {
        screeningIsAddedToTheList();
        Screening screening = screeningRepository.getAll().get(0);
        Movie newMovie = createMovie();
        screeningRepository.updateMovie(screening, newMovie);
        assertEquals(screening.getMovie(), newMovie);
    }

    @Test
    public void expectUpdateRoomSuccessfully() {
        screeningIsAddedToTheList();
        Screening screening = screeningRepository.getAll().get(0);
        Room newRoom = createRoom();
        screeningRepository.updateRoom(screening, newRoom);
        assertEquals(screening.getId(), newRoom.getId());
    }

    @Test
    public void expectUpdateStartTimeSuccessfully() {
        screeningIsAddedToTheList();
        Screening screening = screeningRepository.getAll().get(0);
        Time newStartTime = Time.valueOf("14:30:00");
        screeningRepository.updateStartTime(screening, newStartTime);
        assertEquals(screening.getStartTime(), newStartTime);
    }

    public void screeningIsAddedToTheList() {
        screeningRepository.deleteAll();
        Movie movie = createMovie();
        Room room = createRoom();
        Time startTime = Time.valueOf("12:00:00");
        Screening screening = new Screening2D("1", movie, room, startTime);
        screeningRepository.add(screening);
    }

    public Movie createMovie() {
        return new Movie("1", "MovieTitle", 90, new Vector<String>(), new Vector<String>(), new Vector<String>());
    }

    public Room createRoom() {
        return RoomBuilder.buildRoom("1", 100, 120);
    }

}

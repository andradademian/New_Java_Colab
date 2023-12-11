package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.Award;

import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.AwardRepository;
import map.project.demo.Repository.CinemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CinemaRepoTest {
    CinemaRepository cinemaRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        cinemaRepository = new CinemaRepository();
    }

    @Test
    public void expectCinemaAddedSuccessfully() throws SQLException {
        int size = cinemaRepository.getCinemasFromTable().size();
        cinemaIsAddedToTheTable();
        assertSame(cinemaRepository.getCinemasFromTable().size(), size + 1);
        cinemaIsRemoved();
    }

    @Test
    public void expectCinemaNotAddedSuccessfully() throws SQLException {
        int size = cinemaRepository.getCinemasFromTable().size();
        cinemaIsAddedToTheTable();
        assertNotSame(cinemaRepository.getCinemasFromTable().size(), size);
        cinemaIsRemoved();
    }

    @Test
    public void expectCinemaRemovedSuccessfully() throws SQLException {
        int size = cinemaRepository.getCinemasFromTable().size();
        cinemaIsAddedToTheTable();
        assertSame(cinemaRepository.getCinemasFromTable().size(), size + 1);
        cinemaIsRemoved();
        assertSame(cinemaRepository.getCinemasFromTable().size(), size);
    }

    @Test
    public void expectCinemaNotRemovedSuccessfully() throws SQLException {
        int size = cinemaRepository.getCinemasFromTable().size();
        cinemaIsAddedToTheTable();
        assertEquals(cinemaRepository.getCinemasFromTable().size(), size + 1);
        cinemaIsRemoved();
        assertNotEquals(cinemaRepository.getCinemasFromTable().size(), size + 1);
    }

    @Test
    public void expectCorrectRemovalOfAllCinemas() {
        cinemaRepository.deleteAll();
        assertEquals(cinemaRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllCinemas() {
        cinemaRepository.deleteAll();
        assertNotEquals(cinemaRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfTheName() throws SQLException {
        cinemaIsAddedToTheList();
        cinemaRepository.updateName(cinemaRepository.getAll().getLast(), "Other Name");
        String expected = "Other Name";
        String actual = cinemaRepository.getAll().getLast().getName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheName() throws SQLException {
        cinemaIsAddedToTheList();
        cinemaRepository.updateName(cinemaRepository.getAll().getLast(), "Other Name");
        String expected = "Name";
        String actual = cinemaRepository.getAll().getLast().getName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectUpdateOfTheAddress() throws SQLException {
        cinemaIsAddedToTheList();
        cinemaRepository.updateAddress(cinemaRepository.getAll().getLast(), "Other");
        String expected = "Other";
        String actual = cinemaRepository.getAll().getLast().getAddress();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheAddress() throws SQLException {
        cinemaIsAddedToTheList();
        cinemaRepository.updateAddress(cinemaRepository.getAll().getLast(), "Other");
        String expected = "Address";
        String actual = cinemaRepository.getAll().getLast().getAddress();
        assertNotSame(actual, expected);
    }

    public void cinemaIsAddedToTheList() {
        cinemaRepository.deleteAll();
        Cinema cinema = new Cinema("1", "Name", "Address", new Vector<>());
        cinemaRepository.add(cinema);
    }

    public void cinemaIsAddedToTheTable() throws SQLException {
        Cinema cinema = new Cinema("1K", "Name", "Address", new Vector<>());
        cinemaRepository.addCinemaToTable(cinema);
    }

    public void roomIsAddedToTheCinema() throws SQLException {
        Room room = RoomBuilder.buildRoom("1K", 1, 50);
        cinemaRepository.addRoom("1K", room.getId());
    }

    public void cinemaIsRemoved() throws SQLException {
        cinemaRepository.deleteCinemaWithIdFromTable("1K");
    }

    public void roomIsRemoved() throws SQLException {
        cinemaRepository.deleteRoom("1K", "1K");
    }
}

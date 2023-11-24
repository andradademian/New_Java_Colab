package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.Award;

import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.AwardRepository;
import map.project.demo.Repository.CinemaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class CinemaRepoTest {
    CinemaRepository cinemaRepository;

    @BeforeEach
    public void setUp() {
        cinemaRepository = CinemaRepository.getInstance();
    }

    @Test
    public void expectCinemaAddedSuccessfully() {
        cinemaIsAddedToTheList();
        assertEquals(cinemaRepository.getAll().size(), 1);
    }

    @Test
    public void expectCinemaNotAddedSuccessfully() {
        cinemaIsAddedToTheList();
        assertNotEquals(cinemaRepository.getAll().size(), 0);
    }

    @Test
    public void expectCinemaRemovedSuccessfully() {
        cinemaIsAddedToTheList();
        Cinema cinema = cinemaRepository.getAll().get(0);
        cinemaRepository.delete(cinema);
        assertEquals(cinemaRepository.getAll().size(), 0);
    }

    @Test
    public void expectCinemaNotRemovedSuccessfully() {
        cinemaIsAddedToTheList();
        Cinema cinema = cinemaRepository.getAll().get(0);
        cinemaRepository.delete(cinema);
        assertNotEquals(cinemaRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllCinemas() {
        cinemaIsAddedToTheList();
        cinemaRepository.deleteAll();
        assertEquals(cinemaRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllCinemas() {
        cinemaIsAddedToTheList();
        cinemaRepository.deleteAll();
        assertNotEquals(cinemaRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfTheName() {
        cinemaIsAddedToTheList();
        cinemaRepository.updateName(cinemaRepository.getAll().get(0), "Other Name");
        String expected = "Other Name";
        String actual = cinemaRepository.getAll().get(0).getName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheName() {
        cinemaIsAddedToTheList();
        cinemaRepository.updateName(cinemaRepository.getAll().get(0), "Other Name");
        String expected = "Name";
        String actual = cinemaRepository.getAll().get(0).getName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectUpdateOfTheAddress() {
        cinemaIsAddedToTheList();
        cinemaRepository.updateAddress(cinemaRepository.getAll().get(0), "Other");
        String expected = "Other";
        String actual = cinemaRepository.getAll().get(0).getAddress();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfTheAddress() {
        cinemaIsAddedToTheList();
        cinemaRepository.updateAddress(cinemaRepository.getAll().get(0), "Other");
        String expected = "Address";
        String actual = cinemaRepository.getAll().get(0).getAddress();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectAddingOfTheRoom() {
        cinemaIsAddedToTheList();
        roomIsAddedToTheCinema();
        assertEquals(cinemaRepository.getAll().get(0).getListOfRooms().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheRoom() {
        cinemaIsAddedToTheList();
        roomIsAddedToTheCinema();
        assertNotEquals(cinemaRepository.getAll().get(0).getListOfRooms().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheRoom() {
        cinemaIsAddedToTheList();
        roomIsAddedToTheCinema();
        cinemaRepository.deleteRoom(cinemaRepository.getAll().get(0), cinemaRepository.getAll().get(0).getListOfRooms().get(0));
        assertEquals(cinemaRepository.getAll().get(0).getListOfRooms().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheRoom() {
        cinemaIsAddedToTheList();
        roomIsAddedToTheCinema();
        cinemaRepository.deleteRoom(cinemaRepository.getAll().get(0), cinemaRepository.getAll().get(0).getListOfRooms().get(0));
        assertNotEquals(cinemaRepository.getAll().get(0).getListOfRooms().size(), 1);
    }

    public void cinemaIsAddedToTheList() {
        cinemaRepository.deleteAll();
        Cinema cinema = new Cinema("1", "Name", "Address", new Vector<>());
        cinemaRepository.add(cinema);
    }

    public void roomIsAddedToTheCinema() {
        Room room = RoomBuilder.buildRoom("1", 1, 50);
        cinemaRepository.addRoom(cinemaRepository.getAll().get(0), room);
    }
}

package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Repository.RoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RoomRepoTest {
    RoomRepository roomRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        roomRepository = RoomRepository.getInstance();
    }

    @Test
    public void expectRoomAddedSuccessfully() {
        roomIsAddedToTheList();
        assertEquals(roomRepository.getAll().size(), 1);
    }

    @Test
    public void expectRoomNotAddedSuccessfully() {
        roomIsAddedToTheList();
        assertNotEquals(roomRepository.getAll().size(), 0);
    }

    @Test
    public void expectRoomRemovedSuccessfully() {
        roomIsAddedToTheList();
        Room room = roomRepository.getAll().get(0);
        roomRepository.delete(room);
        assertEquals(roomRepository.getAll().size(), 0);
    }

    @Test
    public void expectRoomNotRemovedSuccessfully() {
        roomIsAddedToTheList();
        Room room = roomRepository.getAll().get(0);
        roomRepository.delete(room);
        assertNotEquals(roomRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllRooms() {
        roomIsAddedToTheList();
        roomRepository.deleteAll();
        assertEquals(roomRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllRooms() {
        roomIsAddedToTheList();
        roomRepository.deleteAll();
        assertNotEquals(roomRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdateRoomNumberSuccessfully() {
        roomIsAddedToTheList();
        Room room = roomRepository.getAll().get(0);
        int newRoomNumber = 101;
        roomRepository.updateRoomNumber(room, newRoomNumber);
        assertEquals(room.getRoomNumber(), newRoomNumber);
    }

    @Test
    public void expectUpdateNumberOfSeatsSuccessfully() {
        roomIsAddedToTheList();
        Room room = roomRepository.getAll().get(0);
        int newNumberOfSeats = 150;
        roomRepository.updateNumberOfSeats(room, newNumberOfSeats);
        assertEquals(room.getNumberOfSeats(), newNumberOfSeats);
    }

    public void roomIsAddedToTheList() {
        roomRepository.deleteAll();
        Room room = RoomBuilder.buildRoom("1", 100, 120);
        roomRepository.add(room);
    }
}

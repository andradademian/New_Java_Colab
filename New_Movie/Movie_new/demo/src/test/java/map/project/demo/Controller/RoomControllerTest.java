package map.project.demo.Controller;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Controller.RoomController;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.RoomRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

public class RoomControllerTest {
    RoomRepository roomRepository = new RoomRepository();
    RoomController roomController = new RoomController(roomRepository);

    public RoomControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() {
        roomRepository.add(RoomBuilder.buildRoom("1", 100, 120));
        roomRepository.add(RoomBuilder.buildRoom("1", 100, 120));
        roomRepository.add(RoomBuilder.buildRoom("1", 100, 120));
    }

    @Test
    public void shouldFindRoomById() {
        Room foundRoom = roomController.findRoomById("1");

        assertNotNull(foundRoom);
        assertEquals("1", foundRoom.getId());
    }

    @Test
    public void shouldNotFindRoomByInvalidId() {
        Room foundRoom = roomController.findRoomById("4");

        assertNull(foundRoom);
    }
}

package map.project.demo.Controller;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Controller.CinemaController;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.CinemaRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class CinemaControllerTest {
    CinemaRepository cinemaRepository = CinemaRepository.getInstance();
    CinemaController cinemaController = new CinemaController(cinemaRepository);

    @BeforeEach
    public void setUp() {
        cinemaRepository.add(new Cinema("1", "Cinema1", "Address1", new Vector<>()));
        cinemaRepository.add(new Cinema("2", "Cinema2", "Address2", new Vector<>()));
        cinemaRepository.add(new Cinema("3", "Cinema3", "Address3", new Vector<>()));
    }


    @Test
    public void shouldFindCinemaById() {
        String cinemaId = "2";

        Cinema foundCinema = cinemaController.findCinemaById(cinemaId);

        assertNotNull(foundCinema);
        assertEquals(cinemaId, foundCinema.getId());
    }

    @Test
    public void shouldNotFindInvalidCinemaById() {
        String invalidCinemaId = "InvalidId";

        Cinema foundCinema = cinemaController.findCinemaById(invalidCinemaId);

        assertNull(foundCinema);
    }
    @Test
    public void shouldFindRoomById() {
        Cinema cinema = cinemaController.findCinemaById("1");
        assertNotNull(cinema);

        Room roomToAdd = RoomBuilder.buildRoom("1", 100, 120);
        cinemaController.addRoomToCinema(cinema, roomToAdd);

        String roomId = "1";

        Room foundRoom = cinemaController.findRoomById(cinema, roomId);

        assertNotNull(foundRoom);
        assertEquals(roomId, foundRoom.getId());
    }



    @Test
    public void shouldNotFindInvalidRoomById() {
        Cinema cinema = cinemaController.findCinemaById("1");
        String invalidRoomId = "InvalidId";

        Room foundRoom = cinemaController.findRoomById(cinema, invalidRoomId);

        assertNull(foundRoom);
    }



}

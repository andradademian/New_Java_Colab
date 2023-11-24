package map.project.demo.Builder;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuilderTest {

    @Test
    public void testBuildRoom() {
        String id = "101";
        int roomNumber = 1;
        int numberOfSeats = 50;

        Room room = RoomBuilder.buildRoom(id, roomNumber, numberOfSeats);

        assertEquals(id, room.getId());
        assertEquals(roomNumber, room.getRoomNumber());
        assertEquals(numberOfSeats, room.getNumberOfSeats());
    }
}

package map.project.demo.Builder;

import map.project.demo.Domain.Room;

public class RoomBuilder {
    public static Room buildRoom(String id, int roomNumber, int numberOfSeats) {
        return new Room.Builder()
                .setId(id)
                .setRoomNumber(roomNumber)
                .setNumberOfSeats(numberOfSeats)
                .build();
    }
}

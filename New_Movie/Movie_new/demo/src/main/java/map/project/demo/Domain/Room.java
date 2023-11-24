package map.project.demo.Domain;

public class Room {
    private String id;
    private int roomNumber;
    private int numberOfSeats;

    private Room(String id, int roomNumber, int numberOfSeats) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    @Override
    public String toString() {
        return "Room{" +
                "id='" + id + '\'' +
                ", roomNumber=" + roomNumber +
                ", numberOfSeats=" + numberOfSeats +
                '}';
    }

    public static class Builder {
        private String id;
        private int roomNumber;
        private int numberOfSeats;

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setRoomNumber(int roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder setNumberOfSeats(int numberOfSeats) {
            this.numberOfSeats = numberOfSeats;
            return this;
        }

        public Room build() {
            return new Room(id, roomNumber, numberOfSeats);
        }
    }
}

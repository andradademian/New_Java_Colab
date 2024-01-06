package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.ObserverPattern.Observer;


@Entity
@Getter
@Table(name = "Room")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Room {
    @Setter
    @Id
    private String id;

    @Setter
    @Column(name = "roomnumber")
    private int roomNumber;

    @Setter
    @Column(name = "numberofseats")
    private int numberOfSeats;

    public Room() {

    }

    public Room(String id, int roomNumber, int numberOfSeats) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
    }

//    public String getId() {
//        return id;
//    }

//    public void setId(String id) {
//        this.id = id;
//    }

//    public int getRoomNumber() {
//        return roomNumber;
//    }
//
//    public void setRoomNumber(int roomNumber) {
//        this.roomNumber = roomNumber;
//    }
//
//    public int getNumberOfSeats() {
//        return numberOfSeats;
//    }
//
//    public void setNumberOfSeats(int numberOfSeats) {
//        this.numberOfSeats = numberOfSeats;
//    }


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

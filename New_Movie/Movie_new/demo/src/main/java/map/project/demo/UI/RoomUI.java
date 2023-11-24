package map.project.demo.UI;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Controller.RoomController;
import map.project.demo.Domain.Room;

import java.util.Scanner;

public class RoomUI {
    private final RoomController roomController;

    public RoomUI(RoomController roomController) {
        this.roomController = roomController;
    }

    public void mainRoomUI() {
        int choice;
        do {
            this.roomController.printAllRooms();
            roomMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addRoom();
            }
            if (choice == 2) {
                deleteRoom();
            }
            if (choice == 3) {
                roomController.deleteAllRooms();
            }
            if (choice == 4) {
                updateRoom();
            }
        } while (choice > 0 && choice < 5);
    }

    public static void roomMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add a room");
        System.out.println("2. Delete a room");
        System.out.println("3. Delete all rooms");
        System.out.println("4. Update a room");
        System.out.println("5. Exit");
    }

    public void addRoom() {
        this.roomController.printAllRooms();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter room ID:");
        String roomId = keyboard.next();

        System.out.println("Enter room number:");
        int roomNumber = keyboard.nextInt();

        System.out.println("Enter number of seats:");
        int numberOfSeats = keyboard.nextInt();

        this.roomController.addRoom(RoomBuilder.buildRoom(roomId, roomNumber, numberOfSeats));

    }

    public void deleteRoom() {
        this.roomController.printAllRooms();
        System.out.println("Enter room ID of the room you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String roomId = keyboard.next();
        this.roomController.deleteRoom(roomId);
    }

    public void updateRoom(String roomId) {
        Room roomToUpdate = roomController.findRoomById(roomId);
        if (roomToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update room number");
            System.out.println("2. Update number of seats");
            System.out.println("3. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new room number:");
                int newRoomNumber = keyboard.nextInt();
                roomController.updateRoomNumber(roomToUpdate, newRoomNumber);
            } else if (choice == 2) {
                System.out.println("Enter new number of seats:");
                int newNumberOfSeats = keyboard.nextInt();
                roomController.updateNumberOfSeats(roomToUpdate, newNumberOfSeats);
            }
        }
    }


    public void updateRoom() {
        this.roomController.printAllRooms();
        System.out.println("Enter room ID of the room you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String roomId = keyboard.next();
        this.updateRoom(roomId);
    }
}

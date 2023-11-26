package map.project.demo.UI;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Controller.AwardController;
import map.project.demo.Controller.CinemaController;
import map.project.demo.Controller.RoomController;
import map.project.demo.Controller.SpectatorController;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;
import map.project.demo.Domain.Spectator;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class CinemaUI {
    private final CinemaController cinemaController;
    private final SpectatorController spectatorController;
    private final RoomController roomController;

    public CinemaUI(CinemaController cinemaController, SpectatorController spectatorController, RoomController roomController) {
        this.cinemaController = cinemaController;
        this.spectatorController = spectatorController;
        this.roomController = roomController;
    }

    public void mainCinemaUI() throws SQLException {
        int choice;
        do {
            this.cinemaController.printAllCinemas();
            cinemaMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addACinema();
            }
            if (choice == 2) {
                deleteACinema();
            }
            if (choice == 3) {
                cinemaController.deleteAllCinemas();
            }
            if (choice == 4) {
                updateACinema();
            }
            if (choice == 5) {
                addSubscriberToCinema();
            }
            if (choice == 6) {
                notifyAllSubscribers();
            }
        } while (choice > 0 && choice < 7);
    }

    public static void cinemaMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add a cinema");
        System.out.println("2. Delete a cinema");
        System.out.println("3. Delete all cinemas");
        System.out.println("4. Update a cinema");
        System.out.println("5. Add a subscriber to a cinema");
        System.out.println("6. Notify all the spectators subscribed to a cinema");
        System.out.println("7. Exit");
    }

    public void addACinema() {
        this.cinemaController.printAllCinemas();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter ID for cinema:");
        String id = keyboard.next();

        System.out.println("Enter name for cinema:");
        String name = keyboard.next();

        System.out.println("Enter address:");
        String address = keyboard.next();

        this.cinemaController.addCinema(new Cinema(id, name, address, new Vector<>()));
    }

    public void deleteACinema() {
        this.cinemaController.printAllCinemas();
        System.out.println("Enter ID of the cinema you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.cinemaController.deleteCinema(id);
    }

    public void updateCinema(String cinemaId) {
        Cinema cinemaToUpdate = cinemaController.findCinemaById(cinemaId);
        if (cinemaToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update name");
            System.out.println("2. Update address");
            System.out.println("3. Add a room");
            System.out.println("4. Delete a room");
            System.out.println("5. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new name:");
                String name = keyboard.next();
                cinemaController.updateCinemaName(cinemaToUpdate, name);
            } else if (choice == 2) {
                System.out.println("Enter new address:");
                String address = keyboard.next();
                cinemaController.updateCinemaAddress(cinemaToUpdate, address);
            } else if (choice == 3) {
                System.out.println(cinemaToUpdate.getListOfRooms().toString());
                System.out.println("Enter id for cinema:");
                String id = keyboard.next();
                System.out.println("Enter room number:");
                int number = keyboard.nextInt();
                System.out.println("Enter number of seats:");
                int seats = keyboard.nextInt();
                Room room = RoomBuilder.buildRoom(id, number, seats);
                cinemaController.addRoomToCinema(cinemaToUpdate, room);
                roomController.addRoom(room);
            } else if (choice == 4) {
                if (!cinemaToUpdate.getListOfRooms().isEmpty()) {
                    System.out.println(cinemaToUpdate.getListOfRooms().toString());
                    System.out.println("Enter id of the room you want to delete");
                    String id = keyboard.next();
                    Room roomToDelete = cinemaController.findRoomById(cinemaToUpdate, id);
                    if (roomToDelete != null) {
                        cinemaController.deleteRoomFromCinema(cinemaToUpdate, roomToDelete);
                    }
                } else {
                    System.out.println("No rooms in the list");
                }
            }
        }
    }


    public void addSubscriberToCinemaWithId(String cinemaId) throws SQLException {
        Cinema cinemaToUpdate = cinemaController.findCinemaById(cinemaId);
        if (cinemaToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Add an existing spectator");
            System.out.println("2. Add a new spectator");
            Scanner keyboard = new Scanner(System.in);
            Integer choice = keyboard.nextInt();
            if (choice == 1) {
                spectatorController.printAllSpectators();
                System.out.println("Enter Id of the spectator you want to subscribe:");
                String id = keyboard.next();
                Spectator spectator = spectatorController.findSpectatorById(id);
                if (spectator != null) {
                    cinemaToUpdate.addObserver(spectator);
                }
            }
            if (choice == 2) {
                spectatorController.printAllSpectators();
                System.out.println("Enter spectator ID:");
                String spectatorId = keyboard.next();

                System.out.println("Enter first name:");
                String firstName = keyboard.next();

                System.out.println("Enter last name:");
                String lastName = keyboard.next();

                Spectator newSpectator = new Spectator(spectatorId, firstName, lastName);
                cinemaToUpdate.addObserver(newSpectator);
                spectatorController.addSpectator(newSpectator);
            }
        }
    }

    public void notifyAllSubscribersOfTheCinemaWithId(String cinemaId) {
        Cinema cinemaToUpdate = cinemaController.findCinemaById(cinemaId);
        if (cinemaToUpdate != null) {
            System.out.println("Enter notification message:");
            Scanner keyboard = new Scanner(System.in);
            String message = keyboard.next();
            cinemaToUpdate.notifyObservers(message);
        }
    }


    public void updateACinema() {
        this.cinemaController.printAllCinemas();
        System.out.println("Enter ID of the award you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.updateCinema(id);
    }

    public void addSubscriberToCinema() throws SQLException {
        this.cinemaController.printAllCinemas();
        System.out.println("Enter id of the cinema to which you want to add a subscriber:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.addSubscriberToCinemaWithId(id);
    }

    public void notifyAllSubscribers() {
        this.cinemaController.printAllCinemas();
        System.out.println("Enter id of the cinema hat wants to notify its subscribers:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.notifyAllSubscribersOfTheCinemaWithId(id);
    }
}

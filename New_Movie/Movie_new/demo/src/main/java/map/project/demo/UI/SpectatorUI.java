//package map.project.demo.UI;
//
//import map.project.demo.Controller.SpectatorController;
//import map.project.demo.Controller.TicketController;
//import map.project.demo.Domain.Spectator;
//import map.project.demo.Domain.Ticket;
//
//import java.sql.SQLException;
//import java.util.Scanner;
//import java.util.Vector;
//
//public class SpectatorUI {
//    private final SpectatorController spectatorController;
//    private final TicketController ticketController;
//
//    public SpectatorUI(SpectatorController spectatorController, TicketController ticketController) {
//        this.spectatorController = spectatorController;
//        this.ticketController = ticketController;
//    }
//
//    public void mainSpectatorUI() throws SQLException {
//        int choice;
//        do {
//            this.spectatorController.printAllSpectators();
//            spectatorMenu();
//            Scanner keyboard = new Scanner(System.in);
//            System.out.println("Your choice:");
//            choice = keyboard.nextInt();
//
//            switch (choice) {
//                case 1:
//                    addSpectator();
//                    break;
//                case 2:
//                    deleteSpectator();
//                    break;
//                case 3:
//                    spectatorController.deleteAllSpectators();
//                    break;
//                case 4:
//                    updateSpectator();
//                    break;
//            }
//        } while (choice > 0 && choice < 5);
//    }
//
//    public static void spectatorMenu() {
//        System.out.println("What do you want to do?");
//        System.out.println("1. Add a spectator");
//        System.out.println("2. Delete a spectator");
//        System.out.println("3. Delete all spectators");
//        System.out.println("4. Update a spectator");
//        System.out.println("5. Exit");
//    }
//
//    public void addSpectator() throws SQLException {
//        this.spectatorController.printAllSpectators();
//
//        Scanner keyboard = new Scanner(System.in);
//
//        System.out.println("Enter spectator ID:");
//        String spectatorId = keyboard.next();
//
//        System.out.println("Enter first name:");
//        String firstName = keyboard.next();
//
//        System.out.println("Enter last name:");
//        String lastName = keyboard.next();
//
//        ticketController.printAllTickets();
//        System.out.println("Enter id of the ticket:");
//        String ticketId = keyboard.next();
//        Ticket ticket = ticketController.findTicketById(ticketId);
//        if (ticket != null) {
//            Spectator newSpectator = new Spectator(spectatorId, firstName, lastName);
//            newSpectator.setTicket(ticket);
//            spectatorController.addSpectator(newSpectator);
//        } else {
//            System.out.println("No valid ticket id, could not create spectator");
//        }
//    }
//
//    public void deleteSpectator() throws SQLException {
//        //this.spectatorController.printAllSpectators();
//        System.out.println("Enter spectator ID of the spectator you want to delete:");
//        Scanner keyboard = new Scanner(System.in);
//        String spectatorId = keyboard.next();
//        spectatorController.deleteSpectator(spectatorId);
//    }
//
//    public void updateSpectator(String spectatorId) {
//        Spectator spectatorToUpdate = spectatorController.findSpectatorById(spectatorId);
//        if (spectatorToUpdate != null) {
//            System.out.println("What do you want to do?");
//            System.out.println("1. Update first name");
//            System.out.println("2. Update last name");
//            System.out.println("3. Nothing");
//            System.out.println("Enter your choice:");
//            Scanner keyboard = new Scanner(System.in);
//            int choice = keyboard.nextInt();
//            if (choice == 1) {
//                System.out.println("Enter new first name:");
//                String firstName = keyboard.next();
//                spectatorController.updateSpectatorFirstName(spectatorToUpdate, firstName);
//            } else if (choice == 2) {
//                System.out.println("Enter new last name:");
//                String lastName = keyboard.next();
//                spectatorController.updateSpectatorLastName(spectatorToUpdate, lastName);
//            }
//        }
//    }
//
//    public void updateSpectator() {
//        this.spectatorController.printAllSpectators();
//        System.out.println("Enter spectator ID of the spectator you want to update:");
//        Scanner keyboard = new Scanner(System.in);
//        String spectatorId = keyboard.next();
//
//        Spectator spectatorToUpdate = spectatorController.findSpectatorById(spectatorId);
//
//        if (spectatorToUpdate != null) {
//            System.out.println("What do you want to do?");
//            System.out.println("1. Update first name");
//            System.out.println("2. Update last name");
//            System.out.println("3. Nothing");
//            System.out.println("Enter your choice:");
//
//            int choice = keyboard.nextInt();
//            if (choice == 1) {
//                System.out.println("Enter new first name:");
//                String firstName = keyboard.next();
//                spectatorController.updateSpectatorFirstName(spectatorToUpdate, firstName);
//            } else if (choice == 2) {
//                System.out.println("Enter new last name");
//                String lastName = keyboard.next();
//                spectatorController.updateSpectatorLastName(spectatorToUpdate, lastName);
//            }
//        }
//    }
//}

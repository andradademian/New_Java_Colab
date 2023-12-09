//package map.project.demo.UI;
//
//import map.project.demo.Controller.MovieController;
//import map.project.demo.Controller.ScreeningController;
//import map.project.demo.Controller.SpectatorController;
//import map.project.demo.Controller.TicketController;
//import map.project.demo.Domain.*;
//import map.project.demo.Strategy.Screening;
//
//import java.sql.SQLException;
//import java.sql.SQLOutput;
//import java.sql.Time;
//import java.util.Objects;
//import java.util.Scanner;
//import java.util.Vector;
//
//public class TicketUI {
//    private final TicketController ticketController;
//    private final SpectatorController spectatorController;
//    private final ScreeningController screeningController;
//
//    public TicketUI(TicketController ticketController, SpectatorController spectatorController, ScreeningController screeningController) {
//        this.ticketController = ticketController;
//        this.spectatorController = spectatorController;
//        this.screeningController = screeningController;
//    }
//
//    public void mainTicketUI() throws SQLException {
//        int choice;
//        do {
//            this.ticketController.printAllTickets();
//            ticketMenu();
//            Scanner keyboard = new Scanner(System.in);
//            System.out.println("Your choice:");
//            choice = keyboard.nextInt();
//            if (choice == 1) {
//                addTicket();
//            }
//            if (choice == 2) {
//                deleteTicket();
//            }
//            if (choice == 3) {
//                ticketController.deleteAllTickets();
//            }
//            if (choice == 4) {
//                updateTicket();
//            }
//            if (choice == 5) {
//                addDiscountOnTicket();
//            }
//        } while (choice > 0 && choice < 6);
//    }
//
//    public static void ticketMenu() {
//        System.out.println("What do you want to do?");
//        System.out.println("1. Add a ticket");
//        System.out.println("2. Delete a ticket");
//        System.out.println("3. Delete all tickets");
//        System.out.println("4. Update a ticket");
//        System.out.println("5. Add discount based on screening");
//        System.out.println("6. Exit");
//    }
//
//    public void addTicket() throws SQLException {
//        this.ticketController.printAllTickets();
//        Scanner keyboard = new Scanner(System.in);
//        Screening screening;
//        System.out.println("Enter ticket ID:");
//        String ticketId = keyboard.next();
//
//        System.out.println("Screenings:");
//        screeningController.printAllScreenings();
//        System.out.println("Choose the id of the screening:");
//        String screeningId = keyboard.next();
//        screening = screeningController.findScreeningById(screeningId);
//
//        System.out.println("Enter seat number:");
//        int seatNumber = keyboard.nextInt();
//
//        System.out.println("Enter the price for the ticket:");
//        float price = keyboard.nextFloat();
//
////        System.out.println("What do you want to do?");
////        System.out.println("1. Add new spectator");
////        System.out.println("2. Add an existing spectator");
////        int choice = keyboard.nextInt();
////        Spectator spectator;
////        if (choice == 1) {
////            System.out.println("Enter id of the spectator:");
////            String spectatorId = keyboard.next();
////
////            System.out.println("Enter first name of the spectator:");
////            String firstName = keyboard.next();
////
////            System.out.println("Enter last name of the spectator:");
////            String lastName = keyboard.next();
////            spectator = new Spectator(spectatorId, firstName, lastName);
////            spectatorController.addSpectator(spectator);
////        } else {
////            spectatorController.printAllSpectators();
////            System.out.println("Enter id of the spectator:");
////            String specId = keyboard.next();
////            spectator = spectatorController.findSpectatorById(specId);
////        }
//        this.ticketController.addTicket(new Ticket(ticketId, screening, price, seatNumber));
//    }
//
//    public void deleteTicket() {
//        this.ticketController.printAllTickets();
//        System.out.println("Enter ticket ID of the ticket you want to delete:");
//        Scanner keyboard = new Scanner(System.in);
//        String ticketId = keyboard.next();
//        this.ticketController.deleteTicket(ticketId);
//    }
//
//    public void updateTicket() {
//        this.ticketController.printAllTickets();
//        System.out.println("Enter ticket ID of the ticket you want to update:");
//        Scanner keyboard = new Scanner(System.in);
//        String ticketId = keyboard.next();
//
//        System.out.println("What do you want to update?");
//        System.out.println("1. Update price");
//        System.out.println("2. Update seat number");
//        System.out.println("3. Nothing");
//        System.out.println("Enter your choice:");
//
//        int choice = keyboard.nextInt();
//        if (choice == 1) {
//            System.out.println("Enter new price:");
//            float price = keyboard.nextFloat();
//            ticketController.updateTicketPrice(ticketId, price);
//        } else if (choice == 2) {
//            System.out.println("Enter new seat number:");
//            int seatNumber = keyboard.nextInt();
//            ticketController.updateTicketSeatNumber(ticketId, seatNumber);
//        }
//    }
//
//    public void addDiscountOnTicket() {
//        System.out.println("Enter ticket ID of the ticket you want change:");
//        Scanner keyboard = new Scanner(System.in);
//        String ticketId = keyboard.next();
//        Ticket ticket = ticketController.findTicketById(ticketId);
//        ticket.setPrice(ticket.getScreening().applyDiscount(ticket.getPrice()));
//        System.out.println("Discount applied successfully!");
//    }
//}
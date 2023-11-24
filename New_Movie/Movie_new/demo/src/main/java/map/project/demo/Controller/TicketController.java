package map.project.demo.Controller;

import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.TicketRepository;

import java.util.Vector;

public class TicketController {
    private final TicketRepository ticketRepo;

    public TicketController(TicketRepository ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    public void addTicket(Ticket ticket) {
        ticketRepo.add(ticket);
    }

    public void deleteTicket(String ticketId) {
        Ticket ticketToDelete = findTicketById(ticketId);
        if (ticketToDelete != null) {
            ticketRepo.delete(ticketToDelete);
        }
    }

    public void deleteAllTickets() {
        ticketRepo.deleteAll();
    }

    public void updateTicketPrice(String ticketId, float price) {
        Ticket ticketToUpdate = findTicketById(ticketId);
        if (ticketToUpdate != null) {
            ticketRepo.updatePrice(ticketToUpdate, price);
        }
    }

    public void updateTicketSeatNumber(String ticketId, int seatNumber) {
        Ticket ticketToUpdate = findTicketById(ticketId);
        if (ticketToUpdate != null) {
            ticketRepo.updateSeatNumber(ticketToUpdate, seatNumber);
        }
    }

    public Vector<Ticket> getAllTickets() {
        return ticketRepo.getAll();
    }

    public void printAllTickets() {
        ticketRepo.printAll();
    }

    public Ticket findTicketById(String ticketId) {
        for (Ticket ticket : ticketRepo.getAll()) {
            if (ticket.getId().equals(ticketId)) {
                return ticket;
            }
        }
        System.out.println("No ticket with that ID");
        return null;
    }
}

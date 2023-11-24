package map.project.demo.Repository;

import map.project.demo.Domain.Ticket;

import java.util.Vector;

public class TicketRepository {
    private static TicketRepository instance;
    private final Vector<Ticket> tickets;

    public TicketRepository() {
        tickets = new Vector<>();
    }
    public static TicketRepository getInstance() {
        if (instance == null) {
            instance = new TicketRepository();
        }
        return instance;
    }
    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    public void delete(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void deleteAll() {
        tickets.clear();
    }

    public void printAll() {
        System.out.println(tickets);
    }

    public void updatePrice(Ticket ticket, float price) {
        tickets.get(getAll().indexOf(ticket)).setPrice(price);
    }

    public void updateSeatNumber(Ticket ticket, int seatNumber) {
        tickets.get(getAll().indexOf(ticket)).setSeatNumber(seatNumber);
    }

    public Vector<Ticket> getAll() {
        return this.tickets;
    }
}

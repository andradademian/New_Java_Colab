package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Repository.TicketRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TicketRepoTest {
    TicketRepository ticketRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        ticketRepository = TicketRepository.getInstance();
    }

    @Test
    public void expectTicketAddedSuccessfully() {
        ticketIsAddedToTheList();
        assertEquals(ticketRepository.getAll().size(), 1);
    }

    @Test
    public void expectTicketNotAddedSuccessfully() {
        ticketIsAddedToTheList();
        assertNotEquals(ticketRepository.getAll().size(), 0);
    }

    @Test
    public void expectTicketRemovedSuccessfully() {
        ticketIsAddedToTheList();
        Ticket ticket = ticketRepository.getAll().get(0);
        ticketRepository.delete(ticket);
        assertEquals(ticketRepository.getAll().size(), 0);
    }

    @Test
    public void expectTicketNotRemovedSuccessfully() {
        ticketIsAddedToTheList();
        Ticket ticket = ticketRepository.getAll().get(0);
        ticketRepository.delete(ticket);
        assertNotEquals(ticketRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllTickets() {
        ticketIsAddedToTheList();
        ticketRepository.deleteAll();
        assertEquals(ticketRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllTickets() {
        ticketIsAddedToTheList();
        ticketRepository.deleteAll();
        assertNotEquals(ticketRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdatePriceSuccessfully() {
        ticketIsAddedToTheList();
        Ticket ticket = ticketRepository.getAll().get(0);
        float newPrice = 25.0f;
        ticketRepository.updatePrice(ticket, newPrice);
        assertEquals(ticket.getPrice(), newPrice, 0.01);
    }

    @Test
    public void expectUpdateSeatNumberSuccessfully() {
        ticketIsAddedToTheList();
        Ticket ticket = ticketRepository.getAll().get(0);
        int newSeatNumber = 101;
        ticketRepository.updateSeatNumber(ticket, newSeatNumber);
        assertEquals(ticket.getSeatNumber(), newSeatNumber);
    }

    public void ticketIsAddedToTheList() {
        ticketRepository.deleteAll();
        Movie movie = createMovie();
        Room room = createRoom();
        Time startTime = Time.valueOf("12:00:00");
        Screening2D screening2D = new Screening2D("1", movie, room, startTime);
        Spectator spectator = new Spectator("15", "FirstName", "LastName");

        Ticket ticket = new Ticket("1", screening2D, 42, 8, spectator);
        ticketRepository.add(ticket);
    }

    public Movie createMovie() {
        return new Movie("1", "MovieTitle", 90, new Vector<>(), new Vector<>(), null);
    }

    public Room createRoom() {
        return RoomBuilder.buildRoom("1", 100, 120);
    }


}

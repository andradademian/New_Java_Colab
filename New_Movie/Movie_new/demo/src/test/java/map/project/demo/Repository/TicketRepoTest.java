//package map.project.demo.Repository;
//
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Domain.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.sql.Time;
//import java.util.Vector;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class TicketRepoTest {
//    TicketRepository ticketRepository;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        ticketRepository = new TicketRepository();
//    }
//
//    @Test
//    public void expectTicketAddedSuccessfully() throws SQLException {
//        ticketIsAddedToTheList();
//        assertEquals(ticketRepository.getAll().size(), 1);
//    }
//
//    @Test
//    public void expectTicketNotAddedSuccessfully() throws SQLException {
//        ticketIsAddedToTheList();
//        assertNotEquals(ticketRepository.getAll().size(), 0);
//    }
//
//    @Test
//    public void expectTicketRemovedSuccessfully() throws SQLException {
//        ticketIsAddedToTheList();
//        Ticket ticket = ticketRepository.getAll().get(0);
//        ticketRepository.delete(ticket);
//        assertEquals(ticketRepository.getAll().size(), 0);
//    }
//
//    @Test
//    public void expectTicketNotRemovedSuccessfully() throws SQLException {
//        ticketIsAddedToTheList();
//        Ticket ticket = ticketRepository.getAll().get(0);
//        ticketRepository.delete(ticket);
//        assertNotEquals(ticketRepository.getAll().size(), 1);
//    }
//
//    public void ticketIsAddedToTheList() throws SQLException {
//        ticketRepository.deleteAll();
//        Movie movie = createMovie();
//        Room room = createRoom();
//        Time startTime = Time.valueOf("12:00:00");
//        Screening2D screening2D = new Screening2D("1", movie, room, startTime);
//
//        Ticket ticket = new Ticket("1", screening2D, 42, 8);
//        ticketRepository.add(ticket);
//    }
//
//    public Movie createMovie() {
//        return new Movie("KX", "MovieTitle", 90, new Vector<>(), new Vector<>(), null);
//    }
//
//    public Room createRoom() {
//        return RoomBuilder.buildRoom("1", 100, 120);
//    }
//
//
//}

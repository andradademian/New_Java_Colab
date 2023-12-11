//package map.project.demo.Controller;
//
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Controller.TicketController;
//import map.project.demo.Domain.*;
//import map.project.demo.Repository.TicketRepository;
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
//public class TicketControllerTest {
//    TicketRepository ticketRepository = TicketRepository.getInstance();
//    TicketController ticketController = new TicketController(ticketRepository);
//
//    public TicketControllerTest() throws SQLException {
//    }
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        Ticket ticket1 = new Ticket("1", new Screening3D("1", new Movie("1", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10")), 10, 10);
//        Ticket ticket2 = new Ticket("2", new Screening3D("1", new Movie("1", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10")), 10, 10);
//        Ticket ticket3 = new Ticket("3", new Screening3D("1", new Movie("1", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10")), 10, 10);
//        ticketController.addTicket(ticket1);
//        ticketController.addTicket(ticket2);
//        ticketController.addTicket(ticket3);
//    }
//
//    @Test
//    public void expectCorrectFindingOfTheTicketById() {
//        Ticket ticket = ticketController.findTicketById("1");
//        assertEquals(ticket.getId(), "1");
//    }
//
//    @Test
//    public void expectIncorrectFindingOfTheTicketById() {
//        Ticket ticket = ticketController.findTicketById("1");
//        assertNotEquals(ticket.getId(), "2");
//    }
//
//    @Test
//    public void expectCorrectUpdateOfTheTicketPriceById() {
//        ticketController.updateTicketPrice("1", 1010);
//        Ticket ticket = ticketController.findTicketById("1");
//        assertEquals(ticket.getPrice(), 1010);
//    }
//
//    @Test
//    public void expectIncorrectUpdateOfTheTicketPriceById() {
//        ticketController.updateTicketPrice("1", 1010);
//        Ticket ticket = ticketController.findTicketById("1");
//        assertNotEquals(ticket.getPrice(), 10);
//    }
//
//    @Test
//    public void expectCorrectUpdateOfTheSeatNumberById() {
//        ticketController.updateTicketSeatNumber("1", 11);
//        Ticket ticket = ticketController.findTicketById("1");
//        assertEquals(ticket.getSeatNumber(), 11);
//    }
//
//    @Test
//    public void expectIncorrectUpdateOfTheSeatNumberById() {
//        ticketController.updateTicketSeatNumber("1", 11);
//        Ticket ticket = ticketController.findTicketById("1");
//        assertNotEquals(ticket.getSeatNumber(), 10);
//    }
//}

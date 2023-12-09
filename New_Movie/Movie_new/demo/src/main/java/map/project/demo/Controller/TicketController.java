package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Vector;

@RestController
@RequestMapping("/api/ticket")
@Getter
@Setter
@NoArgsConstructor
public class TicketController {
    @Autowired
    private TicketRepository ticketRepo;

    @PostMapping
    public void addTicket(@RequestBody Ticket ticket) throws SQLException {
        ticketRepo.addTicketToTable(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicketWithId(@PathVariable String id) throws SQLException {
        return ticketRepo.getTicketWithIdFromTable(id);
    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable String ticketId) throws SQLException {
        ticketRepo.deleteTicketWithId(ticketId);
    }

    @DeleteMapping
    public void deleteAllTickets() throws SQLException {
        ticketRepo.deleteAllFromTicketTable();
    }

    @PutMapping("/{ticketId}/price")
    public void updateTicketPrice(@PathVariable String ticketId, @RequestBody float price) throws SQLException {
        ticketRepo.updatePrice(ticketId, price);
    }

    @PutMapping("/{ticketId}/seatNumber")
    public void updateTicketSeatNumber(@PathVariable String ticketId, @RequestBody int seatNumber) throws SQLException {
        ticketRepo.updateSeatNumber(ticketId, seatNumber);
    }

    @GetMapping
    public Vector<Ticket> getAllTickets() throws SQLException {
        return ticketRepo.getTicketsFromTable();
    }
}

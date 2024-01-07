package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.ITicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/ticket")
@Getter
@Setter
@NoArgsConstructor
public class TicketController {
    @Autowired
    private ITicketRepo ticketRepo;

    @PostMapping
    public void addTicket(@RequestBody Ticket ticket) {
        ticketRepo.save(ticket);
    }

    @GetMapping("/{id}")
    public Ticket getTicketWithId(@PathVariable String id) {
        return ticketRepo.findById(id).get();
    }

    @DeleteMapping("/{ticketId}")
    public void deleteTicket(@PathVariable String ticketId) {
        ticketRepo.deleteById(ticketId);
    }

    @DeleteMapping
    public void deleteAllTickets() {
        ticketRepo.deleteAll();
    }

    @PutMapping("/{ticketId}/price")
    public void updateTicketPrice(@PathVariable String ticketId, @RequestBody float price) {
        Ticket ticket = ticketRepo.findById(ticketId).get();
        ticketRepo.deleteById(ticketId);
        ticket.setPrice(price);
        ticketRepo.save(ticket);

    }

    @PutMapping("/{ticketId}/seatNumber")
    public void updateTicketSeatNumber(@PathVariable String ticketId, @RequestBody int seatNumber) {
        Ticket ticket = ticketRepo.findById(ticketId).get();
        ticketRepo.deleteById(ticketId);
        ticket.setSeatNumber(seatNumber);
        ticketRepo.save(ticket);
    }

    public boolean existsWithId(String id) {
        return ticketRepo.existsById(id);
    }

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepo.findAll();
    }
}

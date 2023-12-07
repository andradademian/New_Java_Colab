package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Spectator;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.SpectatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Vector;

@RestController
@RequestMapping("/api/spectator")
@Getter
@Setter
@NoArgsConstructor
public class SpectatorController {
    @Autowired
    private SpectatorRepository spectatorRepo;

    @PostMapping
    public Spectator addSpectator(@RequestBody Spectator spectator) throws SQLException {
        spectatorRepo.addSpectatorToTable(spectator);
        return spectator;
    }

    @GetMapping("/{id}")
    public Spectator findSpectatorById(@PathVariable String id) throws SQLException {
        return spectatorRepo.getSpectatorWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSpectator(@PathVariable String id) throws SQLException {
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllSpectators() throws SQLException {
        spectatorRepo.deleteAllFromSpectatorTable();
    }

    @PutMapping("/{id}")
    public void updateSpectator(@RequestBody Spectator spectator, @PathVariable String id) throws SQLException {
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
        spectator.setId(id);
        spectatorRepo.addSpectatorToTable(spectator);
    }

    @PutMapping("/updateFirstName/{id}")
    public void updateFirstName(@RequestBody String newName, @PathVariable String id) throws SQLException {
        Spectator spectator = spectatorRepo.getSpectatorWithIdFromTable(id);
        spectator.setFirstName(newName);
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
        spectatorRepo.addSpectatorToTable(spectator);
    }

    @PutMapping("/updateLastName/{id}")
    public void updateLastName(@RequestBody String newName, @PathVariable String id) throws SQLException {
        Spectator spectator = spectatorRepo.getSpectatorWithIdFromTable(id);
        spectator.setLastName(newName);
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
        spectatorRepo.addSpectatorToTable(spectator);
    }

    @PutMapping("/{id}/setTicket")
    public void setTicket(@RequestBody Ticket ticket, String id) throws SQLException {
        Spectator spectator = spectatorRepo.getSpectatorWithIdFromTable(id);
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
        spectator.setTicket(ticket);
        spectatorRepo.addSpectatorToTable(spectator);
    }

    @GetMapping
    public Vector<Spectator> getAllSpectators() throws SQLException {
        return spectatorRepo.getSpectatorsFromTable();
    }

}

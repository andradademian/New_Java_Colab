package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Spectator;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.ISpectatorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/spectator")
@Getter
@Setter
@NoArgsConstructor
public class SpectatorController {
    @Autowired
    private ISpectatorRepo spectatorRepo;

    @PostMapping
    public Spectator addSpectator(@RequestBody Spectator spectator) {
        spectatorRepo.save(spectator);
        return spectator;
    }

    public boolean isPresentWithId(String id) {
        return spectatorRepo.existsById(id);
    }

    @GetMapping("/{id}")
    public Spectator findSpectatorById(@PathVariable String id) {
        return spectatorRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteSpectator(@PathVariable String id) {
        spectatorRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllSpectators() {
        spectatorRepo.deleteAll();
    }

    @PutMapping("/{id}")
    public void updateSpectator(@RequestBody Spectator spectator, @PathVariable String id) {
        spectatorRepo.deleteById(id);
        spectator.setId(id);
        spectatorRepo.save(spectator);
    }

    @PutMapping("/updateFirstName/{id}")
    public void updateFirstName(@RequestBody String newName, @PathVariable String id) {
        Spectator spectator = spectatorRepo.findById(id).get();
        spectator.setFirstName(newName);
        spectatorRepo.deleteById(id);
        spectatorRepo.save(spectator);
    }

    @PutMapping("/updateLastName/{id}")
    public void updateLastName(@RequestBody String newName, @PathVariable String id) {
        Spectator spectator = spectatorRepo.findById(id).get();
        spectator.setLastName(newName);
        spectatorRepo.deleteById(id);
        spectatorRepo.save(spectator);
    }

    @PutMapping("/{id}/setTicket")
    public void setTicket(@RequestBody Ticket ticket, @PathVariable String id) {
        Spectator spectator = spectatorRepo.findById(id).get();
        spectatorRepo.deleteById(id);
        spectator.setTicket(ticket);
        spectatorRepo.save(spectator);
    }

    @GetMapping
    public List<Spectator> getAllSpectators() {
        return spectatorRepo.findAll();
    }

}

package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Spectator;
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
//        Spectator spectatorToDelete = findSpectatorById(id);
//        if (spectatorToDelete != null) {
//            spectatorRepo.delete(spectatorToDelete);
//        }
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllSpectators() throws SQLException {
        spectatorRepo.deleteAllFromSpectatorTable();
    }


    //    public void updateSpectatorFirstName(Spectator spectator, String firstName) {
//        spectatorRepo.updateFirstName(spectator, firstName);
//    }
//
//    public void updateSpectatorLastName(Spectator spectator, String lastName) {
//        spectatorRepo.updateLastName(spectator, lastName);
//    }
    @PutMapping("/{id}")
    public void updateSpectator(@RequestBody Spectator spectator, @PathVariable String id) throws SQLException {
        spectatorRepo.deleteSpectatorWithIdFromTable(id);
        spectator.setId(id);
        spectatorRepo.addSpectatorToTable(spectator);
    }

    @GetMapping
    public Vector<Spectator> getAllSpectators() throws SQLException {
        return spectatorRepo.getSpectatorsFromTable();
    }

//    public void printAllSpectators() {
//        spectatorRepo.printAll();
//    }
}

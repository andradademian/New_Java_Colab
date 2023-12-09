package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Screening2D;
import map.project.demo.Domain.Screening3D;
import map.project.demo.Domain.Screening4DX;
import map.project.demo.Repository.ScreeningRepository;
import map.project.demo.Strategy.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

@RestController
@RequestMapping("/api/screening")
@Getter
@Setter
@NoArgsConstructor
public class ScreeningController {
    @Autowired
    private ScreeningRepository screeningRepo;

    @PostMapping("/4DX")
    public void addScreening4DX(@RequestBody Screening4DX screening) throws SQLException {
        screeningRepo.addScreeningToTable(screening);
    }

    @PostMapping("/3D")
    public void addScreening3D(@RequestBody Screening3D screening) throws SQLException {
        screeningRepo.addScreeningToTable(screening);
    }

    @PostMapping("/2D")
    public void addScreening(@RequestBody Screening2D screening) throws SQLException {
        screeningRepo.addScreeningToTable(screening);
    }

    @GetMapping("/{id}")
    public Screening findScreeningById(@PathVariable String id) throws SQLException {
        return screeningRepo.getScreeningWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteScreening(@PathVariable String id) throws SQLException {
        screeningRepo.deleteScreeningWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllScreenings() throws SQLException {
        screeningRepo.deleteAllFromScreeningTable();
    }


    @PutMapping("/{screeningId}/room")
    public void updateScreeningRoom(@PathVariable String screeningId, @RequestBody String roomId) throws SQLException {
        screeningRepo.updateRoom(screeningId, roomId);
    }

    @PutMapping("/{id}/startTime")
    public void updateScreeningStartTime(@PathVariable String id, @RequestBody Time startTime) throws SQLException {
        screeningRepo.updateStartTime(id, startTime);
    }

    @PutMapping("/{id}/movie")
    public void updateScreeningMovie(@PathVariable String id, @RequestBody String movieId) throws SQLException {
        screeningRepo.updateMovie(id, movieId);
    }

    @GetMapping
    public Vector<Screening> getAllScreenings() throws SQLException {
        return screeningRepo.getScreeningsFromTable();
    }
}

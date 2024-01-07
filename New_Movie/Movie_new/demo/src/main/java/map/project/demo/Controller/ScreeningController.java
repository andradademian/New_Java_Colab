package map.project.demo.Controller;

import jdk.dynalink.linker.LinkerServices;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.BaseScreening;
import map.project.demo.Domain.Screening2D;
import map.project.demo.Domain.Screening3D;
import map.project.demo.Domain.Screening4DX;
import map.project.demo.Repository.IScreeningRepo;
import map.project.demo.Strategy.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.sql.Time;
import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/screening")
@Getter
@Setter
@NoArgsConstructor
public class ScreeningController {
    @Autowired
    private IScreeningRepo screeningRepo;

    @PostMapping("/4DX")
    public void addScreening4DX(@RequestBody Screening4DX screening) throws SQLException {
        screeningRepo.save(screening);
    }

    @PostMapping("/3D")
    public void addScreening3D(@RequestBody Screening3D screening) {
        screeningRepo.save(screening);
    }

    @PostMapping("/2D")
    public void addScreening(@RequestBody Screening2D screening) {
        screeningRepo.save(screening);
    }

    @GetMapping("/{id}")
    public Screening findScreeningById(@PathVariable String id) {
        return screeningRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteScreening(@PathVariable String id) {
        screeningRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllScreenings() {
        screeningRepo.deleteAll();
    }

    @GetMapping
    public List<BaseScreening> getAllScreenings() {
        return screeningRepo.findAll();
    }
}

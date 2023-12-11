package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import map.project.demo.Domain.Award;

import map.project.demo.Repository.AwardRepository;
import map.project.demo.AwardFactory.AwardFactory;

import java.sql.SQLException;

import java.util.Vector;

@RestController
@RequestMapping("/api/award")
@Getter
@Setter
@NoArgsConstructor
public class AwardController {
    @Autowired
    private AwardRepository awardRepo;

    @PostMapping
    public void addAward(@RequestBody Award award) throws SQLException {
        awardRepo.addAwardToTable(award);
    }

    @DeleteMapping("/{id}")
    public void deleteAwardWithIdFromTable(@PathVariable String id) throws SQLException {
        awardRepo.deleteAwardWithIdFromTable(id);
    }

    @DeleteMapping
    public void deleteAllAwards() throws SQLException {
        awardRepo.deleteAllFromAwards();
    }

    @GetMapping("/{id}")
    public Award findAwardById(@PathVariable String id) throws SQLException {
        return awardRepo.getAwardWithIdFromTable(id);
    }


    @PutMapping("/{id}/updateAwardCategory")
    public void updateAwardCategory(@PathVariable String id, @RequestBody String category) throws SQLException {
        Award award = awardRepo.getAwardWithIdFromTable(id);
        awardRepo.deleteAwardWithIdFromTable(id);
        award.setCategory(category);
        awardRepo.addAwardToTable(award);

    }

    @GetMapping
    public Vector<Award> getAllAwards() throws SQLException {
        return awardRepo.getAwardsFromTable();
    }

}
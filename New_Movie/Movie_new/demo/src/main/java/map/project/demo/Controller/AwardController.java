package map.project.demo.Controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Actor;
import map.project.demo.Repository.IAwardRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import map.project.demo.Domain.Award;

import map.project.demo.AwardFactory.AwardFactory;

import java.sql.SQLException;

import java.util.List;
import java.util.Vector;

@RestController
@RequestMapping("/api/award")
@Getter
@Setter
@NoArgsConstructor
public class AwardController {
    @Autowired
    private IAwardRepo awardRepo;

    @PostMapping
    public void addAward(@RequestBody Award award) throws SQLException {
        awardRepo.save(award);
    }

//    @PostMapping
//    public void addAward(@RequestBody Award award) {
//        AwardFactory awardFactory = AwardFactory.getInstance();
//        Award newAward = awardFactory.buildAward(award.getType(), award.getId(), award.getCategory());
//        newAward.setType(newAward.getType());
//        awardRepo.save(newAward);
//    }

    @DeleteMapping("/{id}")
    public void deleteAwardWithIdFromTable(@PathVariable String id) {
        awardRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllAwards() {
        awardRepo.deleteAll();
    }

    @GetMapping("/{id}")
    public Award findAwardById(@PathVariable String id) {
        return awardRepo.findById(id).get();
    }


    @PutMapping("/{id}/updateAwardCategory")
    public void updateAwardCategory(@PathVariable String id, @RequestBody String category) {
        Award award = awardRepo.findById(id).get();
        awardRepo.deleteById(id);
        award.setCategory(category);
        awardRepo.save(award);
    }

    @GetMapping
    public List<Award> getAllAwards() {
        return awardRepo.findAll();
    }

}
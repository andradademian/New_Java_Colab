package map.project.demo.Controller;

import map.project.demo.Domain.*;
import map.project.demo.Repository.IStageDirectorRepo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stagedirector")
@Getter
@Setter
@NoArgsConstructor
public class StageDirectorController {
    @Autowired
    private IStageDirectorRepo stageDirectorRepo;

    @PostMapping
    public StageDirector addDirector(@RequestBody StageDirector stageDirector) {
        stageDirectorRepo.save(stageDirector);
        return stageDirector;
    }

    @GetMapping("/{id}")
    public StageDirector findDirectorById(@PathVariable String id) {
        return stageDirectorRepo.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteDirectorWithIdFromTable(@PathVariable String id) {
        stageDirectorRepo.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllDirectors() {
        stageDirectorRepo.deleteAll();
    }

    @DeleteMapping("/{id}/movies")
    public void deleteAllFromMovieDirectorTable(@PathVariable String id) {
        stageDirectorRepo.deleteAll();
    }

    @DeleteMapping("/{id}/awards")
    public void deleteAllFromDirectorAwardTable(@PathVariable String id) {
        stageDirectorRepo.deleteAll();
    }

    @PutMapping("/{id}/updateFirstName")
    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) {
        StageDirector stageDirector = stageDirectorRepo.findById(id).get();
        stageDirectorRepo.deleteById(id);
        stageDirector.setFirstName(firstName);
        stageDirectorRepo.save(stageDirector);
    }

    @PutMapping("/{id}/updateLastName")
    public void updateLastName(@PathVariable String id, @RequestBody String lastName) {
        StageDirector stageDirector = stageDirectorRepo.findById(id).get();
        stageDirectorRepo.deleteById(id);
        stageDirector.setLastName(lastName);
        stageDirectorRepo.save(stageDirector);
    }

    @DeleteMapping("/{stagedirectorId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String stagedirectorId, @PathVariable String movieId) {
        StageDirector stageDirector = stageDirectorRepo.findById(stagedirectorId).get();
        stageDirectorRepo.deleteById(stagedirectorId);
        stageDirector.deleteMovie(movieId);
        stageDirectorRepo.save(stageDirector);
    }


    @PostMapping("{stagedirectorId}/movies")
    public void addMovie(@PathVariable String stagedirectorId, @RequestBody String movieId) {
        StageDirector stageDirector = stageDirectorRepo.findById(stagedirectorId).get();
        stageDirectorRepo.deleteById(stagedirectorId);
        stageDirector.addMovie(movieId);
        stageDirectorRepo.save(stageDirector);
    }

    @PostMapping("{stagedirectorId}/awards")
    public void addAward(@PathVariable String stagedirectorId, @RequestBody String awardId) {
        StageDirector stageDirector = stageDirectorRepo.findById(stagedirectorId).get();
        stageDirectorRepo.deleteById(stagedirectorId);
        stageDirector.addAward(awardId);
        stageDirectorRepo.save(stageDirector);
    }

    @DeleteMapping("/{stagedirectorId}/awards/{awardId}")
    public void deleteAward(@PathVariable String stagedirectorId, @PathVariable String awardId) {
        StageDirector stageDirector = stageDirectorRepo.findById(stagedirectorId).get();
        stageDirectorRepo.deleteById(stagedirectorId);
        stageDirector.deleteAward(awardId);
        stageDirectorRepo.save(stageDirector);
    }

    public boolean findMovieById(StageDirector stageDirector, String id) {
        if (stageDirector.getListOfMovies().contains(id)) {
            return true;
        }
        System.out.println("No movie with that id");
        return false;
    }

    public boolean findAwardById(StageDirector stageDirector, String id) {
        if (stageDirector.getAwards().contains(id)) {
            return true;
        }
        System.out.println("No award with that id");
        return false;
    }

    @GetMapping
    public List<StageDirector> getAllDirectors() {
        return stageDirectorRepo.findAll();
    }
}

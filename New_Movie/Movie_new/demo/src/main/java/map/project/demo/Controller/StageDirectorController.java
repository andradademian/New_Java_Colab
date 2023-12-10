package map.project.demo.Controller;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;
import map.project.demo.Repository.StageDirectorRepository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Domain.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

@RestController
@RequestMapping("/api/stagedirector")
@Getter
@Setter
@NoArgsConstructor
public class StageDirectorController {
    @Autowired
    private StageDirectorRepository stageDirectorRepo;

    @PostMapping
    public void addDirector(@RequestBody StageDirector stageDirector) throws SQLException {
        stageDirectorRepo.addDirectorToTable(stageDirector);
    }

    @GetMapping("/{id}")
    public StageDirector findDirectorById(@PathVariable String id) throws SQLException {
        return stageDirectorRepo.getDirectorWithIdFromTable(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDirectorWithIdFromTable(@PathVariable String id) throws SQLException {
        stageDirectorRepo.deleteDirectorWithIdFromTable(id);
    }
    @DeleteMapping
    public void deleteAllDirectors() throws SQLException {
        stageDirectorRepo.deleteAllFromDirectorTable();
    }

    @DeleteMapping("/movies")
    public void deleteAllFromMovieDirectorTable() throws SQLException {
        stageDirectorRepo.deleteAllFromMovieDirectorTable();
    }

    @DeleteMapping("/awards")
    public void deleteAllFromDirectorAwardTable() throws SQLException {
        stageDirectorRepo.deleteAllFromDirectorAwardTable();
    }

    @PutMapping("/{id}/updateFirstName")
    public void updateFirstName(@PathVariable String id, @RequestBody String firstName) throws SQLException {
        StageDirector stageDirector = stageDirectorRepo.getDirectorWithIdFromTable(id);
        stageDirectorRepo.deleteDirectorWithIdFromTable(id);
        stageDirector.setFirstName(firstName);
        stageDirectorRepo.addDirectorToTable(stageDirector);
    }

    @PutMapping("/{id}/updateLastName")
    public void updateLastName(@PathVariable String id, @RequestBody String lastName) throws SQLException {
        StageDirector stageDirector = stageDirectorRepo.getDirectorWithIdFromTable(id);
        stageDirectorRepo.deleteDirectorWithIdFromTable(id);
        stageDirector.setLastName(lastName);
        stageDirectorRepo.addDirectorToTable(stageDirector);
    }

    @DeleteMapping("/{stagedirectorId}/movies/{movieId}")
    public void deleteMovie(@PathVariable String stagedirectorId, @PathVariable String movieId) throws SQLException {
        stageDirectorRepo.deleteMovie(stagedirectorId, movieId);
    }


    @PostMapping("{stagedirectorId}/movies")
    public void addMovie(@PathVariable String stagedirectorId, @RequestBody String movieId) throws SQLException {
        stageDirectorRepo.addMovie(stagedirectorId, movieId);
    }

    @PostMapping("{stagedirectorId}/awards")
    public void addAward(@PathVariable String stagedirectorId, @RequestBody String awardId) throws SQLException {
        stageDirectorRepo.addAward(stagedirectorId, awardId);
    }

    @DeleteMapping("/{stagedirectorId}/awards/{awardId}")
    public void deleteAward(@PathVariable String stagedirectorId, @PathVariable String awardId) throws SQLException {
        stageDirectorRepo.deleteAward(stagedirectorId, awardId);
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
    public Vector<StageDirector> getAllDirectors() throws SQLException {
        return stageDirectorRepo.getDirectorsFromTable();
    }
}

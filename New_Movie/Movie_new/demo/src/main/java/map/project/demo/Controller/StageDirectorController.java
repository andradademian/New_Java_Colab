package map.project.demo.Controller;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;
import map.project.demo.Repository.StageDirectorRepository;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class StageDirectorController {
    private final StageDirectorRepository stageDirectorRepo;

    public StageDirectorController(StageDirectorRepository stageDirectorRepo) {
        this.stageDirectorRepo = stageDirectorRepo;
    }

    public void addStageDirector(StageDirector stageDirector) {
        stageDirectorRepo.add(stageDirector);
    }

    public StageDirector findStageDirectorById(String stageDirectorId) {
        for (StageDirector stageDirector : stageDirectorRepo.getAll()) {
            if (stageDirector.getId().equals(stageDirectorId)) {
                return stageDirector;
            }
        }
        System.out.println("No stage director with that ID");
        return null;
    }

    public void deleteStageDirector(StageDirector stageDirector) {
        stageDirectorRepo.delete(stageDirector);
    }

    public void deleteAllStageDirectors() {
        stageDirectorRepo.deleteAll();
    }


    public void updateStageDirectorFirstName(StageDirector stageDirector, String firstName) {
        stageDirectorRepo.updateFirstName(stageDirector, firstName);
    }

    public void updateStageDirectorLastName(StageDirector stageDirector, String lastName) {
        stageDirectorRepo.updateLastName(stageDirector, lastName);
    }

    public void deleteMovie(StageDirector stageDirector, String movie) {
        stageDirectorRepo.deleteMovie(stageDirector, movie);
    }

    public void addMovie(StageDirector stageDirector, String movie) {
        stageDirectorRepo.addMovie(stageDirector, movie);
    }

    public void addAward(StageDirector stageDirector, String award) {
        stageDirectorRepo.addAward(stageDirector, award);
    }

    public void deleteAward(StageDirector stageDirector, String award) {
        stageDirectorRepo.deleteAward(stageDirector, award);
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

    public Vector<StageDirector> getAllStageDirectors() {
        return stageDirectorRepo.getAll();
    }

    public void printAllStageDirectors() {
        stageDirectorRepo.printAll();
    }
}

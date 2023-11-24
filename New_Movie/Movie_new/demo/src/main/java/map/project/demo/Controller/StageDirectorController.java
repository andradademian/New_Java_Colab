package map.project.demo.Controller;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;
import map.project.demo.Repository.StageDirectorRepository;

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

    public void deleteStageDirector(String stageDirectorId) {
        StageDirector stageDirectorToDelete = findStageDirectorById(stageDirectorId);
        if (stageDirectorToDelete != null) {
            stageDirectorRepo.delete(stageDirectorToDelete);
        }
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

    public void deleteMovie(StageDirector stageDirector, Movie movie) {
        stageDirectorRepo.deleteMovie(stageDirector, movie);
    }

    public void addMovie(StageDirector stageDirector, Movie movie) {
        stageDirectorRepo.addMovie(stageDirector, movie);
    }

    public void addAward(StageDirector stageDirector, Award award) {
        stageDirectorRepo.addAward(stageDirector, award);
    }

    public void deleteAward(StageDirector stageDirector, Award award) {
        stageDirectorRepo.deleteAward(stageDirector, award);
    }

    public Movie findMovieById(StageDirector stageDirector, String id) {
        for (Movie movie : stageDirector.getListOfMovies()) {
            if (Objects.equals(movie.getId(), id)) {
                return movie;
            }
        }
        System.out.println("No movie with that id");
        return null;
    }

    public Award findAwardById(StageDirector stageDirector, String id) {
        for (Award award : stageDirector.getAwards()) {
            if (Objects.equals(award.getId(), id)) {
                return award;
            }
        }
        System.out.println("No award with that id");
        return null;
    }

    public Vector<StageDirector> getAllStageDirectors() {
        return stageDirectorRepo.getAll();
    }

    public void printAllStageDirectors() {
        stageDirectorRepo.printAll();
    }
}

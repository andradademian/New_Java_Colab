package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;

import java.util.Vector;

public class StageDirectorRepository {
    private static StageDirectorRepository instance;
    private final Vector<StageDirector> stageDirectors;

    public StageDirectorRepository() {
        stageDirectors = new Vector<>();
    }
    public static StageDirectorRepository getInstance() {
        if (instance == null) {
            instance = new StageDirectorRepository();
        }
        return instance;
    }
    public void add(StageDirector stageDirector) {
        stageDirectors.add(stageDirector);
    }

    public void delete(StageDirector stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    public void deleteAll() {
        stageDirectors.clear();
    }

    public void printAll() {
        System.out.println(stageDirectors);
    }

    public void updateFirstName(StageDirector stageDirector, String firstName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setFirstName(firstName);
    }

    public void updateLastName(StageDirector stageDirector, String lastName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setLastName(lastName);
    }

    public void deleteMovie(StageDirector stageDirector, Movie movie) {
        stageDirectors.get(getAll().indexOf(stageDirector)).deleteMovie(movie);
    }

    public void addMovie(StageDirector stageDirector, Movie movie) {
        stageDirectors.get(getAll().indexOf(stageDirector)).addMovie(movie);
    }

    public void addAward(StageDirector stageDirector, Award award) {
        stageDirectors.get(getAll().indexOf(stageDirector)).addAward(award);
    }

    public void deleteAward(StageDirector stageDirector, Award award) {
        stageDirectors.get(getAll().indexOf(stageDirector)).deleteAward(award);
    }

    public Vector<StageDirector> getAll() {
        return this.stageDirectors;
    }
}

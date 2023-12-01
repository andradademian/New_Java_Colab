package map.project.demo.Domain;

import map.project.demo.Controller.AwardController;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class StageDirector extends Spectator {

    private Vector<String> listOfMovies;
    private Vector<String> awards;

    public StageDirector(String id, String firstName, String lastName, Vector<String> listOfMovies, Vector<String> awards) throws SQLException {
        super(id, firstName, lastName);
        this.listOfMovies = listOfMovies;
        this.awards = awards;
    }

    public Vector<String> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(Vector<String> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public Vector<String> getAwards() {
        return awards;
    }

    public void setAwards(Vector<String> awards) {
        this.awards = awards;
    }

    public void addMovie(String movie) {
        this.listOfMovies.add(movie);
    }

    public void deleteMovie(String movie) {
        this.listOfMovies.remove(movie);
    }

    public void addAward(String award) {
        this.awards.add(award);
    }

    public void deleteAward(String award) {
        this.awards.remove(award);
    }

    public void deleteAllMovies() {
        this.listOfMovies.clear();
    }

    @Override
    public String toString() {
        return "StageDirector{" +
                "ID=" + getId() + '\'' +
                ", First Name=" + getFirstName() + '\'' +
                ", Last Name=" + getLastName() + '\'' +
                ", listOfMovies=" + listOfMovies +
                ", awards=" + awards + '\'' +
                '}';
    }
}

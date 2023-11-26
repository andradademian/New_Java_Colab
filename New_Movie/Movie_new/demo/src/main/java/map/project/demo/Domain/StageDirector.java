package map.project.demo.Domain;

import map.project.demo.Controller.AwardController;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class StageDirector extends Spectator {

    private Vector<Movie> listOfMovies;
    private Vector<Award> awards;

    public StageDirector(String id, String firstName, String lastName, Vector<Movie> listOfMovies, Vector<Award> awards) throws SQLException {
        super(id, firstName, lastName);
        this.listOfMovies = listOfMovies;
        this.awards = awards;
    }

    public Vector<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(Vector<Movie> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public Vector<Award> getAwards() {
        return awards;
    }

    public void setAwards(Vector<Award> awards) {
        this.awards = awards;
    }

    public void addMovie(Movie movie) {
        this.listOfMovies.add(movie);
    }

    public void deleteMovie(Movie movie) {
        this.listOfMovies.remove(movie);
    }

    public void addAward(Award award) {
        this.awards.add(award);
    }

    public void deleteAward(Award award) {
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

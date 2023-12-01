package map.project.demo.Domain;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

public class Actor extends Spectator {
    private Vector<String> listOfMovies;
    private Vector<String> listOfAwards;
    private Date startOfCareer;

    public Actor(String id, String firstName, String lastName, Vector<String> listOfMovies, Date startOfCareer, Vector<String> awards) throws SQLException {
        super(id, firstName, lastName);
        this.listOfMovies = listOfMovies;
        this.startOfCareer = startOfCareer;
        this.listOfAwards = awards;
    }

    public Vector<String> getListOfMovies() {
        return listOfMovies;
    }

    public Vector<String> getListOfAwards() {
        return listOfAwards;
    }

    public void setListOfAwards(Vector<String> listOfAwards) {
        this.listOfAwards = listOfAwards;
    }

    public void addMovie(String movieId) {
        listOfMovies.add(movieId);
    }

    public void removeMovie(String movieId) {
        listOfMovies.remove(movieId);
    }

    public void addAward(String awardId) {
        listOfAwards.add(awardId);
    }

    public void removeAward(String awardId) {
        listOfAwards.remove(awardId);
    }

    public void setListOfMovies(Vector<String> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    public Date getStartOfCareer() {
        return startOfCareer;
    }

    public void setStartOfCareer(Date startOfCareer) {
        this.startOfCareer = startOfCareer;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id='" + getId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", listOfMovies=" + listOfMovies +
                ", listOfAwards=" + listOfAwards +
                ", startOfCareer=" + startOfCareer +
                '}';
    }
}

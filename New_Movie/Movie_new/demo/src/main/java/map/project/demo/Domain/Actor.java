package map.project.demo.Domain;

import java.sql.Date;
//import java.util.Date;
import java.sql.SQLException;
import java.util.Vector;

public class Actor extends Spectator {
    private Vector<Movie> listOfMovies;
    private Vector<Award> listOfAwards;
    private Date startOfCareer;

    public Actor(String id, String firstName, String lastName, Vector<Movie> listOfMovies, Date startOfCareer, Vector<Award> awards) throws SQLException {
        super(id, firstName, lastName);
        this.listOfMovies = listOfMovies;
        this.startOfCareer = startOfCareer;
        this.listOfAwards = awards;
    }

    public Vector<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public Vector<Award> getListOfAwards() {
        return listOfAwards;
    }

    public void setListOfAwards(Vector<Award> listOfAwards) {
        this.listOfAwards = listOfAwards;
    }

    public void addMovie(Movie movie) {
        listOfMovies.add(movie);
    }

    public void removeMovie(Movie movie) {
        listOfMovies.remove(movie);
    }

    public void addAward(Award award) {
        listOfAwards.add(award);
    }

    public void removeAward(Award award) {
        listOfAwards.remove(award);
    }

    public void setListOfMovies(Vector<Movie> listOfMovies) {
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

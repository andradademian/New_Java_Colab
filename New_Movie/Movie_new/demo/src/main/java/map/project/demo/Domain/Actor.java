package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Actor")
public class Actor extends Spectator {
    @Id
    private String id;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Getter
    @Column(name = "startofcareer")
    private Date startOfCareer;

    @Getter
    @ElementCollection
    private Vector<String> listOfMovies;

    @Getter
    @ElementCollection
    private Vector<String> listOfAwards;

    public Actor() {

    }

    public Actor(String id, String firstName, String lastName, Vector<String> listOfMovies, Date startOfCareer, Vector<String> awards) {
        super(id, firstName, lastName);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.listOfMovies = listOfMovies;
        this.startOfCareer = startOfCareer;
        this.listOfAwards = awards;
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

package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "actor")
public class Actor extends Spectator {
    @Id
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "startofcareer")
    private Date startOfCareer;

    @ElementCollection
    @CollectionTable(name = "ActorMovie", joinColumns = @JoinColumn(name = "actorid"))
    @Column(name = "movieid")
    private List<String> listOfMovies;

    @ElementCollection
    @CollectionTable(name = "ActorAward", joinColumns = @JoinColumn(name = "actorid"))
    @Column(name = "awardid")
    private List<String> listOfAwards;

    public Actor() {

    }

    public Actor(String id, String firstName, String lastName, List<String> listOfMovies, Date startOfCareer, List<String> awards) {
        super(id, firstName, lastName);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.listOfMovies = listOfMovies;
        this.startOfCareer = startOfCareer;
        this.listOfAwards = awards;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public void setListOfAwards(List<String> listOfAwards) {
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

    public void setListOfMovies(List<String> listOfMovies) {
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

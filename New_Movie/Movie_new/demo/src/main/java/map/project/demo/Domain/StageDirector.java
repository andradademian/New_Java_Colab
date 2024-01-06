package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "StageDirector")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class StageDirector extends Spectator {

    @Id
    private String id;

    @Transient
    @ElementCollection
    @CollectionTable(name = "MovieDirector", joinColumns = @JoinColumn(name = "directorid"))
    @Column(name = "movieid")
    private List<String> listOfMovies;

    @Transient
    @ElementCollection
    @CollectionTable(name = "DirectorAward", joinColumns = @JoinColumn(name = "directorid"))
    @Column(name = "awardid")
    private List<String> awards;

    public StageDirector() {

    }
    public StageDirector(String id, String firstName, String lastName, List<String> listOfMovies, List<String> awards)  {
        super(id, firstName, lastName);
        this.listOfMovies = listOfMovies;
        this.awards = awards;
    }

//    public List<String> getListOfMovies() {
//        return listOfMovies;
//    }

    public void setListOfMovies(List<String> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

//    public List<String> getAwards() {
//        return awards;
//    }

    public void setAwards(List<String> awards) {
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

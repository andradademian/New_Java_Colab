package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "Genre")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Genre {
    @Getter
    @Id
    private String id;
    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Transient
    @ElementCollection
    @CollectionTable(name = "MovieGenre", joinColumns = @JoinColumn(name = "cinemaid"))
    @Column(name = "genreid")
    private List<String> listOfMovies;

    public Genre() {

    }

    public Genre(String id, String name, List<String> listOfMovies) {
        this.id = id;
        this.name = name;
        this.listOfMovies = listOfMovies;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<String> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", listOfMovies=" + listOfMovies +
                '}';
    }

    public void addMovie(String movieId) {
        this.listOfMovies.add(movieId);
    }

    public void deleteMovie(String movieId) {
        this.listOfMovies.remove(movieId);
    }

    public void deleteAllMovies() {
        this.listOfMovies.clear();
    }
}

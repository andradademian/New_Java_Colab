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
@Table(name = "Movie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movie{
    @Id
    private String id;

    @Setter
    @Column(name = "title")
    private String title;

    @Setter
    @Column(name = "durationInMinutes")
    private int durationInMinutes;

    @Transient
    @ElementCollection
    @CollectionTable(name = "MovieDirector", joinColumns = @JoinColumn(name = "movieid"))
    @Column(name = "directorid")
    private List<String> stageDirectors;

    @Transient
    @ElementCollection
    @CollectionTable(name = "ActorMovie", joinColumns = @JoinColumn(name = "movieid"))
    @Column(name = "actorid")
    private List<String> actors;

    @Transient
    @ElementCollection
    @CollectionTable(name = "MovieGenre", joinColumns = @JoinColumn(name = "movieid"))
    @Column(name = "genreid")
    private List<String> genres;

    public Movie(String id, String title, int durationInMinutes, List<String> stageDirectors, List<String> actors, List<String> genres) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.stageDirectors = stageDirectors;
        this.actors = actors;
        this.genres = genres;
    }

    public Movie() {

    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public void setId(String id) {
        this.id = id;
    }



    public void setStageDirectors(List<String> stageDirectors) {
        this.stageDirectors = stageDirectors;
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }

    public void deleteGenre(String genre) {
        genres.remove(genre);
    }

    public void deleteStageDirector(String stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    public void addStageDirector(String stageDirector) {
        stageDirectors.add(stageDirector);
    }

    public void addActor(String actor) {
        actors.add(actor);
    }

    public void deleteActor(String actor) {
        actors.remove(actor);
    }


    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", stageDirectors=" + stageDirectors +
                ", actors=" + actors +
                ", genres=" + genres +
                '}';
    }


    public void showMovie() throws Exception {
        System.out.println("Movie \""+this.title+"\" is showed.");
    }
}

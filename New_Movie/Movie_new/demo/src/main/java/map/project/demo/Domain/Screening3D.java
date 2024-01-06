package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.Adapter.ThreeDScreeningAdapter;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

@Getter
@Setter
@Entity
@DiscriminatorValue("3D")
public class Screening3D extends BaseScreening {
    @Id
    private String id;

    @OneToOne
    private Movie movie;

    @OneToOne
    private Room room;

    @Column(name = "starttime")
    private Time startTime;

    @Column(name = "screeningformat")
    private String format;

    public Screening3D() {

    }

    public Screening3D(String id, Movie movie, Room room, Time startTime) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
        this.format = "3D";
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setTime(Time time) {
        this.startTime = time;
    }

    public Movie getIdMovie() {
        return movie;
    }

    public String getFormat() {
        return "3D";
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setIdRoom(Room room) {
        this.room = room;
    }

    public Time getTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Screening3D{" +
                "id=" + id + '\'' +
                ", movie='" + movie + '\'' +
                ", room='" + room + '\'' +
                ", startTime=" + startTime +
                '}' + '\n';
    }

    @Override
    public float applyDiscount(float ticketPrice) {
        return (float) 0.7 * ticketPrice;
    }

    public void play3D() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("The movie '" + movie.getTitle() + "' is played in 3D format.");
        System.out.println("-------------------------------------------------------------");
    }

    public ThreeDScreeningAdapter createScreeningAdapter() {
        return new ThreeDScreeningAdapter(this);
    }
}

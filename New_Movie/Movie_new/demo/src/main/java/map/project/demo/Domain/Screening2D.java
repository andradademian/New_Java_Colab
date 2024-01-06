package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.Adapter.TwoDScreeningAdapter;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

@Getter
@Entity
@Setter
@DiscriminatorValue("2D")
public class Screening2D extends BaseScreening {
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

    public Screening2D() {

    }

    public Screening2D(String id, Movie movie, Room room, Time startTime) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
        this.format = "2D";
    }

    public String getFormat() {
        return "2D";
    }

    public void setTime(Time time) {
        this.startTime = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getIdMovie() {
        return movie.getId();
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getIdRoom() {
        return room.getId();
    }

    public Time getTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Screening2D{" +
                "id=" + id + '\'' +
                ", movie='" + movie + '\'' +
                ", room='" + room + '\'' +
                ", startTime=" + startTime +
                '}' + '\n';
    }

    @Override
    public float applyDiscount(float ticketPrice) {
        return (float) 0.5 * ticketPrice;
    }

    public void play2D() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("The movie '" + movie.getTitle() + "' is played in 2D format.");
        System.out.println("-------------------------------------------------------------");
    }

    public TwoDScreeningAdapter createScreeningAdapter() {
        return new TwoDScreeningAdapter(this);
    }
}

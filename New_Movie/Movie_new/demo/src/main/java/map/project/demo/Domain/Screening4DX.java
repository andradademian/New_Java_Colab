package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.Adapter.FourDXScreeningAdapter;
import map.project.demo.Decorator.ScreeningDecorator;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

@Getter
@Entity
@Setter
@DiscriminatorValue("4DX")
public class Screening4DX extends ScreeningDecorator {

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

    public Screening4DX() {

    }

    public Screening4DX(String id, Movie movie, Room room, Time startTime) {
        super(id, movie, room, startTime);
        this.id = id;
        this.room = room;
        this.movie = movie;
        this.startTime = startTime;
        this.format = "4DX";
    }

    public void setTime(Time time) {
        this.startTime = time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormat() {
        return "4DX";
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Movie getIdMovie() {
        return movie;
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
        return "Screening4DX{" +
                "id=" + id + '\'' +
                ", movie='" + movie + '\'' +
                ", room='" + room + '\'' +
                ", startTime=" + startTime +
                '}' + '\n';
    }

    @Override
    public float applyDiscount(float ticketPrice) {
        return (float) 0.85 * ticketPrice;
    }

    public void play4DX() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("The movie '" + movie.getTitle() + "' is played in 4DX format.");
        System.out.println("-------------------------------------------------------------");
        moveChairs();
    }

    public FourDXScreeningAdapter createScreeningAdapter() {
        return new FourDXScreeningAdapter(this);
    }

    public void moveChairs() {
        System.out.println("Chairs are moving <3");
    }
}

package map.project.demo.Decorator;

import lombok.Getter;
import map.project.demo.Adapter.FourDXScreeningAdapter;
import map.project.demo.Domain.BaseScreening;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Room;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

@Getter
public abstract class ScreeningDecorator extends BaseScreening {

    protected String id;
    protected Movie movie;
    protected Room room;
    protected Time startTime;

    public ScreeningDecorator() {

    }

    public ScreeningDecorator(String id, Movie movie, Room room, Time startTime) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFormat() {
        return "4DX";
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
        return "Screening{" +
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

//    public void play4DX() {
//        System.out.println("-------------------------------------------------------------");
//        System.out.println("The movie '" + movie.getTitle() + "' is played in 4DX format.");
//        System.out.println("-------------------------------------------------------------");
//    }

}

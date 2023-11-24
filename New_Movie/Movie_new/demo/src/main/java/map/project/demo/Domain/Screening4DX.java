package map.project.demo.Domain;

import map.project.demo.Adapter.FourDXScreeningAdapter;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

public class Screening4DX implements Screening {
    String id;
    private Movie movie;
    private Room room;
    private Time startTime;

    public Screening4DX(String id, Movie movie, Room room, Time startTime) {
        this.id = id;
        this.movie = movie;
        this.room = room;
        this.startTime = startTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Time getStartTime() {
        return startTime;
    }

    public Movie getIdMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return this.room;
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
    }

    public FourDXScreeningAdapter createScreeningAdapter() {
        return new FourDXScreeningAdapter(this);
    }
}

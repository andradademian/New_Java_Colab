package map.project.demo.Strategy;

import map.project.demo.Adapter.ScreeningPlayer;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Room;

import java.sql.Time;

public interface Screening {
    String getId();

    void setId(String id);

    Movie getMovie();

    void setRoom(Room room);

    Time getStartTime();

    Movie getIdMovie();

    String getFormat();

    void setMovie(Movie movie);

    Room getRoom();

    void setIdRoom(Room room);

    Time getTime();

    void setStartTime(Time startTime);

    String toString();

    float applyDiscount(float ticketPrice);

    public ScreeningPlayer createScreeningAdapter();

}

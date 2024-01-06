package map.project.demo.Domain;

import jakarta.persistence.*;
import map.project.demo.Adapter.ScreeningPlayer;
import map.project.demo.Strategy.Screening;

import java.sql.Time;

@Entity(name = "Screening")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "screeningformat",
        discriminatorType = DiscriminatorType.STRING)
public abstract class BaseScreening implements Screening {

    @Override
    @Id
    public String getId() {
        return null;
    }

    @Override
    public void setId(String id) {

    }

    @Override
    @OneToOne
    public Movie getMovie() {
        return null;
    }

    @Override
    public void setRoom(Room room) {

    }


    @Override
    public Time getStartTime() {
        return null;
    }

//    @Override
//    public Movie getIdMovie() {
//        return null;
//    }

    @Override
    public void setTime(Time time) {
        //;
    }

    @Override
    public String getFormat() {
        return null;
    }

    @Override
    public void setMovie(Movie movie) {

    }

    @Override
    @OneToOne
    public Room getRoom() {
        return null;
    }

    @Override
    public void setIdRoom(Room room) {

    }

    @Override
    public Time getTime() {
        return null;
    }

    @Override
    public void setStartTime(Time startTime) {

    }

    @Override
    public float applyDiscount(float ticketPrice) {
        return 0;
    }

    @Override
    public ScreeningPlayer createScreeningAdapter() {
        return null;
    }
}

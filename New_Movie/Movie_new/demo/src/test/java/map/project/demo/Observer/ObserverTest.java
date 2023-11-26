package map.project.demo.Observer;

import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;
import map.project.demo.Domain.Spectator;
import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

public class ObserverTest {
    Observer observer1;
    Observer observer2;
    Observer observer3;
    Observable observable;

    @BeforeEach
    public void setUp() throws SQLException {
        observer1 = new Spectator("1", "Angelina", "Jolie");
        observer2 = new Spectator("2", "Lionel", "Messi");
        observer3 = new Spectator("3", "Taylor", "Swift");
        observable = new Cinema("111", "Cinema City", "Strada Unirii", new Vector<Room>());
    }

    @Test
    public void observerIsSubscribedCorrectly() {
        observable.addObserver(observer1);
        assertEquals(observable.getObservers().size(), 1);
        observable.addObserver(observer3);
        assertEquals(observable.getObservers().size(), 2);
    }

    @Test
    public void observerIsNotSubscribed() {
        observable.addObserver(observer2);
        observable.addObserver(observer1);
        assertNotEquals(observable.getObservers().size(), 0);
        assertNotEquals(observable.getObservers().size(), 1);
    }

}

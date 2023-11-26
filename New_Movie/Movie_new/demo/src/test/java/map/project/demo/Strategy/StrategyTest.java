package map.project.demo.Strategy;

import static org.junit.jupiter.api.Assertions.*;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.sql.Time;
import java.util.Vector;

public class StrategyTest {
    Screening screening2D;
    Screening screening3D;
    Screening screening4DX;
    Ticket ticket1;
    Ticket ticket2;
    Ticket ticket3;

    @BeforeEach
    public void setUp() throws SQLException {
        screening2D = new Screening2D("1", new Movie("1", "Movie1", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 1, 120), Time.valueOf("12:00:00"));
        screening3D = new Screening3D("1", new Movie("2", "Movie2", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 1, 120), Time.valueOf("15:00:00"));
        screening4DX = new Screening4DX("1", new Movie("2", "Movie3", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 1, 120), Time.valueOf("19:00:00"));
        ticket1 = new Ticket("1", screening2D, 100, 10);
        ticket2 = new Ticket("1", screening3D, 100, 10);
        ticket3 = new Ticket("1", screening4DX, 100, 10);
    }

    @Test
    public void discountFor2DTicketSetCorrectly() {
        ticket1.addDiscount();
        assertEquals(ticket1.getPrice(), 50);
    }

    @Test
    public void discountFor2DTicketSetIncorrectly() {
        ticket1.addDiscount();
        assertNotEquals(ticket1.getPrice(), 100);
    }

    @Test
    public void discountFor3DTicketSetCorrectly() {
        ticket2.addDiscount();
        assertEquals(ticket2.getPrice(), 70);
    }

    @Test
    public void discountFor3DTicketSetIncorrectly() {
        ticket2.addDiscount();
        assertNotEquals(ticket2.getPrice(), 100);
    }

    @Test
    public void discountFor4DXTicketSetCorrectly() {
        ticket3.addDiscount();
        assertEquals(ticket3.getPrice(), 85);
    }

    @Test
    public void discountFor4DXTicketSetIncorrectly() {
        ticket3.addDiscount();
        assertNotEquals(ticket3.getPrice(), 100);
    }
}

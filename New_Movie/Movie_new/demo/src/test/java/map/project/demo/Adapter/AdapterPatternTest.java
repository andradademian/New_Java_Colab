package map.project.demo.Adapter;

import map.project.demo.Adapter.FourDXScreeningAdapter;
import map.project.demo.Adapter.ThreeDScreeningAdapter;
import map.project.demo.Adapter.TwoDScreeningAdapter;
import map.project.demo.Builder.RoomBuilder;
import map.project.demo.*;
import org.testng.annotations.Test;
import map.project.demo.Domain.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.Vector;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.*;

public class AdapterPatternTest {
    @Test
    public void testTwoDAdapter() {
        Movie movie = createMovie();
        Room room = createRoom();
        Time startTime = Time.valueOf("12:00:00");
        TwoDScreeningAdapter twoDScreeningAdapter = new TwoDScreeningAdapter(new Screening2D("1", movie, room, startTime));
        assertNotNull(twoDScreeningAdapter);
        twoDScreeningAdapter.play();
    }

    @Test
    public void testThreeDAdapter() {
        Movie movie = createMovie();
        Room room = createRoom();
        Time startTime = Time.valueOf("12:00:00");
        ThreeDScreeningAdapter threeDScreeningAdapter = new ThreeDScreeningAdapter(new Screening3D("1", movie, room, startTime));
        assertNotNull(threeDScreeningAdapter);
        threeDScreeningAdapter.play();
    }

    @Test
    public void testFourDXAdapter() {
        Movie movie = createMovie();
        Room room = createRoom();
        Time startTime = Time.valueOf("12:00:00");
        FourDXScreeningAdapter fourDXScreeningAdapter = new FourDXScreeningAdapter(new Screening4DX("1", movie, room, startTime));
        assertNotNull(fourDXScreeningAdapter);
        fourDXScreeningAdapter.play();
    }

    public Movie createMovie() {
        return new Movie("1", "MovieTitle", 90, new Vector<String>(), new Vector<String>(), new Vector<String>());
    }

    public Room createRoom() {
        return RoomBuilder.buildRoom("1", 100, 120);
    }


}

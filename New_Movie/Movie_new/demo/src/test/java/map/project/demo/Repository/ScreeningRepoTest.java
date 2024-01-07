//package map.project.demo.Repository;
//
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Domain.*;
//import map.project.demo.Strategy.Screening;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.sql.Time;
//import java.util.Vector;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class ScreeningRepoTest {
//    ScreeningRepository screeningRepository;
//    MovieRepository movieRepository;
//    RoomRepository roomRepository;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        screeningRepository = new ScreeningRepository();
//        movieRepository = new MovieRepository();
//        roomRepository = new RoomRepository();
//    }
//
//    @Test
//    public void expectScreeningAddedSuccessfully() throws SQLException {
//        int size = screeningRepository.getScreeningsFromTable().size();
//        screeningIsAddedToTheTable();
//        assertEquals(screeningRepository.getScreeningsFromTable().size(), size + 1);
//        screeningIsRemovedFromTable();
//    }
//
//    @Test
//    public void expectScreeningNotAddedSuccessfully() throws SQLException {
//        int size = screeningRepository.getScreeningsFromTable().size();
//        screeningIsAddedToTheTable();
//        assertNotEquals(screeningRepository.getScreeningsFromTable().size(), size);
//        screeningIsRemovedFromTable();
//    }
//
//    @Test
//    public void expectScreeningRemovedSuccessfully() throws SQLException {
//        int size = screeningRepository.getScreeningsFromTable().size();
//        screeningIsAddedToTheTable();
//        assertEquals(screeningRepository.getScreeningsFromTable().size(), size + 1);
//        screeningIsRemovedFromTable();
//        assertEquals(screeningRepository.getScreeningsFromTable().size(), size);
//    }
//
//    @Test
//    public void expectScreeningNotRemovedSuccessfully() throws SQLException {
//        int size = screeningRepository.getScreeningsFromTable().size();
//        screeningIsAddedToTheTable();
//        assertEquals(screeningRepository.getScreeningsFromTable().size(), size + 1);
//        screeningIsRemovedFromTable();
//        assertNotEquals(screeningRepository.getScreeningsFromTable().size(), size + 1);
//    }
//
//    @Test
//    public void expectUpdateStartTimeSuccessfully() throws SQLException {
//        screeningIsAddedToTheTable();
//        Time newStartTime = Time.valueOf("14:30:00");
//        screeningRepository.updateStartTime("KX", newStartTime);
//        Screening screening = screeningRepository.getScreeningWithIdFromTable("KX");
//        assertEquals(screening.getStartTime(), newStartTime);
//        screeningIsRemovedFromTable();
//    }
//
//    @Test
//    public void expectUpdateStartTimeNotSuccessfully() throws SQLException {
//        screeningIsAddedToTheTable();
//        Time wrongStartTime = Time.valueOf("14:20:00");
//        Time newStartTime = Time.valueOf("14:30:00");
//        screeningRepository.updateStartTime("KX", newStartTime);
//        Screening screening = screeningRepository.getScreeningWithIdFromTable("KX");
//        assertNotEquals(screening.getStartTime(), wrongStartTime);
//        screeningIsRemovedFromTable();
//    }
//
//    public void screeningIsAddedToTheTable() throws SQLException {
//        Movie movie = movieIdAddedToTheTable();
//        Room room = roomIsAddedToTheTable();
//        Time startTime = Time.valueOf("12:00:00");
//        Screening screening = new Screening2D("KX", movie, room, startTime);
//        screeningRepository.addScreeningToTable(screening);
//    }
//
//    public void screeningIsRemovedFromTable() throws SQLException {
//        screeningRepository.deleteScreeningWithIdFromTable("KX");
//        movieRepository.deleteMovieWithIdFromTable("KX");
//        roomRepository.deleteRoomWithIdFromTable("KX");
//    }
//
//    public Movie movieIdAddedToTheTable() throws SQLException {
//        Movie movie = new Movie("KX", "MovieTitle", 90, new Vector<String>(), new Vector<String>(), new Vector<String>());
//        movieRepository.addMovieToTable(movie);
//        return movie;
//    }
//
//    public Room roomIsAddedToTheTable() throws SQLException {
//        Room room = RoomBuilder.buildRoom("KX", 100, 120);
//        roomRepository.addRoomToTable(room);
//        return room;
//    }
//
//}

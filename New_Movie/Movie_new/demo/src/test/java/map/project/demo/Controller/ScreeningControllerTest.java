//package map.project.demo.Controller;
//
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Controller.ScreeningController;
//import map.project.demo.Domain.*;
//import map.project.demo.Repository.ScreeningRepository;
//import map.project.demo.Strategy.Screening;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.sql.Time;
//import java.util.Vector;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class ScreeningControllerTest {
//    ScreeningRepository screeningRepository = ScreeningRepository.getInstance();
//    ScreeningController screeningController = new ScreeningController(screeningRepository);
//
//    public ScreeningControllerTest() throws SQLException {
//    }
//
//    @BeforeEach
//    public void setUp() {
//        Screening screening1 = new Screening2D("111", new Movie("1", "Movie1", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10"));
//        Screening screening2 = new Screening3D("112", new Movie("2", "Movie2", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10"));
//        Screening screening3 = new Screening4DX("113", new Movie("3", "Movie3", 120, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom("1", 100, 120), Time.valueOf("10:10:10"));
//        screeningController.addScreening(screening1);
//        screeningController.addScreening(screening2);
//        screeningController.addScreening(screening3);
//    }
//
//    @Test
//    public void expectCorrectAddingOfTheScreenings() {
//        assertEquals(screeningController.getAllScreenings().size(), 3);
//        screeningController.deleteAllScreenings();
//    }
//
//    @Test
//    public void expectIncorrectAddingOfTheScreenings() {
//        assertNotEquals(screeningController.getAllScreenings().size(), 0);
//        screeningController.deleteAllScreenings();
//    }
//
//    @Test
//    public void expectCorrectFindingOfTheScreeningById() {
//        Screening screening = screeningController.findScreeningById("112");
//        assertEquals(screening.getId(), "112");
//        screeningController.deleteAllScreenings();
//    }
//
//    @Test
//    public void expectIncorrectFindingOfTheScreeningById() {
//        Screening screening = screeningController.findScreeningById("111");
//        assertNotEquals(screening.getId(), "112");
//        screeningController.deleteAllScreenings();
//    }
//
//    @Test
//    public void expectCorrectDeletingOfTheScreeningById() {
//        screeningController.deleteScreening("111");
//        Screening screening = screeningController.findScreeningById("111");
//        assertNull(screening);
//        screeningController.deleteAllScreenings();
//    }
//
//    @Test
//    public void expectIncorrectDeletingOfTheScreeningById() {
//        screeningController.deleteScreening("112");
//        Screening screening = screeningController.findScreeningById("113");
//        assertNotNull(screening);
//        screeningController.deleteAllScreenings();
//    }
//
//
//}

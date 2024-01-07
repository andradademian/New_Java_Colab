//package map.project.demo.Repository;
//
//import map.project.demo.Domain.*;
//import map.project.demo.Repository.StageDirectorRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.util.Vector;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class StageDirectorRepoTest {
//    StageDirectorRepository stageDirectorRepository;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        stageDirectorRepository = new StageDirectorRepository();
//    }
//
//    @Test
//    public void expectStageDirectorAddedSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        assertEquals(stageDirectorRepository.getAll().size(), 1);
//    }
//
//    @Test
//    public void expectStageDirectorNotAddedSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        assertNotEquals(stageDirectorRepository.getAll().size(), 0);
//    }
//
//    @Test
//    public void expectStageDirectorRemovedSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
//        stageDirectorRepository.delete(stageDirector);
//        assertEquals(stageDirectorRepository.getAll().size(), 0);
//    }
//
//    @Test
//    public void expectStageDirectorNotRemovedSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
//        stageDirectorRepository.delete(stageDirector);
//        assertNotEquals(stageDirectorRepository.getAll().size(), 1);
//    }
//
//    @Test
//    public void expectUpdateFirstNameSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
//        String newFirstName = "NewFirstName";
//        stageDirectorRepository.updateFirstName(stageDirector, newFirstName);
//        assertEquals(stageDirector.getFirstName(), newFirstName);
//    }
//
//    @Test
//    public void expectUpdateLastNameSuccessfully() throws SQLException {
//        stageDirectorIsAddedToTheList();
//        StageDirector stageDirector = stageDirectorRepository.getAll().get(0);
//        String newLastName = "NewLastName";
//        stageDirectorRepository.updateLastName(stageDirector, newLastName);
//        assertEquals(stageDirector.getLastName(), newLastName);
//    }
//
//    public void stageDirectorIsAddedToTheList() throws SQLException {
//        stageDirectorRepository.deleteAll();
//        StageDirector stageDirector = new StageDirector("1", "FirstName", "LastName", new Vector<>(), new Vector<>());
//        stageDirectorRepository.add(stageDirector);
//    }
//
//    public Movie createMovie() {
//        return new Movie("1", "MovieTitle", 90, new Vector<>(), new Vector<>(), new Vector<>());
//    }
//}

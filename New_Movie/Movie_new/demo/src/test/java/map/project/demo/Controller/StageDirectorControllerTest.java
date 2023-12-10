package map.project.demo.Controller;

import map.project.demo.Domain.*;
import map.project.demo.Repository.StageDirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class StageDirectorControllerTest {
    StageDirectorRepository stageDirectorRepository = new StageDirectorRepository();
    StageDirectorController stageDirectorController = new StageDirectorController();

    public StageDirectorControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        stageDirectorController.addDirector(new StageDirector("1", "First1", "Last1", new Vector<>(), new Vector<>()));
        stageDirectorController.addDirector(new StageDirector("2", "First1", "Last1", new Vector<>(), new Vector<>()));
        stageDirectorController.addDirector(new StageDirector("3", "First1", "Last1", new Vector<>(), new Vector<>()));
    }

    @Test
    public void expectCorrectFindingOfTheDirectorById() throws SQLException {
        StageDirector stageDirector = stageDirectorController.findDirectorById("1");
        assertEquals(stageDirector.getId(), "1");
    }

    @Test
    public void expectIncorrectFindingOfTheDirectorById() throws SQLException {
        StageDirector stageDirector = stageDirectorController.findDirectorById("1");
        assertNotEquals(stageDirector.getId(), "2");
    }

    @Test
    public void expectCorrectFindingOfTheMovieById() throws SQLException {
        stageDirectorRepository.addMovie(String.valueOf(stageDirectorController.getAllDirectors().get(0)), "111");
        boolean movieExists = stageDirectorController.findMovieById(stageDirectorController.getAllDirectors().get(0), "111");
        assertTrue(movieExists);
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() throws SQLException {
        stageDirectorController.addMovie(String.valueOf(stageDirectorController.getAllDirectors().get(0)), "111");
        boolean movieExists = stageDirectorController.findMovieById(stageDirectorController.getAllDirectors().get(0), "112");
        assertFalse(movieExists);
    }

    @Test
    public void expectIncorrectFindingOfTheAwardById() throws SQLException {
        stageDirectorRepository.addAward(stageDirectorController.getAllDirectors().get(0), "111");
        boolean award = stageDirectorController.findAwardById(stageDirectorController.getAllDirectors().get(0), "112");
        assertFalse(award);
    }

    @Test
    public void expectCorrectFindingOfTheAwardById() throws SQLException {
        stageDirectorRepository.addAward(stageDirectorController.getAllDirectors().get(0), "111");
        boolean award = stageDirectorController.findAwardById(stageDirectorController.getAllDirectors().get(0), "111");
        assertTrue(award);
    }
}

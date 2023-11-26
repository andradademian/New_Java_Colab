package map.project.demo.Controller;

import map.project.demo.Controller.StageDirectorController;
import map.project.demo.Domain.*;
import map.project.demo.Repository.StageDirectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class StageDirectorControllerTest {
    StageDirectorRepository stageDirectorRepository = StageDirectorRepository.getInstance();
    StageDirectorController stageDirectorController = new StageDirectorController(stageDirectorRepository);

    public StageDirectorControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        stageDirectorController.addStageDirector(new StageDirector("1", "First1", "Last1", new Vector<>(), new Vector<>()));
        stageDirectorController.addStageDirector(new StageDirector("2", "First1", "Last1", new Vector<>(), new Vector<>()));
        stageDirectorController.addStageDirector(new StageDirector("3", "First1", "Last1", new Vector<>(), new Vector<>()));
    }

    @Test
    public void expectCorrectFindingOfTheDirectorById() {
        StageDirector stageDirector = stageDirectorController.findStageDirectorById("1");
        assertEquals(stageDirector.getId(), "1");
    }

    @Test
    public void expectIncorrectFindingOfTheDirectorById() {
        StageDirector stageDirector = stageDirectorController.findStageDirectorById("1");
        assertNotEquals(stageDirector.getId(), "2");
    }

    @Test
    public void expectCorrectFindingOfTheMovieById() {
        stageDirectorRepository.addMovie(stageDirectorController.getAllStageDirectors().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = stageDirectorController.findMovieById(stageDirectorController.getAllStageDirectors().get(0), "111");
        assertEquals(movie.getId(), "111");
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() {
        stageDirectorController.addMovie(stageDirectorController.getAllStageDirectors().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        stageDirectorRepository.addMovie(stageDirectorController.getAllStageDirectors().get(0), new Movie("112", "Movie2", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = stageDirectorController.findMovieById(stageDirectorController.getAllStageDirectors().get(0), "112");
        assertNotEquals(movie.getId(), "111");
    }

    @Test
    public void expectIncorrectFindingOfTheAwardById() {
        stageDirectorRepository.addAward(stageDirectorController.getAllStageDirectors().get(0), new Award("111", "BestActor"));
        stageDirectorRepository.addAward(stageDirectorController.getAllStageDirectors().get(0), new Award("112", "BestActor"));
        Award award = stageDirectorController.findAwardById(stageDirectorController.getAllStageDirectors().get(0), "112");
        assertNotEquals(award.getId(), "111");
    }

    @Test
    public void expectCorrectFindingOfTheAwardById() {
        stageDirectorRepository.addAward(stageDirectorController.getAllStageDirectors().get(0), new Award("111", "BestActor"));
        stageDirectorRepository.addAward(stageDirectorController.getAllStageDirectors().get(0), new Award("112", "BestActor"));
        Award award = stageDirectorController.findAwardById(stageDirectorController.getAllStageDirectors().get(0), "112");
        assertEquals(award.getId(), "112");
    }
}

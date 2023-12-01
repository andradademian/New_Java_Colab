package map.project.demo.Controller;


import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.ActorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ActorControllerTest {
    ActorRepository actorRepository = ActorRepository.getInstance();
    ActorController actorController = new ActorController(actorRepository);

    public ActorControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() throws ParseException, SQLException {
        String startDate = "2000-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = dateFormat.parse(startDate);
        java.sql.Date date = new Date(utilDate.getTime());
        actorRepository.add(new Actor("1", "First1", "Last1", new Vector<String>(), date, new Vector<String>()));
        actorRepository.add(new Actor("2", "First2", "Last2", new Vector<String>(), date, new Vector<String>()));
        actorRepository.add(new Actor("3", "First3", "Last3", new Vector<String>(), date, new Vector<String>()));
    }

    @Test
    public void expectCorrectFindingOfTheActorById() {
        Actor actor = actorController.findActorById("1");
        assertEquals(actor.getId(), "1");
    }

    @Test
    public void expectIncorrectFindingOfTheActorById() {
        Actor actor = actorController.findActorById("1");
        assertNotEquals(actor.getId(), "2");
    }

    @Test
    public void expectCorrectFindingOfTheMovieById() {
        actorRepository.addMovie(actorController.getAllActors().get(0), "111");
        boolean movieExists = actorController.findMovieById(actorController.getAllActors().get(0), "111");
        assertTrue(movieExists);
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() {
        actorRepository.addMovie(actorController.getAllActors().get(0), "111");
        boolean movieExists = actorController.findMovieById(actorController.getAllActors().get(0), "112");
        assertFalse(movieExists);
    }
}

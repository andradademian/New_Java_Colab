package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.ActorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.testng.annotations.Ignore;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ActorRepoTest {
    ActorRepository actorRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        actorRepository = new ActorRepository();
    }

    @Test
    public void expectActorAddedSuccessfully() throws ParseException, SQLException {
        actorIsAddedToTheList();
        assertEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectActorNotAddedSuccessfully() throws ParseException, SQLException {
        actorIsAddedToTheList();
        assertNotEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectActorRemovedSuccessfully() throws ParseException, SQLException {
        actorIsAddedToTheList();
        Actor actor = actorRepository.getAll().get(0);
        actorRepository.delete(actor);
        assertEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectActorNotRemovedSuccessfully() throws ParseException, SQLException {
        actorIsAddedToTheList();
        Actor actor1 = actorRepository.getAll().get(0);
        actorRepository.delete(actor1);
        assertNotEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllActors() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.deleteAll();
        assertEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllActors() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.deleteAll();
        assertNotEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfFirstName() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.updateFirstName(actorRepository.getAll().get(0), "Nora");
        String expected = "Nora";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfFirstName() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.updateFirstName(actorRepository.getAll().get(0), "Nora");
        String expected = "Lora";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectUpdateOfLastName() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.updateLastName(actorRepository.getAll().get(0), "Johnson");
        String expected = "Johnson";
        String actual = actorRepository.getAll().get(0).getLastName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfLastName() throws ParseException, SQLException {
        actorIsAddedToTheList();
        actorRepository.updateLastName(actorRepository.getAll().get(0), "Johnson");
        String expected = "Diesel";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertNotSame(actual, expected);
    }

    @Test
    public void actorIsAddedCorrectlyToTheTable() throws SQLException, ParseException {
        actorIsAddedToTheTable();
        Actor actor = actorRepository.getActorWithIdFromTable("1K");
        assertEquals("1K", actor.getId());
        actorIsRemovedFromTheTable();
    }

    @Test
    public void actorIsNotAddedCorrectlyToTheTable() throws SQLException, ParseException {
        actorIsAddedToTheTable();
        Actor actor = actorRepository.getActorWithIdFromTable("1K");
        assertNotSame(actor.getId(), "1");
        actorIsRemovedFromTheTable();
    }

    @Test
    public void actorIsRemovedCorrectlyFromTheTable() throws SQLException, ParseException {
        int size = actorRepository.getActorsFromTable().size();
        actorIsAddedToTheTable();
        assertSame(actorRepository.getActorsFromTable().size(), size + 1);
        actorIsRemovedFromTheTable();
        assertSame(actorRepository.getActorsFromTable().size(), size);
    }

    @Test
    public void actorIsNotRemovedCorrectlyFromTheTable() throws SQLException, ParseException {
        int size = actorRepository.getActorsFromTable().size();
        actorIsAddedToTheTable();
        assertSame(actorRepository.getActorsFromTable().size(), size + 1);
        actorIsRemovedFromTheTable();
        assertNotSame(actorRepository.getActorsFromTable().size(), size + 1);
    }

    public void actorIsAddedToTheTable() throws ParseException, SQLException {
        String startDate = "2000-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(startDate);
        Date sqlDate = new Date(date.getTime());
        Actor actor = new Actor("1K", "Vin", "Diesel", new Vector<>(), sqlDate, new Vector<>());
        actorRepository.add(actor);
    }

    public void actorIsRemovedFromTheTable() throws SQLException {
        actorRepository.deleteActorWithIdFromTable("1K");
    }

    public void actorIsAddedToTheList() throws ParseException, SQLException {
        actorRepository.deleteAll();
        String startDate = "2000-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = dateFormat.parse(startDate);
        Date sqlDate = new Date(date.getTime());
        Actor actor = new Actor("1", "Vin", "Diesel", new Vector<>(), sqlDate, new Vector<>());
        actorRepository.add(actor);
    }

    public void movieIsAddedToTheActor() throws SQLException {
        Movie movie = new Movie("1", "Title", 120, new Vector<>(), new Vector<>(), new Vector<>());
        actorRepository.addMovie(String.valueOf(actorRepository.getAll().get(0)), movie.getId());
    }

    public void awardIsAddedToTheActor() throws SQLException {
        Award award = new Award("1", "Best actor");
        actorRepository.addAward(String.valueOf(actorRepository.getAll().get(0)), award.getId());
    }

}

package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Repository.ActorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

public class ActorRepoTest {
    ActorRepository actorRepository;

    @BeforeEach
    public void setUp() {
        actorRepository = ActorRepository.getInstance();
    }

    @Test
    public void expectActorAddedSuccessfully() throws ParseException {
        actorIsAddedToTheList();
        assertEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectActorNotAddedSuccessfully() throws ParseException {
        actorIsAddedToTheList();
        assertNotEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectActorRemovedSuccessfully() throws ParseException {
        actorIsAddedToTheList();
        Actor actor = actorRepository.getAll().get(0);
        actorRepository.delete(actor);
        assertEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectActorNotRemovedSuccessfully() throws ParseException {
        actorIsAddedToTheList();
        Actor actor1 = actorRepository.getAll().get(0);
        actorRepository.delete(actor1);
        assertNotEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllActors() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.deleteAll();
        assertEquals(actorRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllActors() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.deleteAll();
        assertNotEquals(actorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectUpdateOfFirstName() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.updateFirstName(actorRepository.getAll().get(0), "Nora");
        String expected = "Nora";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfFirstName() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.updateFirstName(actorRepository.getAll().get(0), "Nora");
        String expected = "Lora";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectUpdateOfLastName() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.updateLastName(actorRepository.getAll().get(0), "Johnson");
        String expected = "Johnson";
        String actual = actorRepository.getAll().get(0).getLastName();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfLastName() throws ParseException {
        actorIsAddedToTheList();
        actorRepository.updateLastName(actorRepository.getAll().get(0), "Johnson");
        String expected = "Diesel";
        String actual = actorRepository.getAll().get(0).getFirstName();
        assertNotSame(actual, expected);
    }

    @Test
    public void expectCorrectAddingOfTheMovie() throws ParseException {
        actorIsAddedToTheList();
        movieIsAddedToTheActor();
        assertEquals(actorRepository.getAll().get(0).getListOfMovies().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheMovie() throws ParseException {
        actorIsAddedToTheList();
        movieIsAddedToTheActor();
        assertNotEquals(actorRepository.getAll().get(0).getListOfMovies().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheMovie() throws ParseException {
        actorIsAddedToTheList();
        movieIsAddedToTheActor();
        actorRepository.deleteMovie(actorRepository.getAll().get(0), actorRepository.getAll().get(0).getListOfMovies().get(0));
        assertEquals(actorRepository.getAll().get(0).getListOfMovies().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheMovie() throws ParseException {
        actorIsAddedToTheList();
        movieIsAddedToTheActor();
        actorRepository.deleteMovie(actorRepository.getAll().get(0), actorRepository.getAll().get(0).getListOfMovies().get(0));
        assertNotEquals(actorRepository.getAll().get(0).getListOfMovies().size(), 1);
    }

    @Test
    public void expectCorrectAddingOfTheAward() throws ParseException {
        actorIsAddedToTheList();
        awardIsAddedToTheActor();
        assertEquals(actorRepository.getAll().get(0).getListOfAwards().size(), 1);
    }

    @Test
    public void expectIncorrectAddingOfTheAward() throws ParseException {
        actorIsAddedToTheList();
        awardIsAddedToTheActor();
        assertNotEquals(actorRepository.getAll().get(0).getListOfAwards().size(), 0);
    }

    @Test
    public void expectCorrectDeletingOfTheAward() throws ParseException {
        actorIsAddedToTheList();
        awardIsAddedToTheActor();
        actorRepository.deleteAward(actorRepository.getAll().get(0), actorRepository.getAll().get(0).getListOfAwards().get(0));
        assertEquals(actorRepository.getAll().get(0).getListOfAwards().size(), 0);
    }

    @Test
    public void expectIncorrectDeletingOfTheAward() throws ParseException {
        actorIsAddedToTheList();
        awardIsAddedToTheActor();
        actorRepository.deleteAward(actorRepository.getAll().get(0), actorRepository.getAll().get(0).getListOfAwards().get(0));
        assertNotEquals(actorRepository.getAll().get(0).getListOfAwards().size(), 1);
    }


    public void actorIsAddedToTheList() throws ParseException {
        actorRepository.deleteAll();
        String startDate = "2000-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(startDate);
        Actor actor = new Actor("1", "Vin", "Diesel", new Vector<>(), date, new Vector<>());
        actorRepository.add(actor);
    }

    public void movieIsAddedToTheActor() {
        Movie movie = new Movie("1", "Title", 120, new Vector<>(), new Vector<>(), new Vector<>());
        actorRepository.addMovie(actorRepository.getAll().get(0), movie);
    }

    public void awardIsAddedToTheActor() {
        Award award = new Award("1", "Best actor");
        actorRepository.addAward(actorRepository.getAll().get(0), award);
    }

}

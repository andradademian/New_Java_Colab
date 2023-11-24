package map.project.demo.Controller;

import map.project.demo.Controller.ActorController;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ActorControllerTest {
    ActorRepository actorRepository = ActorRepository.getInstance();
    ActorController actorController = new ActorController(actorRepository);

    @BeforeEach
    public void setUp() throws ParseException {
        String startDate = "2000-01-01";
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(startDate);
        actorRepository.add(new Actor("1", "First1", "Last1", new Vector<Movie>(), date, new Vector<Award>()));
        actorRepository.add(new Actor("2", "First2", "Last2", new Vector<Movie>(), date, new Vector<Award>()));
        actorRepository.add(new Actor("3", "First3", "Last3", new Vector<Movie>(), date, new Vector<Award>()));
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
        actorRepository.addMovie(actorController.getAllActors().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = actorController.findMovieById(actorController.getAllActors().get(0), "111");
        assertEquals(movie.getId(), "111");
    }

    @Test
    public void expectIncorrectFindingOfTheMovieById() {
        actorRepository.addMovie(actorController.getAllActors().get(0), new Movie("111", "Movie", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        actorRepository.addMovie(actorController.getAllActors().get(0), new Movie("112", "Movie2", 120, new Vector<>(), new Vector<>(), new Vector<>()));
        Movie movie = actorController.findMovieById(actorController.getAllActors().get(0), "112");
        assertNotEquals(movie.getId(), "111");
    }

    @Test
    public void expectIncorrectFindingOfTheAwardById() {
        actorRepository.addAward(actorController.getAllActors().get(0), new Award("111", "BestActor"));
        actorRepository.addAward(actorController.getAllActors().get(0), new Award("112", "BestActor"));
        Award award = actorController.findAwardById(actorController.getAllActors().get(0), "112");
        assertNotEquals(award.getId(), "111");
    }

    @Test
    public void expectCorrectFindingOfTheAwardById() {
        actorRepository.addAward(actorController.getAllActors().get(0), new Award("111", "BestActor"));
        actorRepository.addAward(actorController.getAllActors().get(0), new Award("112", "BestActor"));
        Award award = actorController.findAwardById(actorController.getAllActors().get(0), "112");
        assertEquals(award.getId(), "112");
    }


}

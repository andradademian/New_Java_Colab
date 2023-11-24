package map.project.demo.Singleton;

import map.project.demo.AwardFactory.AwardFactory;

import static org.junit.jupiter.api.Assertions.*;

import map.project.demo.Repository.*;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    public void testSingletonActor() {
        ActorRepository instance1 = ActorRepository.getInstance();
        ActorRepository instance2 = ActorRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonAwardFactory() {
        AwardFactory instance1 = AwardFactory.getInstance();
        AwardFactory instance2 = AwardFactory.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonAward() {
        AwardRepository instance1 = AwardRepository.getInstance();
        AwardRepository instance2 = AwardRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonCinema() {
        CinemaRepository instance1 = CinemaRepository.getInstance();
        CinemaRepository instance2 = CinemaRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonGenre() {
        GenreRepository instance1 = GenreRepository.getInstance();
        GenreRepository instance2 = GenreRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonMovie() {
        MovieRepository instance1 = MovieRepository.getInstance();
        MovieRepository instance2 = MovieRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonRoom() {
        RoomRepository instance1 = RoomRepository.getInstance();
        RoomRepository instance2 = RoomRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonScreening() {
        ScreeningRepository instance1 = ScreeningRepository.getInstance();
        ScreeningRepository instance2 = ScreeningRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonSpectator() {
        SpectatorRepository instance1 = SpectatorRepository.getInstance();
        SpectatorRepository instance2 = SpectatorRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonStageDirector() {
        StageDirectorRepository instance1 = StageDirectorRepository.getInstance();
        StageDirectorRepository instance2 = StageDirectorRepository.getInstance();

        assertSame(instance1, instance2);
    }
    @Test
    public void testSingletonTicket() {
        TicketRepository instance1 = TicketRepository.getInstance();
        TicketRepository instance2 = TicketRepository.getInstance();

        assertSame(instance1, instance2);
    }

}

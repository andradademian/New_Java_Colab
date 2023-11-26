package map.project.demo.Singleton;

import map.project.demo.AwardFactory.AwardFactory;

import static org.junit.jupiter.api.Assertions.*;

import map.project.demo.Repository.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class SingletonTest {

    @Test
    public void testSingletonActor() throws SQLException {
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
    public void testSingletonAward() throws SQLException {
        AwardRepository instance1 = AwardRepository.getInstance();
        AwardRepository instance2 = AwardRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonCinema() throws SQLException {
        CinemaRepository instance1 = CinemaRepository.getInstance();
        CinemaRepository instance2 = CinemaRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonGenre() throws SQLException {
        GenreRepository instance1 = GenreRepository.getInstance();
        GenreRepository instance2 = GenreRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonMovie() throws SQLException {
        MovieRepository instance1 = MovieRepository.getInstance();
        MovieRepository instance2 = MovieRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonRoom() throws SQLException {
        RoomRepository instance1 = RoomRepository.getInstance();
        RoomRepository instance2 = RoomRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonScreening() throws SQLException {
        ScreeningRepository instance1 = ScreeningRepository.getInstance();
        ScreeningRepository instance2 = ScreeningRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonSpectator() throws SQLException {
        SpectatorRepository instance1 = SpectatorRepository.getInstance();
        SpectatorRepository instance2 = SpectatorRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonStageDirector() throws SQLException {
        StageDirectorRepository instance1 = StageDirectorRepository.getInstance();
        StageDirectorRepository instance2 = StageDirectorRepository.getInstance();

        assertSame(instance1, instance2);
    }

    @Test
    public void testSingletonTicket() throws SQLException {
        TicketRepository instance1 = TicketRepository.getInstance();
        TicketRepository instance2 = TicketRepository.getInstance();

        assertSame(instance1, instance2);
    }

}

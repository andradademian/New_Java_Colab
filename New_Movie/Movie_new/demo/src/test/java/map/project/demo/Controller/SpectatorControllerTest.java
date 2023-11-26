package map.project.demo.Controller;

import map.project.demo.Controller.SpectatorController;
import map.project.demo.Domain.Spectator;
import map.project.demo.Repository.SpectatorRepository;
import map.project.demo.Strategy.Screening;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpectatorControllerTest {
    SpectatorRepository spectatorRepository = SpectatorRepository.getInstance();
    SpectatorController spectatorController = new SpectatorController(spectatorRepository);

    public SpectatorControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() throws SQLException {
        Spectator spectator1 = new Spectator("1", "First1", "Last1");
        Spectator spectator2 = new Spectator("2", "First2", "Last3");
        Spectator spectator3 = new Spectator("3", "First3", "Last3");
        spectatorController.addSpectator(spectator1);
        spectatorController.addSpectator(spectator2);
        spectatorController.addSpectator(spectator3);
    }

    @Test
    public void expectCorrectAddingOfTheSpectator() {
        assertEquals(spectatorController.getAllSpectators().size(), 3);
        spectatorController.deleteAllSpectators();
    }

    @Test
    public void expectIncorrectAddingOfTheScreenings() {
        assertNotEquals(spectatorController.getAllSpectators().size(), 0);
        spectatorController.deleteAllSpectators();
    }

    @Test
    public void expectCorrectFindingOfTheSpectatorById() {
        Spectator spectator = spectatorController.findSpectatorById("2");
        assertEquals(spectator.getId(), "2");
        spectatorController.deleteAllSpectators();
    }

    @Test
    public void expectIncorrectFindingOfTheSpectatorById() {
        Spectator spectator = spectatorController.findSpectatorById("1");
        assertNotEquals(spectator.getId(), "2");
        spectatorController.deleteAllSpectators();
    }

    @Test
    public void expectCorrectDeletingOfTheSpectatorById() {
        spectatorController.deleteSpectator("1");
        Spectator spectator = spectatorController.findSpectatorById("1");
        assertNull(spectator);
        spectatorController.deleteAllSpectators();
    }

    @Test
    public void expectIncorrectDeletingOfTheSpectatorById() {
        spectatorController.deleteSpectator("2");
        Spectator spectator = spectatorController.findSpectatorById("3");
        assertNotNull(spectator);
        spectatorController.deleteAllSpectators();
    }
}

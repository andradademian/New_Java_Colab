package map.project.demo.Repository;

import map.project.demo.Domain.*;
import map.project.demo.Repository.SpectatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SpectatorRepoTest {
    SpectatorRepository spectatorRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        spectatorRepository = SpectatorRepository.getInstance();
    }

    @Test
    public void expectSpectatorAddedSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        assertEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectSpectatorNotAddedSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        assertNotEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectSpectatorRemovedSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        spectatorRepository.delete(spectator);
        assertEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectSpectatorNotRemovedSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        spectatorRepository.delete(spectator);
        assertNotEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllSpectators() throws SQLException {
        spectatorIsAddedToTheList();
        spectatorRepository.deleteAll();
        assertEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllSpectators() throws SQLException {
        spectatorIsAddedToTheList();
        spectatorRepository.deleteAll();
        assertNotEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdateFirstNameSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        String newFirstName = "NewFirstName";
        spectatorRepository.updateFirstName(spectator, newFirstName);
        assertEquals(spectator.getFirstName(), newFirstName);
    }

    @Test
    public void expectUpdateLastNameSuccessfully() throws SQLException {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        String newLastName = "NewLastName";
        spectatorRepository.updateLastName(spectator, newLastName);
        assertEquals(spectator.getLastName(), newLastName);
    }

    public void spectatorIsAddedToTheList() throws SQLException {
        spectatorRepository.deleteAll();
        Spectator spectator = new Spectator("1", "FirstName", "LastName");
        spectatorRepository.add(spectator);
    }
}

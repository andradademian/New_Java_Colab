package map.project.demo.Repository;

import map.project.demo.Domain.*;
import map.project.demo.Repository.SpectatorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SpectatorRepoTest {
    SpectatorRepository spectatorRepository;

    @BeforeEach
    public void setUp() {
        spectatorRepository = SpectatorRepository.getInstance();
    }

    @Test
    public void expectSpectatorAddedSuccessfully() {
        spectatorIsAddedToTheList();
        assertEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectSpectatorNotAddedSuccessfully() {
        spectatorIsAddedToTheList();
        assertNotEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectSpectatorRemovedSuccessfully() {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        spectatorRepository.delete(spectator);
        assertEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectSpectatorNotRemovedSuccessfully() {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        spectatorRepository.delete(spectator);
        assertNotEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllSpectators() {
        spectatorIsAddedToTheList();
        spectatorRepository.deleteAll();
        assertEquals(spectatorRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllSpectators() {
        spectatorIsAddedToTheList();
        spectatorRepository.deleteAll();
        assertNotEquals(spectatorRepository.getAll().size(), 1);
    }

    @Test
    public void expectUpdateFirstNameSuccessfully() {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        String newFirstName = "NewFirstName";
        spectatorRepository.updateFirstName(spectator, newFirstName);
        assertEquals(spectator.getFirstName(), newFirstName);
    }

    @Test
    public void expectUpdateLastNameSuccessfully() {
        spectatorIsAddedToTheList();
        Spectator spectator = spectatorRepository.getAll().get(0);
        String newLastName = "NewLastName";
        spectatorRepository.updateLastName(spectator, newLastName);
        assertEquals(spectator.getLastName(), newLastName);
    }

    public void spectatorIsAddedToTheList() {
        spectatorRepository.deleteAll();
        Spectator spectator = new Spectator("1", "FirstName", "LastName");
        spectatorRepository.add(spectator);
    }
}

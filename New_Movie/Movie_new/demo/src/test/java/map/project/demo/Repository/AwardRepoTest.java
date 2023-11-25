package map.project.demo.Repository;

import map.project.demo.Domain.Award;

import map.project.demo.Repository.AwardRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AwardRepoTest {
    AwardRepository awardRepository;

    @BeforeEach
    public void setUp() throws SQLException {
        awardRepository = AwardRepository.getInstance();
    }

    @Test
    public void expectAwardAddedSuccessfully() {
        awardIsAddedToTheList();
        assertEquals(awardRepository.getAll().size(), 1);
    }

    @Test
    public void expectAwardNotAddedSuccessfully() {
        awardIsAddedToTheList();
        assertNotEquals(awardRepository.getAll().size(), 0);
    }

    @Test
    public void expectAwardRemovedSuccessfully() {
        awardIsAddedToTheList();
        Award award = awardRepository.getAll().get(0);
        awardRepository.delete(award);
        assertEquals(awardRepository.getAll().size(), 0);
    }

    @Test
    public void expectAwardNotRemovedSuccessfully() {
        awardIsAddedToTheList();
        Award award = awardRepository.getAll().get(0);
        awardRepository.delete(award);
        assertNotEquals(awardRepository.getAll().size(), 1);
    }

    @Test
    public void expectCorrectRemovalOfAllAwards() {
        awardIsAddedToTheList();
        awardRepository.deleteAll();
        assertEquals(awardRepository.getAll().size(), 0);
    }

    @Test
    public void expectIncorrectRemovalOfAllActors() {
        awardIsAddedToTheList();
        awardRepository.deleteAll();
        assertNotEquals(awardRepository.getAll().size(), 1);
    }


    @Test
    public void expectCorrectUpdateOfCategory() {
        awardIsAddedToTheList();
        awardRepository.updateCategory(awardRepository.getAll().get(0), "Best");
        String expected = "Best";
        String actual = awardRepository.getAll().get(0).getCategory();
        assertSame(actual, expected);
    }

    @Test
    public void expectIncorrectUpdateOfCategory() {
        awardIsAddedToTheList();
        awardRepository.updateCategory(awardRepository.getAll().get(0), "Best");
        String expected = "Best sth";
        String actual = awardRepository.getAll().get(0).getCategory();
        assertNotSame(actual, expected);
    }

    public void awardIsAddedToTheList() {
        awardRepository.deleteAll();
        Award award = new Award("1", "Best actor");
        awardRepository.add(award);
    }
}

package map.project.demo.Controller;

import map.project.demo.Controller.AwardController;
import map.project.demo.Domain.Award;
import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Repository.AwardRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Vector;

public class AwardControllerTest {
    AwardRepository awardRepository = new AwardRepository();
    AwardFactory awardFactory = AwardFactory.getInstance();
    AwardController awardController = new AwardController();

    public AwardControllerTest() throws SQLException {
    }

    @BeforeEach
    public void setUp() {
        awardRepository.add(awardFactory.buildAward("Oscar", "1", "BestActor"));
        awardRepository.add(awardFactory.buildAward("GoldenGlobe", "2", "BestDirector"));
        awardRepository.add(awardFactory.buildAward("PalmeDor", "3", "BestPicture"));
    }


    @Test
    public void shouldFindAwardById() throws SQLException {
        String awardId = "2";

        Award foundAward = awardController.findAwardById(awardId);

        assertNotNull(foundAward);
        assertEquals(awardId, foundAward.getId());
    }

    @Test
    public void shouldNotFindInvalidAwardById() throws SQLException {
        String invalidAwardId = "InvalidId";

        Award foundAward = awardController.findAwardById(invalidAwardId);

        assertNull(foundAward);
    }


}

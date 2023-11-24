package map.project.demo.Controller;

import map.project.demo.Controller.AwardController;
import map.project.demo.Domain.Award;
import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Repository.AwardRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

public class AwardControllerTest {
    AwardRepository awardRepository = AwardRepository.getInstance();
    AwardFactory awardFactory = AwardFactory.getInstance();
    AwardController awardController = new AwardController(awardRepository, awardFactory);

    @BeforeEach
    public void setUp() {
        awardRepository.add(awardFactory.buildAward("Oscar", "1", "BestActor"));
        awardRepository.add(awardFactory.buildAward("GoldenGlobe", "2", "BestDirector"));
        awardRepository.add(awardFactory.buildAward("PalmeDor", "3", "BestPicture"));
    }


    @Test
    public void shouldFindAwardById() {
        String awardId = "2";

        Award foundAward = awardController.findAwardById(awardId);

        assertNotNull(foundAward);
        assertEquals(awardId, foundAward.getId());
    }

    @Test
    public void shouldNotFindInvalidAwardById() {
        String invalidAwardId = "InvalidId";

        Award foundAward = awardController.findAwardById(invalidAwardId);

        assertNull(foundAward);
    }


}

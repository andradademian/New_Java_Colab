package map.project.demo.Factory;

import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Domain.Award;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FactoryTest {
    private AwardFactory awardFactory;

    @BeforeEach
    public void setUp() {
        awardFactory = AwardFactory.getInstance();
    }

    @Test
    public void buildAwardWithValidTypeAndCategory() {
        Award award = awardFactory.buildAward("Oscar", "1", "BestPicture");
        assertNotNull(award);
        assertEquals("Oscar", award.getType());
        assertEquals("1", award.getId());
        assertEquals("BestPicture", award.getCategory());
    }

    @Test
    public void buildAwardWithInvalidType() {
        Award award = awardFactory.buildAward("InvalidType", "2", "BestActor");
        assertNull(award);
    }

    @Test
    public void buildAwardWithInvalidCategory() {
        Award award = awardFactory.buildAward("GoldenGlobe", "3", "InvalidCategory");
        assertNull(award);
    }

    @Test
    public void getAwardCategories() {
        Vector<String> categories = AwardFactory.getAwardCategories();
        assertNotNull(categories);
        assertEquals(4, categories.size());
        assertTrue(categories.contains("BestPicture"));
        assertTrue(categories.contains("BestActor"));
        assertTrue(categories.contains("BestDirector"));
        assertTrue(categories.contains("BestScreenplay"));
    }
}

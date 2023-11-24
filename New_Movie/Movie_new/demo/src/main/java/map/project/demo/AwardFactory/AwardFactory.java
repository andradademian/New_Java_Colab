package map.project.demo.AwardFactory;

import map.project.demo.Domain.Award;

import java.util.Vector;

public class AwardFactory {
    private static AwardFactory instance;
    public static AwardFactory getInstance() {
        if (instance == null) {
            instance = new AwardFactory();
        }
        return instance;
    }
    public Award buildAward(String type, String id, String category) {
        Award award;

        Vector<String> awardCategories = getAwardCategories();

        if (awardCategories.contains(category)) {
            if ("Oscar".equals(type)) {
                award = new Oscar(id, category);
            } else if ("GoldenGlobe".equals(type)) {
                award = new GoldenGlobe(id, category);
            } else if ("PalmeDor".equals(type)){
                award = new PalmeDor(id, category);
            }
            else {
                System.out.println("Invalid award type: " + type);
                return null;
            }
        } else {
            System.out.println("Invalid award category: " + category);
            return null;
        }

        return award;
    }

    public static Vector<String> getAwardCategories() {
        Vector<String> categories = new Vector<>();
        categories.add("BestPicture");
        categories.add("BestActor");
        categories.add("BestDirector");
        categories.add("BestScreenplay");
        return categories;
    }
}

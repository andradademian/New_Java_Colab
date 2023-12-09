package map.project.demo.Domain;

import map.project.demo.AwardFactory.GoldenGlobe;
import map.project.demo.AwardFactory.Oscar;
import map.project.demo.AwardFactory.PalmeDor;

import java.util.Vector;

public class Award {
    private String id;
    private String name;
    private String category;

    public Award() {

    }

    public Award(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Award{" +
                "id='" + id + '\'' +
                ", type='" + getType() + '\'' +
                ", category='" + category + '\'' +
                '}';
    }

    public String getType() {
        return switch (this) {
            case Oscar oscar -> "Oscar";
            case GoldenGlobe goldenGlobe -> "GoldenGlobe";
            case PalmeDor palmeDor -> "PalmeDor";
            default -> "Unknown";
        };
    }

}

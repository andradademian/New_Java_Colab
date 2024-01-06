package map.project.demo.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import map.project.demo.AwardFactory.GoldenGlobe;
import map.project.demo.AwardFactory.Oscar;
import map.project.demo.AwardFactory.PalmeDor;

import java.util.Vector;

@Getter
@Entity
@Table(name = "Award")
public class Award {

    @Id
    private String id;

    @Column(name = "type")
    private String type;

    @Column(name = "category")
    private String category;

    public void setType(String type) {
        this.type = type;
    }

    public Award() {

    }

    public Award(String id, String category) {
        this.id = id;
        this.category = category;
    }

    public void setId(String id) {
        this.id = id;
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

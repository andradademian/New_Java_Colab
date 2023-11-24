package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;

import java.util.Vector;

public class AwardRepository {
    private static AwardRepository instance;
    private final Vector<Award> awards;

    public AwardRepository() {
        awards = new Vector<>();
    }

    public static AwardRepository getInstance() {
        if (instance == null) {
            instance = new AwardRepository();
        }
        return instance;
    }
    public void add(Award award) {
        awards.add(award);
    }

    public void delete(Award award) {
        awards.remove(award);
    }

    public void deleteAll() {
        awards.clear();
    }

    public void printAll() {
        System.out.println(awards);
    }
    public void updateCategory(Award award, String category) {
        awards.get(getAll().indexOf(award)).setCategory(category);
    }

    public Vector<Award> getAll() {
        return this.awards;
    }
}

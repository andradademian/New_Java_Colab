package map.project.demo.Controller;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;

import map.project.demo.Domain.Movie;
import map.project.demo.Repository.AwardRepository;
import map.project.demo.AwardFactory.AwardFactory;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class AwardController {
    private AwardRepository awardRepo = new AwardRepository();
    private final Vector<Award> listOfAwards = new Vector<>();
    private final AwardFactory awardFactory;

    public AwardController(AwardRepository awardRepo, AwardFactory awardFactory) throws SQLException {
        this.awardRepo = awardRepo;
        this.awardFactory = awardFactory;
    }

    public void addAward(String type, String id, String category) {
        Award newAward = awardFactory.buildAward(type, id, category);

        if (newAward != null) {
            awardRepo.add(newAward);
            System.out.println("Award added successfully!");
        } else {
            System.out.println("Failed to add award. Please check the input.");
        }
    }
    public void deleteAward(Award award) {
        awardRepo.delete(award);
    }

    public void deleteAllAwards() {
        awardRepo.deleteAll();
    }

    public Award findAwardById(String awardId) {
        for (Award award : awardRepo.getAll()) {
            if (award.getId().equals(awardId)) {
                return award;
            }
        }
        System.out.println("No award with that ID");
        return null;
    }

    public void deleteAward(String awardId) {
        Award awardToDelete = findAwardById(awardId);
        if (awardToDelete != null) {
            awardRepo.delete(awardToDelete);
        }
    }

    public void updateAwardCategory(Award award, String category) {
        awardRepo.updateCategory(award, category);
    }

    public Vector<Award> getAllAwards() {
        return awardRepo.getAll();
    }

    public void printAllAwards() {
        awardRepo.printAll();
    }
}
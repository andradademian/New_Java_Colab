package map.project.demo.Controller;

import map.project.demo.Domain.Spectator;
import map.project.demo.Repository.SpectatorRepository;

import java.util.Scanner;
import java.util.Vector;

public class SpectatorController {
    private final SpectatorRepository spectatorRepo;

    public SpectatorController(SpectatorRepository spectatorRepo) {
        this.spectatorRepo = spectatorRepo;
    }

    public void addSpectator(Spectator spectator) {
        spectatorRepo.add(spectator);
    }

    public Spectator findSpectatorById(String spectatorId) {
        for (Spectator spectator : spectatorRepo.getAll()) {
            if (spectator.getId().equals(spectatorId)) {
                return spectator;
            }
        }
        System.out.println("No spectator with that ID");
        return null;
    }

    public void deleteSpectator(String spectatorId) {
        Spectator spectatorToDelete = findSpectatorById(spectatorId);
        if (spectatorToDelete != null) {
            spectatorRepo.delete(spectatorToDelete);
        }
    }

    public void deleteAllSpectators() {
        spectatorRepo.deleteAll();
    }


    public void updateSpectatorFirstName(Spectator spectator, String firstName) {
        spectatorRepo.updateFirstName(spectator, firstName);
    }

    public void updateSpectatorLastName(Spectator spectator, String lastName) {
        spectatorRepo.updateLastName(spectator, lastName);
    }

    public Vector<Spectator> getAllSpectators() {
        return spectatorRepo.getAll();
    }

    public void printAllSpectators() {
        spectatorRepo.printAll();
    }
}

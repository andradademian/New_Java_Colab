package map.project.demo.UI;

import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Controller.AwardController;
import map.project.demo.Controller.MovieController;
import map.project.demo.Controller.StageDirectorController;
import map.project.demo.Domain.*;

import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class StageDirectorUI {
    private final StageDirectorController stageDirectorController;
    private final MovieController movieController;
    private final AwardController awardController;

    public StageDirectorUI(StageDirectorController stageDirectorController, MovieController movieController, AwardController awardController) {
        this.stageDirectorController = stageDirectorController;
        this.movieController = movieController;
        this.awardController = awardController;
    }

    public void mainStageDirectorUI() {
        int choice;
        do {
            this.stageDirectorController.printAllStageDirectors();
            stageDirectorMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addStageDirector();
            }
            if (choice == 2) {
                deleteStageDirector();
            }
            if (choice == 3) {
                stageDirectorController.deleteAllStageDirectors();
            }
            if (choice == 4) {
                updateStageDirector();
            }
        } while (choice > 0 && choice < 5);
    }

    public static void stageDirectorMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add a stage director");
        System.out.println("2. Delete a stage director");
        System.out.println("3. Delete all stage directors");
        System.out.println("4. Update a stage director");
        System.out.println("5. Exit");
    }

    public void addStageDirector() {
        this.stageDirectorController.printAllStageDirectors();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter ID:");
        String id = keyboard.next();

        System.out.println("Enter first name:");
        String firstName = keyboard.next();

        System.out.println("Enter last name:");
        String lastName = keyboard.next();

        this.stageDirectorController.addStageDirector(new StageDirector(id, firstName, lastName, new Vector<Movie>(), new Vector<Award>()));
    }

    public void updateStageDirector(String stageDirectorId) {
        StageDirector stageDirectorToUpdate = findStageDirectorById(stageDirectorId);
        if (stageDirectorToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update first name");
            System.out.println("2. Update last name");
            System.out.println("3. Add a movie");
            System.out.println("4. Delete a movie");
            System.out.println("5. Add an award");
            System.out.println("6. Delete an award");
            System.out.println("7. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new first name:");
                String firstName = keyboard.next();
                stageDirectorController.updateStageDirectorFirstName(stageDirectorToUpdate, firstName);
            } else if (choice == 2) {
                System.out.println("Enter new last name:");
                String lastName = keyboard.next();
                stageDirectorController.updateStageDirectorLastName(stageDirectorToUpdate, lastName);
            } else if (choice == 4) {
                if (!stageDirectorToUpdate.getListOfMovies().isEmpty()) {
                    System.out.println(stageDirectorToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id of the movie you want to delete:");
                    String id = keyboard.next();
                    Movie movieToDelete = stageDirectorController.findMovieById(stageDirectorToUpdate, id);
                    if (movieToDelete != null) {
                        stageDirectorController.deleteMovie(stageDirectorToUpdate, movieToDelete);
                    }
                }
            } else if (choice == 3) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new movie");
                System.out.println("2. Add an existing movie");
                int movieChoice = keyboard.nextInt();
                if (movieChoice == 1) {
                    System.out.println(stageDirectorToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id for the movie:");
                    String id = keyboard.next();
                    System.out.println("Enter title of the movie");
                    String title = keyboard.next();
                    System.out.println("Enter duration of the movie");
                    int duration = keyboard.nextInt();
                    Movie movie = new Movie(id, title, duration, new Vector<StageDirector>(), new Vector<Actor>(), new Vector<Genre>());
                    stageDirectorController.addMovie(stageDirectorToUpdate, movie);
                    movieController.addMovie(movie);
                } else if (movieChoice == 2) {
                    movieController.printAllMovies();
                    System.out.println("Enter id of the movie you want to add");
                    String id = keyboard.next();
                    Movie movie = movieController.findMovieById(id);
                    stageDirectorController.addMovie(stageDirectorToUpdate, movie);
                }
            } else if (choice == 5) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new award");
                System.out.println("2. Add an existing award");
                int awardChoice = keyboard.nextInt();
                if (awardChoice == 1) {
                    System.out.println(stageDirectorToUpdate.getAwards().toString());
                    System.out.println("Enter type of award (Oscar, GoldenGlobe, PalmeDor):");
                    String title = keyboard.next();

                    System.out.println("Enter ID for award:");
                    String id = keyboard.next();

                    System.out.print("Enter category (");
                    for (String category : AwardFactory.getAwardCategories()) {
                        System.out.print(category + " ");
                    }
                    System.out.print("):");
                    System.out.println();
                    String category = keyboard.next();
                    Award award = new Award(id, category);
                    stageDirectorController.addAward(stageDirectorToUpdate, award);
                    awardController.addAward(title, id, category);
                } else if (awardChoice == 2) {
                    awardController.printAllAwards();
                    System.out.println("Enter id of the award you want to add");
                    String id = keyboard.next();
                    Award award = awardController.findAwardById(id);
                    stageDirectorController.addAward(stageDirectorToUpdate, award);
                }
            } else if (choice == 6) {
                if (!stageDirectorToUpdate.getAwards().isEmpty()) {
                    System.out.println(stageDirectorToUpdate.getAwards().toString());
                    System.out.println("Enter Id of the award you want to delete:");
                    String id = keyboard.next();
                    Award awardToDelete = stageDirectorController.findAwardById(stageDirectorToUpdate, id);
                    if (awardToDelete != null) {
                        stageDirectorController.deleteAward(stageDirectorToUpdate, awardToDelete);
                    }
                }
            }
        }
    }

    public void updateStageDirector() {
        this.stageDirectorController.printAllStageDirectors();
        System.out.println("Enter ID of the stage director you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();

        StageDirector stageDirectorToDelete = findStageDirectorById(id);
        if (stageDirectorToDelete != null) {

            this.updateStageDirector(id);
        } else {
            System.out.println("No stage director with that id");
        }
    }

    public void deleteStageDirector() {
        this.stageDirectorController.printAllStageDirectors();
        System.out.println("Enter ID of the stage director you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();

        StageDirector stageDirectorToDelete = findStageDirectorById(id);

        if (stageDirectorToDelete != null) {
            this.stageDirectorController.deleteStageDirector(String.valueOf(stageDirectorToDelete));
            System.out.println("StageDirector with ID " + id + " has been deleted.");
        } else {
            System.out.println("No stage director with ID " + id + " found.");
        }
    }

    private StageDirector findStageDirectorById(String id) {
        for (StageDirector stageDirector : this.stageDirectorController.getAllStageDirectors()) {
            if (stageDirector.getId().equals(id)) {
                return stageDirector;
            }
        }
        return null;
    }

}

package map.project.demo.UI;

import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Controller.ActorController;
import map.project.demo.Controller.AwardController;
import map.project.demo.Controller.MovieController;
import map.project.demo.Domain.*;

import java.awt.*;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Date;
import java.util.Scanner;
import java.util.Vector;

public class ActorUI {
    private final ActorController actorController;
    private final AwardController awardController;
    private final MovieController movieController;

    public ActorUI(ActorController actorController, AwardController awardController, MovieController movieController) {
        this.actorController = actorController;
        this.awardController = awardController;
        this.movieController = movieController;
    }

    public void mainActorUI() throws SQLException {
        int choice;
        do {
            this.actorController.showAllActors();
            actorMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addAnActor();
            }
            if (choice == 2) {
                deleteAnActor();
            }
            if (choice == 3) {
                actorController.deleteAllActors();
            }
            if (choice == 4) {
                updateAnActor();
            }
        } while (choice > 0 && choice < 5);
    }

    public static void actorMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add an actor");
        System.out.println("2. Delete an actor");
        System.out.println("3. Delete all actors");
        System.out.println("4. Update an actor");
        System.out.println("5. Exit");
    }

    public void addAnActor() {
        this.actorController.showAllActors();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter ID for actor:");
        String id = keyboard.next();

        System.out.println("Enter first name for actor:");
        String firstName = keyboard.next();

        System.out.println("Enter last name for actor:");
        String lastName = keyboard.next();

        System.out.println("Enter start of career (YYYY-MM-DD):");
        Date startCareer = java.sql.Date.valueOf(keyboard.next());

        this.actorController.addActor(new Actor(id, firstName, lastName, new Vector<Movie>(), startCareer, new Vector<Award>()));
    }

    public void deleteAnActor() {
        this.actorController.showAllActors();
        System.out.println("Enter ID of the actor you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        for (Movie movie : movieController.getAllMovies()) {
            movieController.deleteActorFromMovie(movie, actorController.findActorById(id));
        }
        this.actorController.deleteActor(id);
    }

    public void updateAnActor() throws SQLException {
        this.actorController.showAllActors();
        System.out.println("Enter ID of the actor you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        updateActor(id);
    }

    public void updateActor(String actorId) throws SQLException {
        Actor actorToUpdate = actorController.findActorById(actorId);
        if (actorToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update first name");
            System.out.println("2. Update last name");
            System.out.println("3. Delete a movie");
            System.out.println("4. Add a movie");
            System.out.println("5. Delete an award");
            System.out.println("6. Add an award");
            System.out.println("7. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new first name:");
                String name = keyboard.next();
                actorController.updateFirstName(actorToUpdate, name);
            } else if (choice == 2) {
                System.out.println("Enter new last name:");
                String name = keyboard.next();
                actorController.updateLastName(actorToUpdate, name);
            } else if (choice == 3) {
                if (!actorToUpdate.getListOfMovies().isEmpty()) {
                    System.out.println(actorToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id of the movie you want to delete:");
                    String id = keyboard.next();
                    Movie movieToDelete = actorController.findMovieById(actorToUpdate, id);
                    if (movieToDelete != null) {
                        actorController.deleteMovie(actorToUpdate, movieToDelete);
                    }
                }
            } else if (choice == 4) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new movie");
                System.out.println("2. Add an existing movie");
                int movieChoice = keyboard.nextInt();
                if (movieChoice == 1) {
                    System.out.println(actorToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id for the movie:");
                    String id = keyboard.next();
                    System.out.println("Enter title of the movie");
                    String title = keyboard.next();
                    System.out.println("Enter duration of the movie");
                    int duration = keyboard.nextInt();
                    Movie movie = new Movie(id, title, duration, new Vector<StageDirector>(), new Vector<Actor>(), new Vector<Genre>());
                    actorController.addMovie(actorToUpdate, movie);
                    movieController.addMovie(movie);
                } else if (movieChoice == 2) {
                    movieController.printAllMovies();
                    System.out.println("Enter id of the movie you want to add");
                    String id = keyboard.next();
                    Movie movie = movieController.findMovieById(id);
                    if (movie != null) {
                        actorController.addMovie(actorToUpdate, movie);
                    }
                }
            } else if (choice == 5) {
                if (!actorToUpdate.getListOfAwards().isEmpty()) {
                    System.out.println(actorToUpdate.getListOfAwards().toString());
                    System.out.println("Enter Id of the award you want to delete:");
                    String id = keyboard.next();
                    Award awardToDelete = actorController.findAwardById(actorToUpdate, id);
                    if (awardToDelete != null) {
                        actorController.deleteAward(actorToUpdate, awardToDelete);
                    }
                }
            } else if (choice == 6) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new award");
                System.out.println("2. Add an existing award");
                int awardChoice = keyboard.nextInt();
                if (awardChoice == 1) {
                    System.out.println("Enter type of award (Oscar, GoldenGlobe, PalmeDor):");
                    String type = keyboard.next();

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
                    actorController.addAward(actorToUpdate, award);
                    awardController.addAward(type, id, category);
                } else if (awardChoice == 2) {
                    awardController.printAllAwards();
                    System.out.println("Enter id of the award you want to add");
                    String id = keyboard.next();
                    Award award = awardController.findAwardById(id);
                    actorController.addAward(actorToUpdate, award);
                }
            }
        }
    }
}

package map.project.demo.UI;

import map.project.demo.Controller.ActorController;
import map.project.demo.Controller.GenreController;
import map.project.demo.Controller.MovieController;
import map.project.demo.Controller.StageDirectorController;
import map.project.demo.Domain.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

public class MovieUI {
    private final MovieController movieController;
    private final StageDirectorController stageDirectorController;
    private final ActorController actorController;
    private final GenreController genreController;

    public MovieUI(MovieController movieController, StageDirectorController stageDirectorController, ActorController actorController, GenreController genreController) {
        this.movieController = movieController;
        this.stageDirectorController = stageDirectorController;
        this.actorController = actorController;
        this.genreController = genreController;
    }

    public void mainMovieUI() throws SQLException {
        int choice;
        do {
            this.movieController.printAllMovies();
            movieMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addAMovie();
            }
            if (choice == 2) {
                deleteAMovie();
            }
            if (choice == 3) {
                movieController.deleteAllMovies();
            }
            if (choice == 4) {
                updateAMovie();
            }
        } while (choice > 0 && choice < 5);
    }

    public static void movieMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add a movie");
        System.out.println("2. Delete a movie");
        System.out.println("3. Delete all movies");
        System.out.println("4. Update a movie");
        System.out.println("5. Exit");
    }

    public void addAMovie() {
        this.movieController.printAllMovies();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter Id for the movie:");
        String id = keyboard.next();
        System.out.println("Enter title of the movie");
        String title = keyboard.next();
        System.out.println("Enter duration of the movie");
        int duration = keyboard.nextInt();

        this.movieController.addMovie(new Movie(id, title, duration, new Vector<StageDirector>(), new Vector<>(), new Vector<Genre>()));
    }

    public void addGenresToMovie(Movie movie) throws SQLException {
        Vector<String> genreIds = movieController.getGenresFromMovieGenreTable(movie);
        for (Genre genre : genreController.getAllGenres()) {
            if (genreIds.contains(genre.getId())) {
                movieController.addGenreToMovie(movie, genre);
            }
        }
    }

    public void addDirectorsToMovies(Movie movie) throws SQLException {
        Vector<String> directors = movieController.getDirectorsFromMovieDirectorTable(movie);
        for (StageDirector stageDirector : stageDirectorController.getAllStageDirectors()) {
            if (directors.contains(stageDirector.getId())) {
                movieController.addStageDirectorToMovie(movie, stageDirector);
            }
        }
    }

    public void addActorsToMovies(Movie movie) throws SQLException {
        Vector<String> actorIds = movieController.getActorsFromActorMovieTable(movie);
        for (Actor actor : actorController.getAllActors()) {
            if (actorIds.contains(actor.getId())) {
                movieController.addActorToMovie(movie, actor);
            }
        }
    }

    public void deleteAMovie() {
        this.movieController.printAllMovies();
        System.out.println("Enter ID of the movie you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.movieController.deleteMovie(id);
    }

    public void updateMovie(String movieId) throws SQLException {
        Movie movieToUpdate = movieController.findMovieById(movieId);
        if (movieToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update title");
            System.out.println("2. Update duration");
            System.out.println("3. Add a stage director");
            System.out.println("4. Delete a stage director");
            System.out.println("5. Add a genre");
            System.out.println("6. Delete a genre");
            System.out.println("7. Add an actor");
            System.out.println("8. Delete an actor");
            System.out.println("9. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new title:");
                String name = keyboard.next();
                movieController.updateMovieTitle(movieToUpdate, name);
            } else if (choice == 2) {
                System.out.println("Enter new duration:");
                int duration = keyboard.nextInt();
                movieController.updateMovieDuration(movieToUpdate, duration);
            } else if (choice == 3) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new stage director");
                System.out.println("2. Add an existing stage director");
                int directorChoice = keyboard.nextInt();
                if (directorChoice == 1) {
                    System.out.println(movieToUpdate.getStageDirectors().toString());
                    System.out.println("Enter id for stage director:");
                    String id = keyboard.next();
                    System.out.println("Enter first name:");
                    String firstName = keyboard.next();
                    System.out.println("Enter last Name:");
                    String lastName = keyboard.next();
                    StageDirector stageDirector = new StageDirector(id, firstName, lastName, new Vector<>(), new Vector<>());
                    movieController.addStageDirectorToMovie(movieToUpdate, stageDirector);
                    stageDirectorController.addStageDirector(stageDirector);
                } else if (directorChoice == 2) {
                    stageDirectorController.printAllStageDirectors();
                    System.out.println("Enter id of the stage director you want to add");
                    String id = keyboard.next();
                    StageDirector stageDirector = stageDirectorController.findStageDirectorById(id);
                    movieController.addStageDirectorToMovie(movieToUpdate, stageDirector);
                    stageDirectorController.addStageDirector(stageDirector);
                }
            } else if (choice == 4) {
                System.out.println(movieToUpdate.getStageDirectors().toString());
                System.out.println("Enter id of the stage director you want to delete");
                String id = keyboard.next();
                StageDirector stageDirector = movieController.findDirectorById(movieToUpdate, id);
                if (stageDirector != null) {
                    movieController.deleteStageDirectorFromMovie(movieToUpdate, stageDirector);
                }
            } else if (choice == 5) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new genre");
                System.out.println("2. Add an existing genre");
                int genreChoice = keyboard.nextInt();
                if (genreChoice == 1) {
                    System.out.println("Enter ID for genre:");
                    String id = keyboard.next();
                    System.out.println("Enter name for genre:");
                    String name = keyboard.next();
                    Genre genre = new Genre(id, name, new Vector<>());
                    movieController.addGenreToMovie(movieToUpdate, genre);
                    genreController.addGenre(genre);
                } else if (genreChoice == 2) {
                    genreController.printAllGenres();
                    System.out.println("Enter id of the genre you want to add");
                    String id = keyboard.next();
                    Genre genre = genreController.findGenreById(id);
                    movieController.addGenreToMovie(movieToUpdate, genre);
                }
            } else if (choice == 6) {
                System.out.println(movieToUpdate.getGenres().toString());
                System.out.println("Enter id of the genre you want to delete");
                String id = keyboard.next();
                Genre genre = movieController.findGenreById(movieToUpdate, id);
                if (genre != null) {
                    movieController.deleteGenreFromMovie(movieToUpdate, genre);
                }
            } else if (choice == 7) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new actor");
                System.out.println("2. Add an existing actor");
                int actorChoice = keyboard.nextInt();
                if (actorChoice == 1) {
                    System.out.println("Enter ID for actor:");
                    String id = keyboard.next();
                    System.out.println("Enter first name for actor:");
                    String firstName = keyboard.next();
                    System.out.println("Enter last name for actor:");
                    String lastName = keyboard.next();
                    System.out.println("Enter start of career (YYYY-MM-DD):");
                    Date startCareer = java.sql.Date.valueOf(keyboard.next());
                    Actor actor = new Actor(id, firstName, lastName, new Vector<>(), startCareer, new Vector<>());
                    movieController.addActorToMovie(movieToUpdate, actor);
                    actorController.addActor(actor);
                } else if (actorChoice == 2) {
                    actorController.showAllActors();
                    System.out.println("Enter id of the actor you want to add");
                    String id = keyboard.next();
                    Actor actor = actorController.findActorById(id);
                    movieController.addActorToMovie(movieToUpdate, actor);
                }
            } else if (choice == 8) {
                System.out.println(movieToUpdate.getActors().toString());
                System.out.println("Enter id of the actor you want to delete");
                String id = keyboard.next();
                Actor actor = movieController.findActorById(movieToUpdate, id);
                if (actor != null) {
                    movieController.deleteActorFromMovie(movieToUpdate, actor);
                }
            }
        }
    }

    public void updateAMovie() throws SQLException {
        this.movieController.printAllMovies();
        System.out.println("Enter ID of the movie you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.updateMovie(id);
    }
}

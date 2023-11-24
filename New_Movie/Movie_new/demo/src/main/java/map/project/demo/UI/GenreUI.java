package map.project.demo.UI;

import map.project.demo.Controller.GenreController;
import map.project.demo.Controller.MovieController;
import map.project.demo.Domain.*;

import java.util.Scanner;
import java.util.Vector;

public class GenreUI {
    private final GenreController genreController;
    private final MovieController movieController;

    public GenreUI(GenreController genreController, MovieController movieController) {
        this.genreController = genreController;
        this.movieController = movieController;
    }

    public void mainGenreUI() {
        int choice;
        do {
            this.genreController.printAllGenres();
            genreMenu();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Your choice:");
            choice = keyboard.nextInt();
            if (choice == 1) {
                addAGenre();
            }
            if (choice == 2) {
                deleteAGenre();
            }
            if (choice == 3) {
                genreController.deleteAllGenres();
            }
            if (choice == 4) {
                updateAGenre();
            }
        } while (choice > 0 && choice < 5);
    }

    public static void genreMenu() {
        System.out.println("What do you want to do?");
        System.out.println("1. Add a genre");
        System.out.println("2. Delete a genre");
        System.out.println("3. Delete all genres");
        System.out.println("4. Update a genre");
        System.out.println("5. Exit");
    }

    public void addAGenre() {
        this.genreController.printAllGenres();

        Scanner keyboard = new Scanner(System.in);

        System.out.println("Enter ID for genre:");
        String id = keyboard.next();

        System.out.println("Enter name for genre:");
        String name = keyboard.next();

        this.genreController.addGenre(new Genre(id, name, new Vector<>()));
    }

    public void deleteAGenre() {
        this.genreController.printAllGenres();
        System.out.println("Enter ID of the genre you want to delete:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.genreController.deleteGenre(id);
    }

    public void updateGenre(String genreId) {
        Genre genreToUpdate = genreController.findGenreById(genreId);
        if (genreToUpdate != null) {
            System.out.println("What do you want to do?");
            System.out.println("1. Update name");
            System.out.println("2. Add a movie");
            System.out.println("3. Delete a movie");
            System.out.println("4. Nothing");
            System.out.println("Enter your choice:");
            Scanner keyboard = new Scanner(System.in);
            int choice = keyboard.nextInt();
            if (choice == 1) {
                System.out.println("Enter new name:");
                String name = keyboard.next();
                genreController.updateGenreName(genreToUpdate, name);
            } else if (choice == 2) {
                System.out.println("What do you want to do?");
                System.out.println("1. Add a new movie");
                System.out.println("2. Add an existing movie");
                int movieChoice = keyboard.nextInt();
                if (movieChoice == 1) {
                    System.out.println(genreToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id for the movie:");
                    String id = keyboard.next();
                    System.out.println("Enter title of the movie");
                    String title = keyboard.next();
                    System.out.println("Enter duration of the movie");
                    int duration = keyboard.nextInt();
                    Movie movie = new Movie(id, title, duration, new Vector<StageDirector>(), new Vector<Actor>(), new Vector<Genre>());
                    genreController.addMovieToGenre(genreToUpdate, movie);
                    movieController.addMovie(movie);
                } else if (movieChoice == 2) {
                    movieController.printAllMovies();
                    System.out.println("Enter id of the movie you want to add");
                    String id = keyboard.next();
                    Movie movie = movieController.findMovieById(id);
                    genreController.addMovieToGenre(genreToUpdate, movie);
                }
            } else if (choice == 3) {
                if (!genreToUpdate.getListOfMovies().isEmpty()) {
                    System.out.println(genreToUpdate.getListOfMovies().toString());
                    System.out.println("Enter Id of the movie you want to delete:");
                    String id = keyboard.next();
                    Movie movieToDelete = genreController.findMovieById(genreToUpdate, id);
                    if (movieToDelete != null) {
                        genreController.deleteMovieFromGenre(genreToUpdate, movieToDelete);
                    }
                } else {
                    System.out.println("No movies in the list.");
                }
            }
        }
    }


    public void updateAGenre() {
        this.genreController.printAllGenres();
        System.out.println("Enter ID of the genre you want to update:");
        Scanner keyboard = new Scanner(System.in);
        String id = keyboard.next();
        this.updateGenre(id);
    }
}

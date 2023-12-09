//package map.project.demo.UI;
//
//import map.project.demo.Adapter.ScreeningPlayer;
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Controller.MovieController;
//import map.project.demo.Controller.RoomController;
//import map.project.demo.Controller.ScreeningController;
//import map.project.demo.Domain.*;
//import map.project.demo.Strategy.Screening;
//
//import java.sql.Time;
//import java.util.Objects;
//import java.util.Scanner;
//import java.util.Vector;
//
//public class ScreeningUI {
//    private final ScreeningController screeningController;
//    private final RoomController roomController;
//    private final MovieController movieController;
//
//    public ScreeningUI(ScreeningController screeningController, RoomController roomController, MovieController movieController) {
//        this.screeningController = screeningController;
//        this.roomController = roomController;
//        this.movieController = movieController;
//    }
//
//    public void mainScreeningUI() {
//        int choice;
//        do {
//            this.screeningController.printAllScreenings();
//            screeningMenu();
//            Scanner keyboard = new Scanner(System.in);
//            System.out.println("Your choice:");
//            choice = keyboard.nextInt();
//            if (choice == 1) {
//                addScreening();
//            }
//            if (choice == 2) {
//                deleteScreening();
//            }
//            if (choice == 3) {
//                screeningController.deleteAllScreenings();
//            }
//            if (choice == 4) {
//                updateScreening();
//            }
//            if (choice == 5) {
//                playScreening();
//            }
//        } while (choice > 0 && choice < 6);
//    }
//
//    public static void screeningMenu() {
//        System.out.println("What do you want to do?");
//        System.out.println("1. Add a screening");
//        System.out.println("2. Delete a screening");
//        System.out.println("3. Delete all screenings");
//        System.out.println("4. Update a screening");
//        System.out.println("5. Play screening");
//        System.out.println("6. Exit");
//    }
//
//    public void addScreening() {
//        this.screeningController.printAllScreenings();
//
//        Scanner keyboard = new Scanner(System.in);
//
//        System.out.println("Enter screening ID:");
//        String screeningId = keyboard.next();
//
//        System.out.println("Enter movie ID:");
//        String movieId = keyboard.next();
//
//        System.out.println("Enter movie title");
//        String title = keyboard.next();
//
//        System.out.println("Enter duration (in minutes):");
//        int duration = keyboard.nextInt();
//
//        System.out.println("Enter room ID:");
//        String roomId = keyboard.next();
//
//        System.out.println("Enter room number:");
//        int roomNumber = keyboard.nextInt();
//
//        System.out.println("Enter number of seats:");
//        int seats = keyboard.nextInt();
//
//        System.out.println("Enter start time (HH:mm:ss):");
//        Time startTime = Time.valueOf(keyboard.next());
//
//        System.out.println("Enter format(2D/3D/4DX):");
//        String format = keyboard.next();
//
//        if (Objects.equals(format, "2D")) {
//            this.screeningController.addScreening(new Screening2D(screeningId, new Movie(movieId, title, duration, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom(roomId, roomNumber, seats), startTime));
//        } else if (Objects.equals(format, "3D")) {
//            this.screeningController.addScreening(new Screening3D(screeningId, new Movie(movieId, title, duration, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom(roomId, roomNumber, seats), startTime));
//        } else if (Objects.equals(format, "4DX")) {
//            this.screeningController.addScreening(new Screening4DX(screeningId, new Movie(movieId, title, duration, new Vector<>(), new Vector<>(), new Vector<>()), RoomBuilder.buildRoom(roomId, roomNumber, seats), startTime));
//        }
//        roomController.addRoom(RoomBuilder.buildRoom(roomId, roomNumber, seats));
//        movieController.addMovie(new Movie(movieId, title, duration, new Vector<>(), new Vector<>(), new Vector<>()));
//    }
//
//    public void deleteScreening() {
//        this.screeningController.printAllScreenings();
//        System.out.println("Enter screening ID of the screening you want to delete:");
//        Scanner keyboard = new Scanner(System.in);
//        String screeningId = keyboard.next();
//        this.screeningController.deleteScreening(screeningId);
//    }
//
//    public void updateScreening(String screeningId) {
//        Screening screeningToUpdate = screeningController.findScreeningById(screeningId);
//        if (screeningToUpdate != null) {
//            System.out.println("What do you want to do?");
//            System.out.println("1. Update room");
//            System.out.println("2. Update start time");
//            System.out.println("3. Update movie");
//            System.out.println("4. Nothing");
//            System.out.println("Enter your choice:");
//            Scanner keyboard = new Scanner(System.in);
//            int choice = keyboard.nextInt();
//            if (choice == 1) {
//                System.out.println("What do you want to do?");
//                System.out.println("1. Add new room");
//                System.out.println("2. Add existing room");
//                int roomChoice = keyboard.nextInt();
//                if (roomChoice == 1) {
//                    System.out.println("Enter new room ID:");
//                    String roomId = keyboard.next();
//                    System.out.println("Enter room number");
//                    int number = keyboard.nextInt();
//                    System.out.println("Enter number of seats");
//                    int seats = keyboard.nextInt();
//                    Room room = RoomBuilder.buildRoom(roomId, number, seats);
//                    screeningController.updateScreeningRoom(screeningToUpdate, room);
//                    roomController.addRoom(room);
//                } else if (roomChoice == 2) {
//                    roomController.printAllRooms();
//                    System.out.println("Enter id of the room you want to add");
//                    String id = keyboard.next();
//                    Room room = roomController.findRoomById(id);
//                    screeningController.updateScreeningRoom(screeningToUpdate, room);
//                }
//            } else if (choice == 2) {
//                System.out.println("Enter new start time (HH:mm:ss):");
//                Time startTime = Time.valueOf(keyboard.next());
//                screeningController.updateScreeningStartTime(screeningToUpdate, startTime);
//            } else if (choice == 3) {
//                System.out.println("What do you want to do?");
//                System.out.println("1. Add new movie");
//                System.out.println("2. Add existing movie");
//                int movieChoice = keyboard.nextInt();
//                if (movieChoice == 1) {
//                    System.out.println("Enter movie ID:");
//                    String id = keyboard.next();
//                    System.out.println("Enter title:");
//                    String title = keyboard.next();
//                    System.out.println("Enter duration:");
//                    int duration = keyboard.nextInt();
//                    Movie movie = new Movie(id, title, duration, new Vector<>(), new Vector<>(), new Vector<>());
//                    screeningController.updateScreeningMovie(screeningToUpdate, movie);
//                    movieController.addMovie(movie);
//                } else if (movieChoice == 2) {
//                    movieController.printAllMovies();
//                    System.out.println("Enter id of the movie you want to add");
//                    String id = keyboard.next();
//                    Movie movie = movieController.findMovieById(id);
//                    screeningController.updateScreeningMovie(screeningToUpdate, movie);
//                }
//            }
//        }
//    }
//
//    public void updateScreening() {
//        this.screeningController.printAllScreenings();
//        System.out.println("Enter screening ID of the screening you want to update:");
//        Scanner keyboard = new Scanner(System.in);
//        String screeningId = keyboard.next();
//        this.updateScreening(screeningId);
//    }
//
//    public void playScreening() {
//        this.screeningController.printAllScreenings();
//        System.out.println("Enter screening id of the movie you want to play:");
//        Scanner keyboard = new Scanner(System.in);
//        String screeningId = keyboard.next();
//        this.playScreeningWithId(screeningId);
//    }
//
//    public void playScreeningWithId(String screeningId) {
//        Screening screeningToPlay = screeningController.findScreeningById(screeningId);
//        if (screeningToPlay != null) {
//            ScreeningPlayer screeningPlayer = screeningToPlay.createScreeningAdapter();
//            screeningPlayer.play();
//        }
//    }
//}

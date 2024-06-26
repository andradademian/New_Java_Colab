package map.project.demo.UI;

import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Controller.*;
import map.project.demo.Repository.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UI {
//    private static final ActorRepository actorRepo;
//
//    static {
//        try {
//            actorRepo = ActorRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final AwardRepository awardRepository;
//
//    static {
//        try {
//            awardRepository = AwardRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final CinemaRepository cinemaRepository;
//
//    static {
//        try {
//            cinemaRepository = CinemaRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final GenreRepository genreRepository;
//
//    static {
//        try {
//            genreRepository = GenreRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final MovieRepository movieRepository;
//
//    static {
//        try {
//            movieRepository = MovieRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final RoomRepository roomRepository;
//
//    static {
//        try {
//            roomRepository = RoomRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final ScreeningRepository screeningRepository;
//
//    static {
//        try {
//            screeningRepository = ScreeningRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final SpectatorRepository spectatorRepository;
//
//    static {
//        try {
////            spectatorRepository = SpectatorRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final StageDirectorRepository stageDirectorRepository;
//
//    static {
//        try {
//            stageDirectorRepository = StageDirectorRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final TicketRepository ticketRepository;
//
//    static {
//        try {
//            ticketRepository = TicketRepository.getInstance();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final AwardFactory awardFactory = AwardFactory.getInstance();
//    private static final ActorController actorController = new ActorController(actorRepo);
//    private static final AwardController awardController;
//
//    static {
//        try {
//            awardController = new AwardController(awardRepository, awardFactory);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final CinemaController cinemaController = new CinemaController(cinemaRepository);
//    private static final GenreController genreController = new GenreController(genreRepository);
//    private static final MovieController movieController;
//
//    static {
//        try {
//            movieController = new MovieController(movieRepository);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private static final RoomController roomController = new RoomController(roomRepository);
//    private static final ScreeningController screeningController = new ScreeningController(screeningRepository);
//    private static final SpectatorController spectatorController = new SpectatorController();
//    private static final StageDirectorController stageDirectorController = new StageDirectorController(stageDirectorRepository);
//    private static final TicketController ticketController = new TicketController(ticketRepository);
//    private static final ActorUI actorUI = new ActorUI(actorController, awardController, movieController);
//    private static final AwardUI awardUI = new AwardUI(awardController);
//    private static final CinemaUI cinemaUI = new CinemaUI(cinemaController, spectatorController, roomController);
//    private static final GenreUI genreUI = new GenreUI(genreController, movieController);
//    private static final MovieUI movieUI = new MovieUI(movieController, stageDirectorController, actorController, genreController);
//    private static final RoomUI roomUI = new RoomUI(roomController);
//    private static final ScreeningUI SCREENING_2_DUI = new ScreeningUI(screeningController, roomController, movieController);
//    private static final SpectatorUI spectatorUI = new SpectatorUI(spectatorController, ticketController);
//    private static final StageDirectorUI stageDirectorUI = new StageDirectorUI(stageDirectorController, movieController, awardController);
//    private static final TicketUI ticketUI = new TicketUI(ticketController, spectatorController, screeningController);
//
//    public static void mainUI() throws Exception {
//        int choice;
//        Scanner keyboard = new Scanner(System.in);
//
//        do {
//            mainMenu();
//            System.out.println("Enter your choice: ");
//            choice = keyboard.nextInt();
//
//            switch (choice) {
//                case 1:
//                    actorMenu();
//                    break;
//                case 2:
//                    awardMenu();
//                    break;
//                case 3:
//                    cinemaMenu();
//                    break;
//                case 4:
//                    genreMenu();
//                    break;
//                case 5:
//                    movieMenu();
//                    break;
//                case 6:
//                    roomMenu();
//                    break;
//                case 7:
//                    screeningMenu();
//                    break;
//                case 8:
//                    spectatorMenu();
//                    break;
//                case 9:
//                    stageDirectorMenu();
//                    break;
//                case 10:
//                    ticketMenu();
//                    break;
//                case 11:
//                    exitMenu();
//                    System.out.println("Exiting the program.");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please choose again.");
//                    break;
//            }
//
//        } while (choice != 11);
//    }
//
//
//    public static void mainMenu() {
//        System.out.println("Choose the number of the entity you want to work with");
//        System.out.println("1. Actors");
//        System.out.println("2. Awards");
//        System.out.println("3. Cinemas");
//        System.out.println("4. Genres");
//        System.out.println("5. Movies");
//        System.out.println("6. Rooms");
//        System.out.println("7. Screenings");
//        System.out.println("8. Spectators");
//        System.out.println("9. StageDirectors");
//        System.out.println("10. Tickets");
//        System.out.println("11. Exit");
//    }
//
//    public static void exitMenu() throws SQLException {
//        spectatorRepository.deleteAllFromSpectatorTable();
//        ticketRepository.deleteAllFromTicketTable();
//        screeningRepository.deleteAllFromScreeningTable();
//        actorRepo.deleteAllFromActorMovieTable();
//        actorRepo.deleteAllFromActorAwardTable();
//        stageDirectorRepository.deleteAllFromDirectorAwardTable();
//        stageDirectorRepository.deleteAllFromMovieDirectorTable();
//        movieRepository.deleteAllFromMovieGenreTable();
//        movieRepository.deleteAllFromMovieDirectorTable();
//        awardRepository.deleteAllFromAwards();
//        genreRepository.deleteAllFromGenreTable();
//        roomRepository.deleteAllRoomsFromTable();
//        stageDirectorRepository.deleteAllFromDirectorTable();
//        genreRepository.addGenresToTable();
//        roomRepository.addRoomsToTable();
//        awardRepository.addAwardsToTable();
//        actorRepo.deleteAllFromActorTable();
//        movieRepository.deleteAllFromMovieTable();
//        movieRepository.addMoviesToTable();
//        actorRepo.addActorsToTable();
//        stageDirectorRepository.addDirectorsToTable();
//        cinemaRepository.deleteAllCinemasFromTable();
//        cinemaRepository.addCinemasToTable();
//        screeningRepository.addScreeningToTable();
//        actorRepo.addToActorMovieTable();
//        movieRepository.addToActorMovieTable();
//        actorRepo.addToActorAwardTable();
//        movieRepository.addToMovieGenreTable();
//        movieRepository.addToMovieDirectorTable();
//        stageDirectorRepository.addToMovieDirectorTable();
//        stageDirectorRepository.addToDirectorAwardTable();
//        genreRepository.addToMovieGenreTable();
//        spectatorRepository.addSpectatorsToTable();
//        ticketRepository.addTicketsToTable();
//    }
//
//    public static void actorMenu() throws SQLException {
//        actorUI.mainActorUI();
//    }
//
//    public static void awardMenu() {
//        awardUI.mainAwardUI();
//    }
//
//    public static void cinemaMenu() throws SQLException {
//        cinemaUI.mainCinemaUI();
//    }
//
//    public static void genreMenu() throws SQLException {
//        genreUI.mainGenreUI();
//    }
//
//    public static void movieMenu() throws Exception {
//        movieUI.mainMovieUI();
//    }
//
//    public static void roomMenu() {
//        roomUI.mainRoomUI();
//    }
//
//    public static void screeningMenu() {
//        SCREENING_2_DUI.mainScreeningUI();
//    }
//
//    public static void spectatorMenu() throws SQLException {
//        spectatorUI.mainSpectatorUI();
//    }
//
//    public static void stageDirectorMenu() throws SQLException {
//        stageDirectorUI.mainStageDirectorUI();
//    }
//
//    public static void ticketMenu() throws ParseException, SQLException {
//        ticketUI.mainTicketUI();
//    }

}

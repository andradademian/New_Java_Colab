package map.project.demo.UI;

import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Controller.*;
import map.project.demo.Repository.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class UI {
    private static final ActorRepository actorRepo;

    static {
        try {
            actorRepo = ActorRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final AwardRepository awardRepository;

    static {
        try {
            awardRepository = AwardRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final CinemaRepository cinemaRepository = CinemaRepository.getInstance();
    private static final GenreRepository genreRepository = GenreRepository.getInstance();
    private static final MovieRepository movieRepository;

    static {
        try {
            movieRepository = MovieRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RoomRepository roomRepository;

    static {
        try {
            roomRepository = RoomRepository.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final ScreeningRepository screeningRepository = ScreeningRepository.getInstance();
    private static final SpectatorRepository spectatorRepository = SpectatorRepository.getInstance();
    private static final StageDirectorRepository stageDirectorRepository = StageDirectorRepository.getInstance();
    private static final TicketRepository ticketRepository = TicketRepository.getInstance();
    private static final AwardFactory awardFactory = AwardFactory.getInstance();
    private static final ActorController actorController = new ActorController(actorRepo);
    private static final AwardController awardController;

    static {
        try {
            awardController = new AwardController(awardRepository, awardFactory);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final CinemaController cinemaController = new CinemaController(cinemaRepository);
    private static final GenreController genreController = new GenreController(genreRepository);
    private static final MovieController movieController;

    static {
        try {
            movieController = new MovieController(movieRepository);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static final RoomController roomController = new RoomController(roomRepository);
    private static final ScreeningController screeningController = new ScreeningController(screeningRepository);
    private static final SpectatorController spectatorController = new SpectatorController(spectatorRepository);
    private static final StageDirectorController stageDirectorController = new StageDirectorController(stageDirectorRepository);
    private static final TicketController ticketController = new TicketController(ticketRepository);
    private static final ActorUI actorUI = new ActorUI(actorController, awardController, movieController);
    private static final AwardUI awardUI = new AwardUI(awardController);
    private static final CinemaUI cinemaUI = new CinemaUI(cinemaController, spectatorController, roomController);
    private static final GenreUI genreUI = new GenreUI(genreController, movieController);
    private static final MovieUI movieUI = new MovieUI(movieController, stageDirectorController, actorController, genreController);
    private static final RoomUI roomUI = new RoomUI(roomController);
    private static final ScreeningUI SCREENING_2_DUI = new ScreeningUI(screeningController, roomController, movieController);
    private static final SpectatorUI spectatorUI = new SpectatorUI(spectatorController);
    private static final StageDirectorUI stageDirectorUI = new StageDirectorUI(stageDirectorController, movieController, awardController);
    private static final TicketUI ticketUI = new TicketUI(ticketController, spectatorController, screeningController);

    public static void mainUI() throws ParseException, SQLException {
        int choice;
        Scanner keyboard = new Scanner(System.in);

        do {
            mainMenu();
            System.out.println("Enter your choice: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    actorMenu();
                    break;
                case 2:
                    awardMenu();
                    break;
                case 3:
                    cinemaMenu();
                    break;
                case 4:
                    genreMenu();
                    break;
                case 5:
                    movieMenu();
                    break;
                case 6:
                    roomMenu();
                    break;
                case 7:
                    screeningMenu();
                    break;
                case 8:
                    spectatorMenu();
                    break;
                case 9:
                    stageDirectorMenu();
                    break;
                case 10:
                    ticketMenu();
                    break;
                case 11:
                    exitMenu();
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please choose again.");
                    break;
            }

        } while (choice != 11);
    }


    public static void mainMenu() {
        System.out.println("Choose the number of the entity you want to work with");
        System.out.println("1. Actors");
        System.out.println("2. Awards");
        System.out.println("3. Cinemas");
        System.out.println("4. Genres");
        System.out.println("5. Movies");
        System.out.println("6. Rooms");
        System.out.println("7. Screenings");
        System.out.println("8. Spectators");
        System.out.println("9. StageDirectors");
        System.out.println("10. Tickets");
        System.out.println("11. Exit");
    }

    public static void exitMenu() throws SQLException {
        roomRepository.addRoomsToTable();
        awardRepository.addAwardsToTable();
        actorRepo.addActorsToTable();
        movieRepository.addMoviesToTable();
    }

    public static void actorMenu() {
        actorUI.mainActorUI();
    }

    public static void awardMenu() {
        awardUI.mainAwardUI();
    }

    public static void cinemaMenu() {
        cinemaUI.mainCinemaUI();
    }

    public static void genreMenu() {
        genreUI.mainGenreUI();
    }

    public static void movieMenu() {
        movieUI.mainMovieUI();
    }

    public static void roomMenu() {
        roomUI.mainRoomUI();
    }

    public static void screeningMenu() {
        SCREENING_2_DUI.mainScreeningUI();
    }

    public static void spectatorMenu() {
        spectatorUI.mainSpectatorUI();
    }

    public static void stageDirectorMenu() {
        stageDirectorUI.mainStageDirectorUI();
    }

    public static void ticketMenu() throws ParseException {
        ticketUI.mainTicketUI();
    }

}

//package map.project.demo.Controller;
//
//import map.project.demo.Controller.MovieController;
//import map.project.demo.Domain.*;
//import map.project.demo.Repository.MovieRepository;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.util.Vector;
//
//public class MovieControllerTest {
//    MovieRepository movieRepository = new MovieRepository();
//    MovieController movieController = new MovieController();
//
//    public MovieControllerTest() throws SQLException {
//    }
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        movieRepository.addMovieToTable(new Movie("1", "Movie1", 120, new Vector<>(), new Vector<>(), new Vector<>()));
//        movieRepository.addMovieToTable(new Movie("2", "Movie2", 150, new Vector<>(), new Vector<>(), new Vector<>()));
//        movieRepository.addMovieToTable(new Movie("3", "Movie3", 110, new Vector<>(), new Vector<>(), new Vector<>()));
//    }
//
//    @Test
//    public void shouldFindMovieById() throws SQLException {
//        String movieId = "1";
//        Movie foundMovie = movieController.getMovieWithId(movieId);
//        assertNotNull(foundMovie);
//        assertEquals(movieId, foundMovie.getId());
//    }
//
//    @Test
//    public void shouldNotFindMovieByInvalidId() throws SQLException {
//        String invalidMovieId = "InvalidId";
//        Movie foundMovie = movieController.getMovieWithId(invalidMovieId);
//        assertNull(foundMovie);
//    }
//
//    @Test
//    public void shouldFindDirectorById() throws SQLException {
//        Movie movie = movieController.getAllMovies().get(0);
//        StageDirector stageDirector = new StageDirector("1", "FirstName1", "LastName1", new Vector<String>(), new Vector<String>());
//        movieRepository.addDirectorToMovie(movie.getId(), stageDirector.getId());
//
//        boolean foundDirector = movieController.f(movie, "1");
//
//        assertTrue(foundDirector);
//    }
//
//    @Test
//    public void shouldNotFindDirectorByInvalidId() throws SQLException {
//        Movie movie = movieController.getAllMovies().get(0);
//        StageDirector stageDirector = new StageDirector("1", "FirstName1", "LastName1", new Vector<String>(), new Vector<String>());
//        movieRepository.addStageDirector(movie, stageDirector.getId());
//
//        boolean foundDirector = movieController.findDirectorById(movie, "2");
//
//        assertFalse(foundDirector);
//    }
//
//    @Test
//    public void shouldFindActorById() throws SQLException {
//        Movie movie = movieController.getAllMovies().get(0);
//        Actor actor = new Actor("1", "FirstName1", "LastName1", new Vector<>(), null, new Vector<String>());
//        movieRepository.addActor(movie, actor.getId());
//        boolean foundActor = movieController.findActorById(movie, "1");
//
//        assertTrue(foundActor);
//    }
//
//    @Test
//    public void shouldNotFindActorByInvalidId() throws SQLException {
//        Movie movie = movieController.getAllMovies().get(0);
//        Actor actor = new Actor("1", "FirstName1", "LastName1", new Vector<>(), null, new Vector<String>());
//        movieRepository.addActor(movie, actor.getId());
//
//        boolean foundActor = movieController.findActorById(movie, "2");
//
//        assertFalse(foundActor);
//    }
//
//    @Test
//    public void shouldFindGenreById() {
//        Movie movie = movieController.getAllMovies().get(0);
//        Genre genre = new Genre("1", "Action", new Vector<>());
//        movieRepository.addGenre(movie, genre.getId());
//        boolean foundGenre = movieController.findGenreById(movie, "1");
//
//        assertTrue(foundGenre);
//    }
//
//    @Test
//    public void shouldNotFindGenreByInvalidId() throws SQLException {
//        Movie movie = movieController.getAllMovies().get(0);
//        Genre genre = new Genre("1", "Action", new Vector<>());
//        movieRepository.addGenre(movie, genre.getId());
//        boolean foundGenre = movieController.f(movie, "2");
//
//        assertFalse(foundGenre);
//    }
//
//}

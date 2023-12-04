package map.project.demo.Repository;

import map.project.demo.Domain.*;

import java.sql.*;
import java.util.Vector;

public class MovieRepository {
    private static MovieRepository instance;
    private final Vector<Movie> movies;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancyIntoMovie = "INSERT INTO \"Movie\"(id, title, durationInMinutes) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancyIntoMovie = connection.prepareStatement(insertStringFancyIntoMovie);
    String insertStringFancyIntoActorMovie = "INSERT INTO \"ActorMovie\"(actorid, movieid) VALUES (?, ?) ON CONFLICT (actorid, movieid) DO NOTHING";
    PreparedStatement insertFancyIntoActorMovie = connection.prepareStatement(insertStringFancyIntoActorMovie);
    String insertStringFancyIntoMovieGenre = "INSERT INTO \"MovieGenre\"(movieid, genreid) VALUES (?, ?) ON CONFLICT (movieid, genreid) DO NOTHING";
    PreparedStatement insertFancyIntoMovieGenre = connection.prepareStatement(insertStringFancyIntoMovieGenre);
    String insertStringFancyIntoMovieDirector = "INSERT INTO \"MovieDirector\"(movieid, directorid) VALUES (?, ?) ON CONFLICT (movieid, directorid) DO NOTHING";
    PreparedStatement insertFancyIntoMovieDirector = connection.prepareStatement(insertStringFancyIntoMovieDirector);

    Statement select = connection.createStatement();


    public MovieRepository() throws SQLException {
        movies = getMoviesFromTable();
    }

    public static MovieRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    public Vector<Movie> getMoviesFromTable() throws SQLException {
        Vector<Movie> movieVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Movie\"");
        while (result.next()) {
            String id = result.getString("id");
            String movieTitle = result.getString("title");
            int durationInMinutes = result.getInt("durationInMinutes");
            movieVector.add(new Movie(id, movieTitle, durationInMinutes, getDirectorsFromMovieDirectorTable(id), getActorsFromActorMovieTable(id), getGenresFromMovieGenreTable(id)));
        }
        return movieVector;
    }

    public void deleteAllFromMovieTable() throws SQLException {
        select.execute("delete from \"Movie\"");
    }

    public void addMoviesToTable() throws SQLException {
        for (Movie movie : movies) {
            insertFancyIntoMovie.setString(1, movie.getId());
            insertFancyIntoMovie.setString(2, movie.getTitle());
            insertFancyIntoMovie.setInt(3, movie.getDurationInMinutes());
            insertFancyIntoMovie.executeUpdate();
        }
    }

    public void deleteAllFromActorMovieTable() throws SQLException {
        select.execute("delete from \"ActorMovie\"");
    }

    public void addToActorMovieTable() throws SQLException {
        for (Movie movie : movies) {
            for (String actorId : movie.getActors()) {
                insertFancyIntoActorMovie.setString(1, actorId);
                insertFancyIntoActorMovie.setString(2, movie.getId());
                insertFancyIntoActorMovie.executeUpdate();
            }
        }
    }

    public void deleteAllFromMovieGenreTable() throws SQLException {
        select.execute("delete from \"MovieGenre\"");
    }

    public void addToMovieGenreTable() throws SQLException {
        for (Movie movie : movies) {
            for (String genreId : movie.getGenres()) {
                insertFancyIntoMovieGenre.setString(1, movie.getId());
                insertFancyIntoMovieGenre.setString(2, genreId);
                insertFancyIntoMovieGenre.executeUpdate();
            }
        }
    }

    public Vector<String> getDirectorsFromMovieDirectorTable(String movieId) throws SQLException {
        Vector<String> directorIds = new Vector<>();
        PreparedStatement awardStatement = connection.prepareStatement(" SELECT AA.directorid FROM \"MovieDirector\" AA where AA.movieid=?");
        awardStatement.setString(1, movieId);
        ResultSet result = awardStatement.executeQuery();
        while (result.next()) {
            directorIds.add(result.getString("directorid"));
        }
        return directorIds;
    }

    public Vector<String> getActorsFromActorMovieTable(String movieId) throws SQLException {
        Vector<String> actorsIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT AM.actorid FROM  \"ActorMovie\" AM where AM.movieid=?");
        movieStatement.setString(1, movieId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            actorsIds.add(result.getString("actorid"));
        }
        return actorsIds;
    }

    public Vector<String> getGenresFromMovieGenreTable(String movieId) throws SQLException {
        Vector<String> genreIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT MG.genreid FROM \"MovieGenre\" MG where MG.movieid=?");
        movieStatement.setString(1, movieId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            genreIds.add(result.getString("genreid"));
        }
        return genreIds;
    }

    public void deleteAllFromMovieDirectorTable() throws SQLException {
        select.execute("delete from \"MovieDirector\"");
    }

    public void addToMovieDirectorTable() throws SQLException {
        for (Movie movie : movies) {
            for (String stageDirectorId : movie.getStageDirectors()) {
                insertFancyIntoMovieDirector.setString(1, movie.getId());
                insertFancyIntoMovieDirector.setString(2, stageDirectorId);
                insertFancyIntoMovieDirector.executeUpdate();
            }
        }
    }

    public void add(Movie movie) {
        movies.add(movie);
    }

    public void delete(Movie movie) {
        movies.remove(movie);
    }

    public void deleteAll() {
        movies.clear();
    }

    public void printAll() {
        System.out.println(movies);
    }

    public void updateTitle(Movie movie, String title) {
        movies.get(getAll().indexOf(movie)).setTitle(title);
    }

    public void updateDuration(Movie movie, int duration) {
        movies.get(getAll().indexOf(movie)).setDurationInMinutes(duration);
    }

    public void deleteStageDirector(Movie movie, String stageDirector) {
        movies.get(getAll().indexOf(movie)).deleteStageDirector(stageDirector);
    }

    public void addStageDirector(Movie movie, String stageDirector) {
        movies.get(getAll().indexOf(movie)).addStageDirector(stageDirector);
    }

    public void addActor(Movie movie, String actor) {
        movies.get(getAll().indexOf(movie)).addActor(actor);
    }

    public void deleteActor(Movie movie, String actor) {
        movies.get(getAll().indexOf(movie)).deleteActor(actor);
    }

    public void addGenre(Movie movie, String genre) {
        movies.get(getAll().indexOf(movie)).addGenre(genre);
    }

    public void deleteGenre(Movie movie, String genre) {
        movies.get(getAll().indexOf(movie)).deleteGenre(genre);
    }

    public Vector<Movie> getAll() {
        return this.movies;
    }
}

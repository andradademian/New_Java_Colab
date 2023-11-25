package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;

import java.sql.*;
import java.util.Vector;

public class MovieRepository {
    private static MovieRepository instance;
    private final Vector<Movie> movies;

    Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie","MyUser","slay");
    Statement insert=connection.createStatement();
    String insertStringFancy="INSERT INTO \"Movie\"(id, movieTitle, durationInMinutes) VALUES (?, ?, ?)";
    PreparedStatement insertFancy=connection.prepareStatement(insertStringFancy);

    Statement select=connection.createStatement();


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
        Vector<Movie> movieVector=new Vector<>();
        ResultSet result=select.executeQuery(" SELECT * FROM \"Movie\"");
        while (result.next()){
            String id=result.getString("Id");
            String movieTitle =result.getString("MovieTitle");
            int durationInMinutes=result.getInt("durationInMinutes");
            movieVector.add(new Movie(id, movieTitle, durationInMinutes, new Vector<>(), new Vector<>(), new Vector<>()));
        }
        select.execute("delete from \"Movie\"");
        return movieVector;
    }

    public void addMoviesToTable() throws SQLException {
        for(Movie movie:movies){
            insertFancy.setString(1,movie.getId());
            insertFancy.setString(2,movie.getTitle());
            insertFancy.setInt(3,movie.getDurationInMinutes());
            insertFancy.executeUpdate();
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

    public void deleteStageDirector(Movie movie, StageDirector stageDirector) {
        movies.get(getAll().indexOf(movie)).deleteStageDirector(stageDirector);
    }

    public void addStageDirector(Movie movie, StageDirector stageDirector) {
        movies.get(getAll().indexOf(movie)).addStageDirector(stageDirector);
    }

    public void addActor(Movie movie, Actor actor) {
        movies.get(getAll().indexOf(movie)).addActor(actor);
        String insertString = "INSERT INTO \"actormovie\" (actorId, movieId) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertString)) {
            insertStatement.setString(1, actor.getId());
            insertStatement.setString(2, movie.getId());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteActor(Movie movie, Actor actor) {
        movies.get(getAll().indexOf(movie)).deleteActor(actor);
        String deleteString = "DELETE FROM \"actormovie\" WHERE ActorId = ? AND MovieId = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
            deleteStatement.setString(1, actor.getId());
            deleteStatement.setString(2, movie.getId());
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void addGenre(Movie movie, Genre genre) {
        movies.get(getAll().indexOf(movie)).addGenre(genre);
    }

    public void deleteGenre(Movie movie, Genre genre) {
        movies.get(getAll().indexOf(movie)).deleteGenre(genre);
    }

    public Vector<Movie> getAll() {
        return this.movies;
    }
}

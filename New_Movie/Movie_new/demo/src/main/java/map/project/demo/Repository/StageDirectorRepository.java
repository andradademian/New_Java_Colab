package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;

import java.sql.*;
import java.util.Vector;

public class StageDirectorRepository {
    private static StageDirectorRepository instance;
    private final Vector<StageDirector> stageDirectors;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "insert into \"StageDirector\"(id, firstName, lastName) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyIntoMovieDirector = "INSERT INTO \"MovieDirector\"(movieid,directorid) VALUES (?, ?) ON CONFLICT ( movieid,directorid) DO NOTHING";
    PreparedStatement insertFancyIntoMovieDirector = connection.prepareStatement(insertStringFancyIntoMovieDirector);

    String insertStringFancyIntoDirectorAward = "INSERT INTO \"DirectorAward\"(directorid,awardid) VALUES (?, ?) ON CONFLICT (directorid, awardid) DO NOTHING";
    PreparedStatement insertFancyIntoDirectorAward = connection.prepareStatement(insertStringFancyIntoDirectorAward);
    Statement select = connection.createStatement();

    public StageDirectorRepository() throws SQLException {
        stageDirectors = getDirectorsFromTable();
    }

    public static StageDirectorRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new StageDirectorRepository();
        }
        return instance;
    }

    public Vector<StageDirector> getDirectorsFromTable() throws SQLException {
        Vector<StageDirector> stageDirectorVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"StageDirector\"");
        while (result.next()) {
            String id = result.getString("Id");
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            stageDirectorVector.add(new StageDirector(id, firstName, lastName, new Vector<>(), new Vector<>()));
        }
        return stageDirectorVector;
    }

    public void deleteAllFromDirectorTable() throws SQLException {
        select.execute("delete from \"StageDirector\"");
    }

    public void addDirectorsToTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            insertFancy.setString(1, stageDirector.getId());
            insertFancy.setString(2, stageDirector.getFirstName());
            insertFancy.setString(3, stageDirector.getLastName());
            insertFancy.executeUpdate();
        }
    }

    public void deleteAllFromMovieDirectorTable() throws SQLException {
        select.execute("delete from \"MovieDirector\"");
    }

    public void addToMovieDirectorTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            for (Movie movie : stageDirector.getListOfMovies()) {
                insertFancyIntoMovieDirector.setString(1, movie.getId());
                insertFancyIntoMovieDirector.setString(2, stageDirector.getId());
                insertFancyIntoMovieDirector.executeUpdate();
            }
        }
    }

    public Vector<String> getAwardsFromDirectorAwardTable(String directorId) throws SQLException {
        Vector<String> awardIds = new Vector<>();
        PreparedStatement awardStatement = connection.prepareStatement(" SELECT AA.awardid FROM \"StageDirector\" SD join \"DirectorAward\" AA on AA.directorid=?");
        awardStatement.setString(1, directorId);
        ResultSet result = awardStatement.executeQuery();
        while (result.next()) {
            awardIds.add(result.getString("awardid"));
        }
        return awardIds;
    }

    public Vector<String> getMoviesFromMovieDirectorTable(String directorId) throws SQLException {
        Vector<String> moviesIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT AM.movieid FROM \"StageDirector\" SD join \"MovieDirector\" AM on AM.directorid=?");
        movieStatement.setString(1, directorId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            moviesIds.add(result.getString("movieid"));
        }
        return moviesIds;
    }

    public void deleteAllFromDirectorAwardTable() throws SQLException {
        select.execute("delete from \"DirectorAward\"");
    }

    public void addToDirectorAwardTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            for (Award award : stageDirector.getAwards()) {
                insertFancyIntoDirectorAward.setString(1, stageDirector.getId());
                insertFancyIntoDirectorAward.setString(2, award.getId());
                insertFancyIntoDirectorAward.executeUpdate();
            }
        }
    }

    public void add(StageDirector stageDirector) {
        stageDirectors.add(stageDirector);
    }

    public void delete(StageDirector stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    public void deleteAll() {
        stageDirectors.clear();
    }

    public void printAll() {
        System.out.println(stageDirectors);
    }

    public void updateFirstName(StageDirector stageDirector, String firstName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setFirstName(firstName);
    }

    public void updateLastName(StageDirector stageDirector, String lastName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setLastName(lastName);
    }

    public void deleteMovie(StageDirector stageDirector, Movie movie) {
        stageDirectors.get(getAll().indexOf(stageDirector)).deleteMovie(movie);
    }

    public void addMovie(StageDirector stageDirector, Movie movie) {
        stageDirectors.get(getAll().indexOf(stageDirector)).addMovie(movie);
    }

    public void addAward(StageDirector stageDirector, Award award) {
        stageDirectors.get(getAll().indexOf(stageDirector)).addAward(award);
    }

    public void deleteAward(StageDirector stageDirector, Award award) {
        stageDirectors.get(getAll().indexOf(stageDirector)).deleteAward(award);
    }

    public Vector<StageDirector> getAll() {
        return this.stageDirectors;
    }
}

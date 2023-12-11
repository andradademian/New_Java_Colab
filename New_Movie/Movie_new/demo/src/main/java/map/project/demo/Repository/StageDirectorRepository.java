package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.StageDirector;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Actor;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.Vector;

@Repository
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

    @Transactional
    public Vector<StageDirector> getDirectorsFromTable() throws SQLException {
        Vector<StageDirector> stageDirectorVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"StageDirector\"");
        while (result.next()) {
            String id = result.getString("Id");
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            stageDirectorVector.add(new StageDirector(id, firstName, lastName, getMoviesFromMovieDirectorTable(id), getAwardsFromDirectorAwardTable(id)));
        }
        return stageDirectorVector;
    }

    @Transactional
    public StageDirector getDirectorWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"StageDirector\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            return new StageDirector(id, firstName, lastName, getMoviesFromMovieDirectorTable(id), getAwardsFromDirectorAwardTable(id));
        }
        return null;
    }

    @Transactional
    public void deleteAllFromDirectorTable() throws SQLException {
        select.execute("delete from \"StageDirector\"");
    }

    @Transactional
    public void deleteDirectorWithIdFromTable(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"StageDirector\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void addDirectorsToTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            insertFancy.setString(1, stageDirector.getId());
            insertFancy.setString(2, stageDirector.getFirstName());
            insertFancy.setString(3, stageDirector.getLastName());
            insertFancy.executeUpdate();
        }
    }

    @Transactional
    public void addDirectorToTable(StageDirector stageDirector) throws SQLException {
        insertFancy.setString(1, stageDirector.getId());
        insertFancy.setString(2, stageDirector.getFirstName());
        insertFancy.setString(3, stageDirector.getLastName());
        insertFancy.executeUpdate();
    }

    @Transactional
    public void deleteAllFromMovieDirectorTable() throws SQLException {
        select.execute("delete from \"MovieDirector\"");
    }

    @Transactional
    public void deleteAllMoviesFromDirectorWithId(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"MovieDirector\" where directorid=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteAllAwardsFromDirectorWithId(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"DirectorAward\" where directorid=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void addToMovieDirectorTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            for (String movie : stageDirector.getListOfMovies()) {
                insertFancyIntoMovieDirector.setString(1, movie);
                insertFancyIntoMovieDirector.setString(2, stageDirector.getId());
                insertFancyIntoMovieDirector.executeUpdate();
            }
        }
    }

    @Transactional
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

    @Transactional
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

    @Transactional
    public void deleteAllFromDirectorAwardTable() throws SQLException {
        select.execute("delete from \"DirectorAward\"");
    }

    @Transactional
    public void addToDirectorAwardTable() throws SQLException {
        for (StageDirector stageDirector : stageDirectors) {
            for (String awardId : stageDirector.getAwards()) {
                insertFancyIntoDirectorAward.setString(1, stageDirector.getId());
                insertFancyIntoDirectorAward.setString(2, awardId);
                insertFancyIntoDirectorAward.executeUpdate();
            }
        }
    }

    @Transactional
    public void add(StageDirector stageDirector) {
        stageDirectors.add(stageDirector);
    }

    @Transactional
    public void delete(StageDirector stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    @Transactional
    public void deleteAll() {
        stageDirectors.clear();
    }

    @Transactional
    public void updateFirstName(StageDirector stageDirector, String firstName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setFirstName(firstName);
    }

    @Transactional
    public void updateLastName(StageDirector stageDirector, String lastName) {
        stageDirectors.get(getAll().indexOf(stageDirector)).setLastName(lastName);
    }

    @Transactional
    public void deleteMovie(String stagedirectorId, String movieId) throws SQLException {
        PreparedStatement movieStatement = connection.prepareStatement(" delete FROM \"MovieDirector\" MD where MD.movieid=? and MD.directorid=?");
        movieStatement.setString(1, movieId);
        movieStatement.setString(2, stagedirectorId);
        movieStatement.execute();
    }

    @Transactional
    public void addMovie(String stagedirectorId, String movieId) throws SQLException {
        insertFancyIntoMovieDirector.setString(1, movieId);
        insertFancyIntoMovieDirector.setString(2, stagedirectorId);
        insertFancyIntoMovieDirector.executeUpdate();

    }

    public void addAward(StageDirector stageDirector, String awardId) {
        stageDirectors.get(getAll().indexOf(stageDirector)).addAward(awardId);
    }

    @Transactional
    public void deleteAward(String stagedirectorId, String awardId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" delete FROM \"DirectorAward\" DA where DA.directorid=? and DA.awardid=?");
        statement.setString(1, stagedirectorId);
        statement.setString(2, awardId);
        statement.execute();
    }

    @Transactional
    public void addAward(String stagedirectorId, String awardId) throws SQLException {
        insertFancyIntoDirectorAward.setString(1, stagedirectorId);
        insertFancyIntoDirectorAward.setString(2, awardId);
        insertFancyIntoDirectorAward.executeUpdate();
    }

    @Transactional
    public Vector<StageDirector> getAll() {
        return this.stageDirectors;
    }
}

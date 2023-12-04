package map.project.demo.Repository;

import map.project.demo.Domain.Actor;

import java.sql.*;
import java.util.Vector;

public class ActorRepository {
    private static ActorRepository instance;
    private final Vector<Actor> listOfActors;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "insert into \"Actor\"(id, firstName, lastName, startOfCareer) VALUES (?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyIntoActorMovie = "INSERT INTO \"ActorMovie\"(actorid, movieid) VALUES (?, ?) ON CONFLICT (actorid, movieid) DO NOTHING";
    PreparedStatement insertFancyIntoActorMovie = connection.prepareStatement(insertStringFancyIntoActorMovie);

    String insertStringFancyIntoActorAward = "INSERT INTO \"ActorAward\"(actorid,awardid) VALUES (?, ?) ON CONFLICT (actorid, awardid) DO NOTHING";
    PreparedStatement insertFancyIntoActorAward = connection.prepareStatement(insertStringFancyIntoActorAward);
    Statement select = connection.createStatement();

    private ActorRepository() throws SQLException {
        listOfActors = getActorsFromTable();
    }

    public static ActorRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new ActorRepository();
        }
        return instance;
    }

    public Vector<Actor> getActorsFromTable() throws SQLException {
        Vector<Actor> actorVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Actor\"");
        while (result.next()) {
            String id = result.getString("Id");
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            Date startOfCareer = result.getDate("StartOfCareer");
            Actor actor = new Actor(id, firstName, lastName, getMoviesFromActorMovieTable(id), startOfCareer, getAwardsFromActorAwardTable(id));
            actorVector.add(actor);
        }
        return actorVector;
    }

    public void deleteAllFromActorTable() throws SQLException {
        select.execute("delete from \"Actor\"");
    }

    public void addActorsToTable() throws SQLException {
        for (Actor actor : listOfActors) {
            insertFancy.setString(1, actor.getId());
            insertFancy.setString(2, actor.getFirstName());
            insertFancy.setString(3, actor.getLastName());
            insertFancy.setDate(4, actor.getStartOfCareer());
            insertFancy.executeUpdate();
        }
    }

    public void deleteAllFromActorMovieTable() throws SQLException {
        select.execute("delete from \"ActorMovie\"");
    }

    public void addToActorMovieTable() throws SQLException {
        for (Actor actor : listOfActors) {
            for (String movie : actor.getListOfMovies()) {
                insertFancyIntoActorMovie.setString(1, actor.getId());
                insertFancyIntoActorMovie.setString(2, movie);
                insertFancyIntoActorMovie.executeUpdate();
            }
        }
    }

    public void deleteAllFromActorAwardTable() throws SQLException {
        select.execute("delete from \"ActorAward\"");
    }

    public void addToActorAwardTable() throws SQLException {
        for (Actor actor : listOfActors) {
            for (String award : actor.getListOfAwards()) {
                insertFancyIntoActorAward.setString(1, actor.getId());
                insertFancyIntoActorAward.setString(2, award);
                insertFancyIntoActorAward.executeUpdate();
            }
        }
    }

    public Vector<String> getAwardsFromActorAwardTable(String actorId) throws SQLException {
        Vector<String> awardIds = new Vector<>();
        PreparedStatement awardStatement = connection.prepareStatement(" SELECT AA.awardid FROM \"ActorAward\" AA where AA.actorid=?");
        awardStatement.setString(1, actorId);
        ResultSet result = awardStatement.executeQuery();
        while (result.next()) {
            awardIds.add(result.getString("awardid"));
        }
        return awardIds;
    }

    public Vector<String> getMoviesFromActorMovieTable(String actorId) throws SQLException {
        Vector<String> moviesIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT AM.movieid FROM \"ActorMovie\" AM where AM.actorid=?");
        movieStatement.setString(1, actorId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            moviesIds.add(result.getString("movieid"));
        }
        return moviesIds;
    }

    public void add(Actor actor) {
        listOfActors.add(actor);
    }

    public void delete(Actor actor) {
        listOfActors.remove(actor);
    }

    public void deleteAll() {
        listOfActors.clear();
    }

    public void printAll() {
        System.out.println(listOfActors);
    }

    public void updateFirstName(Actor actor, String firstName) {
        listOfActors.get(getAll().indexOf(actor)).setFirstName(firstName);
    }

    public void updateLastName(Actor actor, String lastName) {
        listOfActors.get(getAll().indexOf(actor)).setLastName(lastName);
    }

    public void deleteMovie(Actor actor, String movieId) throws SQLException {
        listOfActors.get(getAll().indexOf(actor)).removeMovie(movieId);
    }

    public void addMovie(Actor actor, String movieId) {
        listOfActors.get(getAll().indexOf(actor)).addMovie(movieId);

    }

    public void deleteAward(Actor actor, String awardId) {
        listOfActors.get(getAll().indexOf(actor)).removeAward(awardId);
    }

    public void addAward(Actor actor, String awardId) {
        listOfActors.get(getAll().indexOf(actor)).addAward(awardId);
    }

    public Vector<Actor> getAll() {
        return this.listOfActors;
    }
}

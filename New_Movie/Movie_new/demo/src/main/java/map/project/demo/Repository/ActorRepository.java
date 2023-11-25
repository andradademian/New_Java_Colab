package map.project.demo.Repository;

import map.project.demo.AwardFactory.GoldenGlobe;
import map.project.demo.AwardFactory.Oscar;
import map.project.demo.AwardFactory.PalmeDor;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Movie;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class ActorRepository {
    private static ActorRepository instance;
    private final Vector<Actor> listOfActors;

    Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie","MyUser","slay");
    Statement insert=connection.createStatement();
    String insertStringFancy="INSERT INTO \"Actor\"(id, firstName, lastName, startOfCareer) VALUES (?, ?, ?, ?)";
    PreparedStatement insertFancy=connection.prepareStatement(insertStringFancy);

    Statement select=connection.createStatement();

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
        ResultSet result=select.executeQuery(" SELECT * FROM \"actor\"");
        while (result.next()){
            String id=result.getString("Id");
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            Date startOfCareer = result.getDate("StartOfCareer");

        }
        select.execute("delete from \"actor\"");
        return actorVector;
    }

    public void addActorsToTable() throws SQLException {
        for(Actor actor:listOfActors){
            insertFancy.setString(1,actor.getId());
            insertFancy.setString(2,actor.getFirstName());
            insertFancy.setString(3,actor.getLastName());
            insertFancy.setDate(4,actor.getStartOfCareer());
            insertFancy.executeUpdate();
        }
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

    public void deleteMovie(Actor actor, Movie movie) throws SQLException {
        listOfActors.get(getAll().indexOf(actor)).removeMovie(movie);

        String deleteString = "DELETE FROM \"actormovie\" WHERE actorId = ? AND movieId = ?";
        try (PreparedStatement deleteStatement = connection.prepareStatement(deleteString)) {
            deleteStatement.setString(1, actor.getId());
            deleteStatement.setString(2, movie.getId());
            deleteStatement.executeUpdate();
        }
    }

    public void addMovie(Actor actor, Movie movie) {
        listOfActors.get(getAll().indexOf(actor)).addMovie(movie);

        String insertString = "INSERT INTO \"actormovie\" (actorId, movieId) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertString)) {
            insertStatement.setString(1, actor.getId());
            insertStatement.setString(2, movie.getId());
            insertStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteAward(Actor actor, Award award) {
        listOfActors.get(getAll().indexOf(actor)).removeAward(award);
    }

    public void addAward(Actor actor, Award award) {
        listOfActors.get(getAll().indexOf(actor)).addAward(award);
    }

    public Vector<Actor> getAll() {
        return this.listOfActors;
    }
}

package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Optional;
import java.util.Vector;

@Repository
public class ActorRepository {
    private final Vector<Actor> listOfActors;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
    Statement insert = connection.createStatement();
    String insertStringFancy = "insert into \"Actor\"(id, firstName, lastName, startOfCareer) VALUES (?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyIntoActorMovie = "INSERT INTO \"ActorMovie\"(actorid, movieid) VALUES (?, ?) ON CONFLICT (actorid, movieid) DO NOTHING";
    PreparedStatement insertFancyIntoActorMovie = connection.prepareStatement(insertStringFancyIntoActorMovie);

    String insertStringFancyIntoActorAward = "INSERT INTO \"ActorAward\"(actorid,awardid) VALUES (?, ?) ON CONFLICT (actorid, awardid) DO NOTHING";
    PreparedStatement insertFancyIntoActorAward = connection.prepareStatement(insertStringFancyIntoActorAward);
    Statement select = connection.createStatement();

    public ActorRepository() throws SQLException {
        listOfActors = getActorsFromTable();
    }


    //    @Transactional
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

    //    @Transactional
    public Actor getActorWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Actor\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String firstName = result.getString("FirstName");
            String lastName = result.getString("LastName");
            Date startOfCareer = result.getDate("StartOfCareer");
            return new Actor(id, firstName, lastName, getMoviesFromActorMovieTable(id), startOfCareer, getAwardsFromActorAwardTable(id));
        }
        return null;
    }

    //    @Transactional
    public void deleteActorWithIdFromTable(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Actor\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteAllFromActorTable() throws SQLException {
        select.execute("delete from \"Actor\"");
    }

//    @Transactional
//    public void addActorsToTable() throws SQLException {
//        for (Actor actor : listOfActors) {
//            insertFancy.setString(1, actor.getId());
//            insertFancy.setString(2, actor.getFirstName());
//            insertFancy.setString(3, actor.getLastName());
//            insertFancy.setDate(4, actor.getStartOfCareer());
//            insertFancy.executeUpdate();
//        }
//    }

//    @Transactional
//    public void addActorToTable(Actor actor) throws SQLException {
//        insertFancy.setString(1, actor.getId());
//        insertFancy.setString(2, actor.getFirstName());
//        insertFancy.setString(3, actor.getLastName());
//        insertFancy.setDate(4, actor.getStartOfCareer());
//        insertFancy.executeUpdate();
//    }

    @Transactional
    public void deleteAllFromActorMovieTable() throws SQLException {
        select.execute("delete from \"ActorMovie\"");
    }

//    @Transactional
//    public void addToActorMovieTable() throws SQLException {
//        for (Actor actor : listOfActors) {
//            for (String movie : actor.getListOfMovies()) {
//                insertFancyIntoActorMovie.setString(1, actor.getId());
//                insertFancyIntoActorMovie.setString(2, movie);
//                insertFancyIntoActorMovie.executeUpdate();
//            }
//        }
//    }

    @Transactional
    public void deleteAllFromActorAwardTable() throws SQLException {
        select.execute("delete from \"ActorAward\"");
    }

//    @Transactional
//    public void addToActorAwardTable() throws SQLException {
//        for (Actor actor : listOfActors) {
//            for (String award : actor.getListOfAwards()) {
//                insertFancyIntoActorAward.setString(1, actor.getId());
//                insertFancyIntoActorAward.setString(2, award);
//                insertFancyIntoActorAward.executeUpdate();
//            }
//        }
//    }

    //    @Transactional
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

    //    @Transactional
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

    @Transactional
    public void add(Actor actor) {
        listOfActors.add(actor);
    }

//    @Override
//    @Transactional
//    public <S extends Actor> S save(S entity) {
//        try {
//            insertFancy.setString(1, entity.getId());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            insertFancy.setString(2, entity.getFirstName());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            insertFancy.setString(3, entity.getLastName());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            insertFancy.setDate(4, entity.getStartOfCareer());
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            insertFancy.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    @Override
//    public <S extends Actor> Iterable<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//
//    @Transactional
//    @Override
//    public Optional<Actor> findById(String id) {
//        PreparedStatement statement;
//        try {
//            statement = connection.prepareStatement(" SELECT * FROM \"Actor\" where Id=?");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            statement.setString(1, id);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ResultSet result;
//        try {
//            result = statement.executeQuery();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            if (result.next()) {
//                String firstName = result.getString("FirstName");
//                String lastName = result.getString("LastName");
//                Date startOfCareer = result.getDate("StartOfCareer");
//                return Optional.of(new Actor(id, firstName, lastName, getMoviesFromActorMovieTable(id), startOfCareer, getAwardsFromActorAwardTable(id)));
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return null;
//    }
//
//    @Override
//    public boolean existsById(String id) {
//        try {
//            return this.getActorWithIdFromTable(id) != null;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Transactional
//    @Override
//    public Iterable<Actor> findAll() {
//        try {
//            return getActorsFromTable();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Override
//    public Iterable<Actor> findAllById(Iterable<String> strings) {
//        Vector<Actor> actors = new Vector<>();
//        for (String id : strings) {
//            try {
//                actors.add(getActorWithIdFromTable(id));
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        return actors;
//    }
//
//    @Override
//    public long count() {
//        Statement countStatement;
//        try {
//            countStatement = connection.createStatement();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        ResultSet countResult;
//        try {
//            countResult = countStatement.executeQuery("SELECT COUNT(*) FROM \"Actor\"");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            if (countResult.next()) {
//                return countResult.getLong(1);
//            } else {
//                return 0;
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Transactional
//    @Override
//    public void deleteById(String id) {
//        PreparedStatement preparedStatement;
//        try {
//            preparedStatement = connection.prepareStatement("delete from \"Actor\" where id=?");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            preparedStatement.setString(1, id);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Transactional
    public void delete(Actor actor) {
        listOfActors.remove(actor);
    }

//    @Transactional
//    @Override
//    public void deleteAllById(Iterable<? extends String> strings) {
//        for (String id : strings) {
//            try {
//                this.deleteActorWithIdFromTable(id);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    @Override
//    public void deleteAll(Iterable<? extends Actor> entities) {
//        for (Actor actor : entities) {
//            try {
//                this.deleteActorWithIdFromTable(actor.getId());
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }

    @Transactional
    public void deleteAll() {
        listOfActors.clear();
    }


    @Transactional
    public void updateFirstName(Actor actor, String firstName) {
        listOfActors.get(getAll().indexOf(actor)).setFirstName(firstName);
    }

    @Transactional
    public void updateLastName(Actor actor, String lastName) {
        listOfActors.get(getAll().indexOf(actor)).setLastName(lastName);
    }

    @Transactional
    public void deleteMovie(String actorId, String movieId) throws SQLException {
        PreparedStatement movieStatement = connection.prepareStatement(" delete FROM \"ActorMovie\" AM where AM.actorid=? and AM.movieid=?");
        movieStatement.setString(1, actorId);
        movieStatement.setString(2, movieId);
        movieStatement.execute();
    }

    @Transactional
    public void deleteAward(String actorId, String awardId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" delete FROM \"ActorAward\" AA where AA.actorid=? and AA.awardid=?");
        statement.setString(1, actorId);
        statement.setString(2, awardId);
        statement.execute();
    }

    @Transactional
    public void addMovie(String actorId, String movieId) throws SQLException {
        insertFancyIntoActorMovie.setString(1, actorId);
        insertFancyIntoActorMovie.setString(2, movieId);
        insertFancyIntoActorMovie.executeUpdate();

    }

    @Transactional
    public void addAward(String actorId, String awardId) throws SQLException {
        insertFancyIntoActorAward.setString(1, actorId);
        insertFancyIntoActorAward.setString(2, awardId);
        insertFancyIntoActorAward.executeUpdate();
    }

    @Transactional
    public Vector<Actor> getAll() {
        return this.listOfActors;
    }
}

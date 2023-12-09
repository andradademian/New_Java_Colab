package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

@Repository
public class ScreeningRepository {
    private final Vector<Screening> screenings;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Screening\"(id, screeningformat,starttime,movieid,roomid) VALUES (?, ?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    Statement select = connection.createStatement();

    public ScreeningRepository() throws SQLException {
        screenings = getScreeningsFromTable();
    }

//    public static ScreeningRepository getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new ScreeningRepository();
//        }
//        return instance;
//    }

    @Transactional
    public Vector<Screening> getScreeningsFromTable() throws SQLException {
        Vector<Screening> screeningVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Screening\"");
        while (result.next()) {
            String id = result.getString("id");
            String format = result.getString("screeningformat");
            Time startTime = result.getTime("starttime");
            String movieId = result.getString("movieid");
            String roomId = result.getString("roomid");
            PreparedStatement movieStatement = connection.prepareStatement(" select * from \"Movie\" where Id=?");
            movieStatement.setString(1, movieId);
            ResultSet resultMovie = movieStatement.executeQuery();
            PreparedStatement roomStatement = connection.prepareStatement(" select * from \"Room\" where Id=?");
            roomStatement.setString(1, roomId);
            ResultSet resultRoom = roomStatement.executeQuery();
            if (resultRoom.next() && resultMovie.next()) {
                String title = resultMovie.getString("title");
                int duration = resultMovie.getInt("durationinminutes");
                Movie movie = new Movie(movieId, title, duration, getDirectorsFromMovieDirectorTable(movieId), getActorsFromActorMovieTable(movieId), getGenresFromMovieGenreTable(movieId));
                Room room = RoomBuilder.buildRoom(roomId, resultRoom.getInt("roomnumber"), resultRoom.getInt("numberofseats"));
                Screening screening;
                if (Objects.equals(format, "2D")) {
                    screening = new Screening2D(id, movie, room, startTime);
                } else if (Objects.equals(format, "3D")) {
                    screening = new Screening3D(id, movie, room, startTime);
                } else {
                    screening = new Screening4DX(id, movie, room, startTime);
                }
                screeningVector.add(screening);
            }
        }
        return screeningVector;
    }

    @Transactional
    public Screening getScreeningWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Screening\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        while (result.next()) {
            String format = result.getString("screeningformat");
            Time startTime = result.getTime("starttime");
            String movieId = result.getString("movieid");
            String roomId = result.getString("roomid");
            PreparedStatement movieStatement = connection.prepareStatement(" select * from \"Movie\" where Id=?");
            movieStatement.setString(1, movieId);
            ResultSet resultMovie = movieStatement.executeQuery();
            PreparedStatement roomStatement = connection.prepareStatement(" select * from \"Room\" where Id=?");
            roomStatement.setString(1, roomId);
            ResultSet resultRoom = roomStatement.executeQuery();
            if (resultRoom.next() && resultMovie.next()) {
                String title = resultMovie.getString("title");
                int duration = resultMovie.getInt("durationinminutes");
                Movie movie = new Movie(movieId, title, duration, getDirectorsFromMovieDirectorTable(movieId), getActorsFromActorMovieTable(movieId), getGenresFromMovieGenreTable(movieId));
                Room room = RoomBuilder.buildRoom(roomId, resultRoom.getInt("roomnumber"), resultRoom.getInt("numberofseats"));
                Screening screening;
                if (Objects.equals(format, "2D")) {
                    screening = new Screening2D(id, movie, room, startTime);
                } else if (Objects.equals(format, "3D")) {
                    screening = new Screening3D(id, movie, room, startTime);
                } else {
                    screening = new Screening4DX(id, movie, room, startTime);
                }
                return screening;
            }
        }
        return null;
    }

    @Transactional
    public void deleteAllFromScreeningTable() throws SQLException {
        select.execute("delete from \"Screening\"");
    }

    @Transactional
    public void addScreeningToTable(Screening screening) throws SQLException {
        insertFancy.setString(1, screening.getId());
        insertFancy.setString(2, screening.getFormat());
        insertFancy.setTime(3, screening.getTime());
        insertFancy.setString(4, screening.getMovie().getId());
        insertFancy.setString(5, screening.getRoom().getId());
        insertFancy.executeUpdate();
    }

//    @Transactional
//    public void add(Screening screening) {
//        screenings.add(screening);
//    }

    @Transactional
    public void deleteScreeningWithIdFromTable(String screeningId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Screening\" where id=?");
        preparedStatement.setString(1, screeningId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void updateStartTime(String screeningId, Time startTime) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Screening\" set starttime=? where id=?");
        preparedStatement.setTime(1, startTime);
        preparedStatement.setString(2, screeningId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void updateRoom(String screeningId, String roomId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Screening\" set roomid=? where id=?");
        preparedStatement.setString(1, roomId);
        preparedStatement.setString(2, screeningId);
        preparedStatement.executeUpdate();
    }

    public void updateMovie(String screeningId, String movieId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Screening\" set movieid=? where id=?");
        preparedStatement.setString(1, movieId);
        preparedStatement.setString(2, screeningId);
        preparedStatement.executeUpdate();
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

//    @Transactional
//    public void deleteAll() {
//        screenings.clear();
//    }

//    @Transactional
//    public void printAll() {
//        System.out.println(screenings);
//    }

//    @Transactional
//    public void updateMovie(Screening screening, Movie movie) {
//        screenings.get(getAll().indexOf(screening)).setMovie(movie);
//    }
//
//    @Transactional
//    public void updateRoom(Screening screening, Room room) {
//        screenings.get(getAll().indexOf(screening)).setIdRoom(room);
//    }
//
//    @Transactional
//    public void updateStartTime(Screening screening, Time startTime) {
//        screenings.get(getAll().indexOf(screening)).setStartTime(startTime);
//    }

//    @Transactional
//    public Vector<Screening> getAll() {
//        return this.screenings;
//    }
}

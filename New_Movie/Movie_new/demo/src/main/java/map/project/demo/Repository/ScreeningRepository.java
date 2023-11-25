package map.project.demo.Repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class ScreeningRepository {
    private static ScreeningRepository instance;
    private final Vector<Screening> screenings;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Screening\"(id, screeningformat,starttime,movieid,roomid) VALUES (?, ?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    Statement select = connection.createStatement();

    public ScreeningRepository() throws SQLException {
        screenings = getScreeningsFromTable();
    }

    public static ScreeningRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new ScreeningRepository();
        }
        return instance;
    }

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
                Movie movie = new Movie(movieId, title, duration, new Vector<StageDirector>(), new Vector<Actor>(), new Vector<Genre>());
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

    public void deleteAllFromScreeningTable() throws SQLException {
        select.execute("delete from \"Screening\"");
    }

    public void addScreeningToTable() throws SQLException {
        for (Screening screening : screenings) {
            insertFancy.setString(1, screening.getId());
            insertFancy.setString(2, screening.getFormat());
            insertFancy.setTime(3, screening.getTime());
            insertFancy.setString(4, screening.getMovie().getId());
            insertFancy.setString(5, screening.getRoom().getId());
            insertFancy.executeUpdate();
        }
    }

    public void add(Screening screening) {
        screenings.add(screening);
    }

    public void delete(Screening screening) {
        screenings.remove(screening);
    }

    public void deleteAll() {
        screenings.clear();
    }

    public void printAll() {
        System.out.println(screenings);
    }

    public void updateMovie(Screening screening, Movie movie) {
        screenings.get(getAll().indexOf(screening)).setMovie(movie);
    }

    public void updateRoom(Screening screening, Room room) {
        screenings.get(getAll().indexOf(screening)).setIdRoom(room);
    }

    public void updateStartTime(Screening screening, Time startTime) {
        screenings.get(getAll().indexOf(screening)).setStartTime(startTime);
    }

    public Vector<Screening> getAll() {
        return this.screenings;
    }
}

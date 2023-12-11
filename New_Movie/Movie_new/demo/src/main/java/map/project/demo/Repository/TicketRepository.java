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
public class TicketRepository {
    private final Vector<Ticket> tickets;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Ticket\"(id, screeningid, price,seatnumber,\"SpectatorId\") VALUES (?, ?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);

    Statement select = connection.createStatement();

    public TicketRepository() throws SQLException {
        tickets = getTicketsFromTable();
    }

    @Transactional
    public Ticket getTicketWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Ticket\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String screeningId = result.getString(("screeningid"));
            float price = result.getFloat("price");
            int seatNumber = result.getInt("seatnumber");
            String spectatorId = result.getString("SpectatorId");
            Screening screening;
            Spectator spectator;
            PreparedStatement spectatorStatement = connection.prepareStatement("select * from \"Spectator\" where Id=?");
            spectatorStatement.setString(1, spectatorId);
            ResultSet resultSpectator = spectatorStatement.executeQuery();
            PreparedStatement screeningStatement = connection.prepareStatement("select * from \"Screening\" where Id=?");
            screeningStatement.setString(1, screeningId);
            ResultSet resultScreening = screeningStatement.executeQuery();
            if (resultScreening.next()) {
                String format = resultScreening.getString("screeningformat");
                Time startTime = resultScreening.getTime("starttime");
                String movieId = resultScreening.getString("movieid");
                String roomId = resultScreening.getString("roomid");
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
                    if (Objects.equals(format, "2D")) {
                        screening = new Screening2D(screeningId, movie, room, startTime);
                    } else if (Objects.equals(format, "3D")) {
                        screening = new Screening3D(screeningId, movie, room, startTime);
                    } else {
                        screening = new Screening4DX(screeningId, movie, room, startTime);
                    }
                    if (resultSpectator.next()) {
                        spectator = new Spectator(spectatorId, resultSpectator.getString("firstname"), resultSpectator.getString("lastname"));
                        return new Ticket(id, screening, price, seatNumber);
                    }
                }
            }
        }
        return null;
    }

    @Transactional
    public void deleteTicketWithId(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Ticket\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public Vector<Ticket> getTicketsFromTable() throws SQLException {
        Vector<Ticket> ticketVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Ticket\"");
        while (result.next()) {
            String id = result.getString("id");
            String screeningId = result.getString(("screeningid"));
            float price = result.getFloat("price");
            int seatNumber = result.getInt("seatnumber");
            String spectatorId = result.getString("SpectatorId");
            Screening screening;
            Spectator spectator;
            PreparedStatement spectatorStatement = connection.prepareStatement("select * from \"Spectator\" where Id=?");
            spectatorStatement.setString(1, spectatorId);
            ResultSet resultSpectator = spectatorStatement.executeQuery();
            PreparedStatement screeningStatement = connection.prepareStatement("select * from \"Screening\" where Id=?");
            screeningStatement.setString(1, screeningId);
            ResultSet resultScreening = screeningStatement.executeQuery();
            if (resultScreening.next()) {
                String format = resultScreening.getString("screeningformat");
                Time startTime = resultScreening.getTime("starttime");
                String movieId = resultScreening.getString("movieid");
                String roomId = resultScreening.getString("roomid");
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
                    if (Objects.equals(format, "2D")) {
                        screening = new Screening2D(screeningId, movie, room, startTime);
                    } else if (Objects.equals(format, "3D")) {
                        screening = new Screening3D(screeningId, movie, room, startTime);
                    } else {
                        screening = new Screening4DX(screeningId, movie, room, startTime);
                    }
                    if (resultSpectator.next()) {
                        spectator = new Spectator(spectatorId, resultSpectator.getString("firstname"), resultSpectator.getString("lastname"));
                        ticketVector.add(new Ticket(id, screening, price, seatNumber));
                    }
                }
            }
        }
        return ticketVector;
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

    @Transactional
    public void deleteAllFromTicketTable() throws SQLException {
        select.execute("delete from \"Ticket\"");
    }

    @Transactional
    public void addTicketToTable(Ticket ticket) throws SQLException {
        PreparedStatement spectatorStatement = connection.prepareStatement("select Id from \"Spectator\" where ticketid=?");
        spectatorStatement.setString(1, ticket.getId());
        ResultSet resultSpectator = spectatorStatement.executeQuery();
        String id;
        if (resultSpectator.next()) {
            id = resultSpectator.getString("id");
            insertFancy.setString(1, ticket.getId());
            insertFancy.setString(2, ticket.getScreening().getId());
            insertFancy.setDouble(3, ticket.getPrice());
            insertFancy.setInt(4, ticket.getSeatNumber());
            insertFancy.setString(5, id);
            insertFancy.executeUpdate();
        }
    }

    @Transactional
    public void updatePrice(String ticketId, float price) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Ticket\" set price=? where id=?");
        preparedStatement.setFloat(1, price);
        preparedStatement.setString(2, ticketId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void updateSeatNumber(String ticketId, int seatNumber) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Ticket\" set seatnumber=? where id=?");
        preparedStatement.setInt(1, seatNumber);
        preparedStatement.setString(2, ticketId);
        preparedStatement.executeUpdate();
    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    public void delete(Ticket ticket) {
        tickets.remove(ticket);
    }

    public Vector<Ticket> getAll() {
        return tickets;
    }

    public void deleteAll() {
        tickets.clear();
    }
}

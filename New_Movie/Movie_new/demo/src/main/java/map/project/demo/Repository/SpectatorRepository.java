package map.project.demo.Repository;

import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

@Repository
public class SpectatorRepository {
    private final Vector<Spectator> spectators;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Spectator\"(id,firstname, lastname, ticketid) VALUES (?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyTicket = "INSERT INTO \"Ticket\"(id, screeningid, price,seatnumber,\"SpectatorId\") VALUES (?, ?, ?, ?, ?)on conflict (id) do nothing";
    PreparedStatement insertFancyTicket = connection.prepareStatement(insertStringFancyTicket);
    Statement select = connection.createStatement();

    public SpectatorRepository() throws SQLException {
        spectators = getSpectatorsFromTable();
    }

    @Transactional
    public Vector<Spectator> getSpectatorsFromTable() throws SQLException {
        Vector<Spectator> spectatorVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Spectator\"");
        while (result.next()) {
            String id = result.getString("id");
            String firstName = result.getString("firstname");
            String lastName = result.getString("lastname");
            Spectator spectator = new Spectator(id, firstName, lastName);
            String ticketid = result.getString("ticketid");
            PreparedStatement ticketStatement = connection.prepareStatement(" select * from \"Ticket\" where Id=?");
            ticketStatement.setString(1, ticketid);
            ResultSet resultTicket = ticketStatement.executeQuery();
            Screening screening;
            if (resultTicket.next()) {
                String screeningId = resultTicket.getString("screeningid");
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
                        Movie movie = new Movie(movieId, title, duration, new Vector<String>(), new Vector<String>(), new Vector<String>());
                        Room room = RoomBuilder.buildRoom(roomId, resultRoom.getInt("roomnumber"), resultRoom.getInt("numberofseats"));
                        if (Objects.equals(format, "2D")) {
                            screening = new Screening2D(screeningId, movie, room, startTime);
                        } else if (Objects.equals(format, "3D")) {
                            screening = new Screening3D(screeningId, movie, room, startTime);
                        } else {
                            screening = new Screening4DX(screeningId, movie, room, startTime);
                        }
                        float price = resultTicket.getFloat("price");
                        int seatnr = resultTicket.getInt("seatnumber");
                        Ticket ticket = new Ticket(ticketid, screening, price, seatnr);
                        spectator.setTicket(ticket);
                    }
                }
            }
            spectatorVector.add(spectator);
        }
        return spectatorVector;
    }

    @Transactional
    public Spectator getSpectatorWithIdFromTable(String spectatorId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Spectator\" where Id=?");
        statement.setString(1, spectatorId);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String id = result.getString("id");
            String firstName = result.getString("firstname");
            String lastName = result.getString("lastname");
            Spectator spectator = new Spectator(id, firstName, lastName);
            String ticketid = result.getString("ticketid");
            if (ticketid == null) {
                return spectator;
            }
            PreparedStatement ticketStatement = connection.prepareStatement(" select * from \"Ticket\" where Id=?");
            ticketStatement.setString(1, ticketid);
            ResultSet resultTicket = ticketStatement.executeQuery();
            Screening screening;
            if (resultTicket.next()) {
                String screeningId = resultTicket.getString("screeningid");
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
                        Movie movie = new Movie(movieId, title, duration, new Vector<String>(), new Vector<String>(), new Vector<String>());
                        Room room = RoomBuilder.buildRoom(roomId, resultRoom.getInt("roomnumber"), resultRoom.getInt("numberofseats"));
                        if (Objects.equals(format, "2D")) {
                            screening = new Screening2D(screeningId, movie, room, startTime);
                        } else if (Objects.equals(format, "3D")) {
                            screening = new Screening3D(screeningId, movie, room, startTime);
                        } else {
                            screening = new Screening4DX(screeningId, movie, room, startTime);
                        }
                        float price = resultTicket.getFloat("price");
                        int seatnr = resultTicket.getInt("seatnumber");
                        Ticket ticket = new Ticket(ticketid, screening, price, seatnr);
                        spectator.setTicket(ticket);
                        return spectator;
                    }
                }
            }
        }
        return null;//spectator was not created
    }

    @Transactional
    public void deleteAllFromSpectatorTable() throws SQLException {
        select.execute("delete from \"Spectator\"");
    }

    @Transactional
    public void deleteSpectatorWithIdFromTable(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Spectator\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void addSpectatorsToTable() throws SQLException {
        for (Spectator spectator : spectators) {
            addSpectatorToTable(spectator);
        }
    }

    @Transactional
    public void addSpectatorToTable(Spectator spectator) throws SQLException {
        insertFancy.setString(1, spectator.getId());
        insertFancy.setString(2, spectator.getFirstName());
        insertFancy.setString(3, spectator.getLastName());
        if (spectator.getTicket() != null) {
            insertFancy.setString(4, spectator.getTicket().getId());
        } else {
            insertFancy.setString(4, null);
        }
        insertFancy.executeUpdate();
    }

    @Transactional
    public void delete(Spectator spectator) {
        spectators.remove(spectator);
    }

    @Transactional
    public void deleteAll() {
        spectators.clear();
    }

    @Transactional
    public void printAll() {
        System.out.println(spectators);
    }

    @Transactional
    public Vector<Spectator> getAll() {
        return this.spectators;
    }

}

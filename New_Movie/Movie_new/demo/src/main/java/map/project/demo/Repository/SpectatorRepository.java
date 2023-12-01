package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class SpectatorRepository {
    private static SpectatorRepository instance;
    private final Vector<Spectator> spectators;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Spectator\"(id,firstname, lastname, ticketid) VALUES (?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyTicket = "INSERT INTO \"Ticket\"(id, screeningid, price,seatnumber,\"spectatorId\") VALUES (?, ?, ?, ?, ?)on conflict (id) do nothing";
    PreparedStatement insertFancyTicket = connection.prepareStatement(insertStringFancyTicket);
    Statement select = connection.createStatement();

    public SpectatorRepository() throws SQLException {
        spectators = getSpectatorsFromTable();
    }

    public static SpectatorRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new SpectatorRepository();
        }
        return instance;
    }

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

    public void deleteAllFromSpectatorTable() throws SQLException {
        select.execute("delete from \"Spectator\"");
    }

    public void addSpectatorsToTable() throws SQLException {
        for (Spectator spectator : spectators) {
            insertFancyTicket.setString(1, spectator.getTicket().getId());
            insertFancyTicket.setString(2, spectator.getTicket().getScreening().getId());
            insertFancyTicket.setDouble(3, spectator.getTicket().getPrice());
            insertFancyTicket.setInt(4, spectator.getTicket().getSeatNumber());
            insertFancyTicket.setString(5, spectator.getId());
            insertFancyTicket.executeUpdate();
            insertFancy.setString(1, spectator.getId());
            insertFancy.setString(2, spectator.getFirstName());
            insertFancy.setString(3, spectator.getLastName());
            if (spectator.getTicket() != null) {
                insertFancy.setString(4, spectator.getTicket().getId());
            } else {
                insertFancy.setString(4, "");
            }
            insertFancy.executeUpdate();
        }
    }

    public void add(Spectator spectator) {
        spectators.add(spectator);
    }

    public void delete(Spectator spectator) {
        spectators.remove(spectator);
    }

    public void deleteAll() {
        spectators.clear();
    }

    public void printAll() {
        System.out.println(spectators);
    }

    public void updateFirstName(Spectator spectator, String firstName) {
        spectators.get(getAll().indexOf(spectator)).setFirstName(firstName);
    }

    public void updateLastName(Spectator spectator, String lastName) {
        spectators.get(getAll().indexOf(spectator)).setLastName(lastName);
    }

    public Vector<Spectator> getAll() {
        return this.spectators;
    }
}

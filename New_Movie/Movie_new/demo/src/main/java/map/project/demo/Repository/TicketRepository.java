package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.*;
import map.project.demo.Strategy.Screening;

import java.sql.*;
import java.util.Objects;
import java.util.Vector;

public class TicketRepository {
    private static TicketRepository instance;
    private final Vector<Ticket> tickets;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Ticket\"(id, screeningid, price,seatnumber,\"SpectatorId\") VALUES (?, ?, ?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);

    Statement select = connection.createStatement();

    public TicketRepository() throws SQLException {
        tickets = getTicketsFromTable();
    }

    public static TicketRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new TicketRepository();
        }
        return instance;
    }

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
                    Movie movie = new Movie(movieId, title, duration, new Vector<String>(), new Vector<String>(), new Vector<String>());
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

    public void deleteAllFromTicketTable() throws SQLException {
        select.execute("delete from \"Ticket\"");
    }

    public void addTicketsToTable() throws SQLException {
        for (Ticket ticket : tickets) {
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
    }

    public void add(Ticket ticket) {
        tickets.add(ticket);
    }

    public void delete(Ticket ticket) {
        tickets.remove(ticket);
    }

    public void deleteAll() {
        tickets.clear();
    }

    public void printAll() {
        System.out.println(tickets);
    }

    public void updatePrice(Ticket ticket, float price) {
        tickets.get(getAll().indexOf(ticket)).setPrice(price);
    }

    public void updateSeatNumber(Ticket ticket, int seatNumber) {
        tickets.get(getAll().indexOf(ticket)).setSeatNumber(seatNumber);
    }

    public Vector<Ticket> getAll() {
        return this.tickets;
    }
}

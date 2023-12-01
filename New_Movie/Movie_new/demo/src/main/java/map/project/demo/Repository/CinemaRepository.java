package map.project.demo.Repository;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Room;

import java.sql.*;
import java.util.Vector;

public class CinemaRepository implements CinemaRepositoryComponent {
    private static CinemaRepository instance;
    private final Vector<Cinema> cinemas;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"cinema\"(id,cinemaname,cinemaaddress) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);

    Statement select = connection.createStatement();


    public CinemaRepository() throws SQLException {
        cinemas = getCinemasFromTable();
    }

    public static CinemaRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new CinemaRepository();
        }
        return instance;
    }

    public Vector<Cinema> getCinemasFromTable() throws SQLException {
        Vector<Cinema> cinemaVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"cinema\"");
        while (result.next()) {
            String id = result.getString("id");
            String cinemaName = result.getString("cinemaname");
            String address = result.getString("cinemaaddress");
            cinemaVector.add(new Cinema(id, cinemaName, address, new Vector<>()));
        }
        return cinemaVector;
    }

    public void deleteAllCinemasFromTable() throws SQLException {
        select.execute("delete from \"cinema\"");
    }

    public void addCinemasToTable() throws SQLException {
        for (Cinema cinema : cinemas) {
            insertFancy.setString(1, cinema.getId());
            insertFancy.setString(2, cinema.getName());
            insertFancy.setString(3, cinema.getAddress());
            insertFancy.executeUpdate();
        }
    }

    @Override
    public void add(Cinema cinema) {
        cinemas.add(cinema);
    }

    @Override
    public void delete(Cinema cinema) {
        cinemas.remove(cinema);
    }

    @Override
    public void deleteAll() {
        cinemas.clear();
    }

    @Override
    public void printAll() {
        System.out.println(cinemas);
    }

    @Override
    public Vector<Cinema> getAll() {
        return this.cinemas;
    }

    public void updateName(Cinema cinema, String name) {
        cinemas.get(getAll().indexOf(cinema)).setName(name);
    }

    public void updateAddress(Cinema cinema, String address) {
        cinemas.get(getAll().indexOf(cinema)).setAddress(address);
    }

    @Override
    public void addRoom(Cinema cinema, String roomId) {
        cinemas.get(getAll().indexOf(cinema)).addRoom(roomId);
    }

    @Override
    public void deleteRoom(Cinema cinema, String roomId) {
        cinemas.get(getAll().indexOf(cinema)).removeRoom(roomId);

    }
}

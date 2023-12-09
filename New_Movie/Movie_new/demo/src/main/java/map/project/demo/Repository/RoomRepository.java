package map.project.demo.Repository;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Repository;

import map.project.demo.Builder.RoomBuilder;

import map.project.demo.Domain.Room;

import java.sql.*;
import java.util.Vector;

@Repository
public class RoomRepository {
    private static RoomRepository instance;
    private final Vector<Room> rooms;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Room\"(id, roomnumber, numberofseats) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);

    Statement select = connection.createStatement();


    public RoomRepository() throws SQLException {
        rooms = getRoomsFromTable();
    }

    @Transactional
    public Vector<Room> getRoomsFromTable() throws SQLException {
        Vector<Room> roomVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Room\"");
        while (result.next()) {
            String id = result.getString("Id");
            int roomNumber = result.getInt("RoomNumber");
            int numberOfSeats = result.getInt("NumberOfSeats");
            roomVector.add(RoomBuilder.buildRoom(id, roomNumber, numberOfSeats));
        }
        return roomVector;
    }

    @Transactional
    public Room getRoomWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Room\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            int roomNumber = result.getInt("roomnumber");
            int numberOfSeats = result.getInt("numberofseats");
            return new Room(id, roomNumber, numberOfSeats);
        }
        return null;
    }

    @Transactional
    public void deleteRoomWithIdFromTable(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Room\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteAllFromRoomTable() throws SQLException {
        select.execute("delete from \"Room\"");
    }

    @Transactional
    public void addRoomsToTable() throws SQLException {
        for (Room room : rooms) {
            insertFancy.setString(1, room.getId());
            insertFancy.setInt(2, room.getRoomNumber());
            insertFancy.setInt(3, room.getNumberOfSeats());
            insertFancy.executeUpdate();
        }
    }

    @Transactional
    public void addRoomToTable(Room room) throws SQLException {
            insertFancy.setString(1, room.getId());
            insertFancy.setInt(2, room.getRoomNumber());
            insertFancy.setInt(3, room.getNumberOfSeats());
            insertFancy.executeUpdate();
    }

    @Transactional
    public void add(Room room) {
        rooms.add(room);
    }
    @Transactional
    public void delete(Room room) {
        rooms.remove(room);
    }
    @Transactional
    public void deleteAll() {
        rooms.clear();
    }

    @Transactional
    public void updateRoomNumber(Room room, int roomNumber) {
        rooms.get(getAll().indexOf(room)).setRoomNumber(roomNumber);
    }

    @Transactional
    public void updateNumberOfSeats(Room room, int numberOfSeats) {
        rooms.get(getAll().indexOf(room)).setNumberOfSeats(numberOfSeats);
    }

    @Transactional
    public Vector<Room> getAll() {
        return this.rooms;
    }
}

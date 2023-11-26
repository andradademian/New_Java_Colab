package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Room;

import java.sql.*;
import java.util.Vector;

public class RoomRepository {
    private static RoomRepository instance;
    private final Vector<Room> rooms;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "INSERT INTO \"Room\"(id, roomnumber, numberofseats) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);

    Statement select = connection.createStatement();


    public RoomRepository() throws SQLException {
        rooms = getRoomsFromTable();
    }

    public static RoomRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

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

    public void deleteAllRoomsFromTable() throws SQLException {
        select.execute("delete from \"Room\"");
    }

    public void addRoomsToTable() throws SQLException {
        for (Room room : rooms) {
            insertFancy.setString(1, room.getId());
            insertFancy.setInt(2, room.getRoomNumber());
            insertFancy.setInt(3, room.getNumberOfSeats());
            insertFancy.executeUpdate();
        }
    }

    public void add(Room room) {
        rooms.add(room);
    }

    public void delete(Room room) {
        rooms.remove(room);
    }

    public void deleteAll() {
        rooms.clear();
    }

    public void printAll() {
        System.out.println(rooms);
    }

    public void updateRoomNumber(Room room, int roomNumber) {
        rooms.get(getAll().indexOf(room)).setRoomNumber(roomNumber);
    }

    public void updateNumberOfSeats(Room room, int numberOfSeats) {
        rooms.get(getAll().indexOf(room)).setNumberOfSeats(numberOfSeats);
    }

    public Vector<Room> getAll() {
        return this.rooms;
    }
}

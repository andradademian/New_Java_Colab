//package map.project.demo.Repository;
//
//import map.project.demo.Domain.Cinema;
//
//import jakarta.transaction.Transactional;
//import map.project.demo.Domain.Actor;
//import org.springframework.stereotype.Repository;
//
//import java.sql.*;
//import java.util.Vector;
//
//@Repository
//public class CinemaRepository {
//    private static CinemaRepository instance;
//    private final Vector<Cinema> cinemas;
//
//    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
//    Statement insert = connection.createStatement();
//    String insertStringFancy = "INSERT INTO \"Cinema\"(id,cinemaname,cinemaaddress) VALUES (?, ?, ?) on conflict (id) do nothing";
//    String insertFancyIntoCinemaRoom = "INSERT INTO \"CinemaRoom\"(cinemaid, roomid) VALUES (?, ?) ON CONFLICT (cinemaid, roomid) DO NOTHING";
//
//    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
//    PreparedStatement insertFancyIntoActorMovie = connection.prepareStatement(insertFancyIntoCinemaRoom);
//
//
//    Statement select = connection.createStatement();
//
//
//    public CinemaRepository() throws SQLException {
//        cinemas = getCinemasFromTable();
//    }
//
//    @Transactional
//    public Vector<Cinema> getCinemasFromTable() throws SQLException {
//        Vector<Cinema> cinemaVector = new Vector<>();
//        ResultSet result = select.executeQuery(" SELECT * FROM \"Cinema\"");
//        while (result.next()) {
//            String id = result.getString("id");
//            String cinemaName = result.getString("cinemaname");
//            String address = result.getString("cinemaaddress");
//            cinemaVector.add(new Cinema(id, cinemaName, address, new Vector<>()));
//        }
//        return cinemaVector;
//    }
//
//    @Transactional
//    public void deleteAllCinemasFromTable() throws SQLException {
//        select.execute("delete from \"Cinema\"");
//    }
//
//    @Transactional
//    public void deleteAllFromCinemaRoomTable() throws SQLException {
//        select.execute("delete from \"CinemaRoom\"");
//    }
//
//    @Transactional
//    public void addCinemasToTable() throws SQLException {
//        for (Cinema cinema : cinemas) {
//            insertFancy.setString(1, cinema.getId());
//            insertFancy.setString(2, cinema.getName());
//            insertFancy.setString(3, cinema.getAddress());
//            insertFancy.executeUpdate();
//        }
//    }
//
//    @Transactional
//    public void addCinemaToTable(Cinema cinema) throws SQLException {
//        insertFancy.setString(1, cinema.getId());
//        insertFancy.setString(2, cinema.getName());
//        insertFancy.setString(3, cinema.getAddress());
//        insertFancy.executeUpdate();
//    }
//
//    @Transactional
//    public Vector<String> getRoomsFromCinemaRoomTable(String actorId) throws SQLException {
//        Vector<String> roomsIds = new Vector<>();
//        PreparedStatement roomStatement = connection.prepareStatement(" SELECT CR.roomid FROM \"CinemaRoom\" CR where CR.cinemaid=?");
//        roomStatement.setString(1, actorId);
//        ResultSet result = roomStatement.executeQuery();
//        while (result.next()) {
//            roomsIds.add(result.getString("roomid"));
//        }
//        return roomsIds;
//    }
//
//    @Transactional
//    public Cinema getCinemaWithIdFromTable(String id) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Cinema\" where Id=?");
//        statement.setString(1, id);
//        ResultSet result = statement.executeQuery();
//        if (result.next()) {
//            String name = result.getString("cinemaname");
//            String address = result.getString("cinemaaddress");
//            return new Cinema(id, name, address, getRoomsFromCinemaRoomTable(id));
//        }
//        return null;
//    }
//
//    @Transactional
//    public void deleteCinemaWithIdFromTable(String id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Cinema\" where id=?");
//        preparedStatement.setString(1, id);
//        preparedStatement.executeUpdate();
//    }
//
//    @Transactional
//    public void deleteAllFromCinemaTable() throws SQLException {
//        select.execute("delete from \"Cinema\"");
//    }
//
//    public Vector<Cinema> getAll() {
//        return this.cinemas;
//    }
//
//
//    @Transactional
//    public void add(Cinema cinema) {
//        cinemas.add(cinema);
//    }
//
//    @Transactional
//    public void delete(Cinema cinema) {
//        cinemas.remove(cinema);
//    }
//
//    @Transactional
//    public void deleteAll() {
//        cinemas.clear();
//    }
//
//    @Transactional
//    public void printAll() {
//        System.out.println(cinemas);
//    }
//
//
//    public void updateName(Cinema cinema, String name) {
//        cinemas.get(getAll().indexOf(cinema)).setName(name);
//    }
//
//    public void updateAddress(Cinema cinema, String address) {
//        cinemas.get(getAll().indexOf(cinema)).setAddress(address);
//    }
//
//    @Transactional
//    public void addRoom(String cinemaId, String roomId) throws SQLException {
//        insertFancyIntoActorMovie.setString(1, cinemaId);
//        insertFancyIntoActorMovie.setString(2, roomId);
//        insertFancyIntoActorMovie.executeUpdate();
//
//    }
//
//    @Transactional
//    public void deleteRoom(String cinemaId, String roomId) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement(" delete FROM \"CinemaRoom\" CR where CR.cinemaid=? and CR.roomid=?");
//        statement.setString(1, cinemaId);
//        statement.setString(2, roomId);
//        statement.execute();
//    }
//}

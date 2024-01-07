//package map.project.demo.Repository;
//
//import jakarta.transaction.Transactional;
//import map.project.demo.AwardFactory.AwardFactory;
//import map.project.demo.Domain.Actor;
//import org.springframework.stereotype.Repository;
//
//import map.project.demo.AwardFactory.GoldenGlobe;
//import map.project.demo.AwardFactory.Oscar;
//import map.project.demo.AwardFactory.PalmeDor;
//import map.project.demo.Builder.RoomBuilder;
//import map.project.demo.Domain.Actor;
//import map.project.demo.Domain.Award;
//import map.project.demo.Domain.Room;
//
//import java.sql.*;
//import java.util.Objects;
//import java.util.Vector;
//
//@Repository
//public class AwardRepository {
//    private static AwardRepository instance;
//    private final Vector<Award> awards;
//
//    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
//    Statement insert = connection.createStatement();
//    String insertStringFancy = "INSERT INTO \"Award\"(id, awardType, category) VALUES (?, ?, ?) on conflict (id) do nothing";
//    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
//
//    Statement select = connection.createStatement();
//
//
//    public AwardRepository() throws SQLException {
//        awards = getAwardsFromTable();
//    }
//
//    @Transactional
//    public Vector<Award> getAwardsFromTable() throws SQLException {
//        Vector<Award> awardVector = new Vector<>();
//        ResultSet result = select.executeQuery(" SELECT * FROM \"Award\"");
//        while (result.next()) {
//            String id = result.getString("Id");
//            String awardType = result.getString("AwardType");
//            String category = result.getString("Category");
//            if (Objects.equals(awardType, "Oscar")) {
//                awardVector.add(new Oscar(id, category));
//            } else if (Objects.equals(awardType, "PalmeDor")) {
//                awardVector.add(new PalmeDor(id, category));
//            } else if (Objects.equals(awardType, "GoldenGlobe")) {
//                awardVector.add(new GoldenGlobe(id, category));
//            }
//
//        }
//        return awardVector;
//    }
//
//    @Transactional
//    public void deleteAllFromAwards() throws SQLException {
//        select.execute("delete from \"Award\"");
//    }
//
//    @Transactional
//    public void addAwardsToTable() throws SQLException {
//        for (Award award : awards) {
//            insertFancy.setString(1, award.getId());
//            insertFancy.setString(2, award.getType());
//            insertFancy.setString(3, award.getCategory());
//            insertFancy.executeUpdate();
//        }
//    }
//
//    @Transactional
//    public void addAwardToTable(Award award) throws SQLException {
//        insertFancy.setString(1, award.getId());
//        insertFancy.setString(2, award.getType());
//        insertFancy.setString(3, award.getCategory());
//        insertFancy.executeUpdate();
//    }
//
//    @Transactional
//    public Award getAwardWithIdFromTable(String id) throws SQLException {
//        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Award\" where Id=?");
//        statement.setString(1, id);
//        ResultSet result = statement.executeQuery();
//        if (result.next()) {
//            String type = result.getString("AwardType");
//            String category = result.getString("Category");
//            if (Objects.equals(type, "Oscar")) {
//                return new Oscar(id, category);
//            } else if (Objects.equals(type, "PalmeDor")) {
//                return new PalmeDor(id, category);
//            } else if (Objects.equals(type, "GoldenGlobe")) {
//                return new GoldenGlobe(id, category);
//            } else
//                return new Award(id, category);
//
//        }
//        return null;
//    }
//
//    @Transactional
//    public void deleteAwardWithIdFromTable(String id) throws SQLException {
//        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Award\" where id=?");
//        preparedStatement.setString(1, id);
//        preparedStatement.executeUpdate();
//    }
//
//
//    @Transactional
//    public void add(Award award) {
//        awards.add(award);
//    }
//
//    @Transactional
//    public void delete(Award award) {
//        awards.remove(award);
//    }
//
//    @Transactional
//    public void deleteAll() {
//        awards.clear();
//    }
//
//    @Transactional
//    public void printAll() {
//        System.out.println(awards);
//    }
//
//    @Transactional
//    public void updateCategory(Award award, String category) {
//        awards.get(getAll().indexOf(award)).setCategory(category);
//    }
//
//    @Transactional
//    public Vector<Award> getAll() {
//        return this.awards;
//    }
//}

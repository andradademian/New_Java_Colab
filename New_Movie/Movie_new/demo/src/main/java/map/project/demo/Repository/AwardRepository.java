package map.project.demo.Repository;

import map.project.demo.Builder.RoomBuilder;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Domain.Room;

import java.sql.*;
import java.util.Vector;

public class AwardRepository {
    private static AwardRepository instance;
    private final Vector<Award> awards;

    Connection connection= DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie","MyUser","slay");
    Statement insert=connection.createStatement();
    String insertStringFancy="INSERT INTO \"Award\"(id, awardType, category) VALUES (?, ?, ?)";
    PreparedStatement insertFancy=connection.prepareStatement(insertStringFancy);

    Statement select=connection.createStatement();


    public AwardRepository() throws SQLException {
        awards = new Vector<>();
    }

    public static AwardRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new AwardRepository();
        }
        return instance;
    }

    public Vector<Award> getAwardsFromTable() throws SQLException {
        Vector<Award> awardVector = new Vector<>();
        ResultSet result=select.executeQuery(" SELECT * FROM \"Award\"");
        while (result.next()){
            String id=result.getString("Id");
            String awardType = result.getString("AwardType");
            String category = result.getString("Category");
            awardVector.add(new Award(id, category));
        }
        select.execute("delete from \"Room\"");
        return awardVector;
    }

    public void addAwardsToTable() throws SQLException {
        for(Award award:awards){
            insertFancy.setString(1,award.getId());
            insertFancy.setString(2,award.getType());
            insertFancy.setString(3,award.getCategory());
            insertFancy.executeUpdate();
        }
    }


    public void add(Award award) {
        awards.add(award);
    }

    public void delete(Award award) {
        awards.remove(award);
    }

    public void deleteAll() {
        awards.clear();
    }

    public void printAll() {
        System.out.println(awards);
    }
    public void updateCategory(Award award, String category) {
        awards.get(getAll().indexOf(award)).setCategory(category);
    }

    public Vector<Award> getAll() {
        return this.awards;
    }
}

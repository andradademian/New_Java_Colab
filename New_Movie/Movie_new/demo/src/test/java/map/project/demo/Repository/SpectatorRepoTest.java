//package map.project.demo.Repository;
//
//import map.project.demo.Domain.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//public class SpectatorRepoTest {
//    SpectatorRepository spectatorRepository;
//
//    @BeforeEach
//    public void setUp() throws SQLException {
//        spectatorRepository = new SpectatorRepository();
//    }
//
//    @Test
//    public void expectSpectatorAddedSuccessfully() throws SQLException {
//        int size = spectatorRepository.getSpectatorsFromTable().size();
//        spectatorIsAddedToTheTable();
//        assertEquals(spectatorRepository.getSpectatorsFromTable().size(), size + 1);
//        spectatorIsRemovedFromTable();
//    }
//
//    @Test
//    public void expectSpectatorNotAddedSuccessfully() throws SQLException {
//        int size = spectatorRepository.getSpectatorsFromTable().size();
//        spectatorIsAddedToTheTable();
//        assertNotEquals(spectatorRepository.getSpectatorsFromTable().size(), size);
//        spectatorIsRemovedFromTable();
//    }
//
//    @Test
//    public void expectSpectatorRemovedSuccessfully() throws SQLException {
//        int size = spectatorRepository.getSpectatorsFromTable().size();
//        spectatorIsAddedToTheTable();
//        assertEquals(spectatorRepository.getSpectatorsFromTable().size(), size + 1);
//        spectatorIsRemovedFromTable();
//        assertEquals(spectatorRepository.getSpectatorsFromTable().size(), size);
//    }
//
//    @Test
//    public void expectSpectatorNotRemovedSuccessfully() throws SQLException {
//        int size = spectatorRepository.getSpectatorsFromTable().size();
//        spectatorIsAddedToTheTable();
//        assertEquals(spectatorRepository.getSpectatorsFromTable().size(), size + 1);
//        spectatorIsRemovedFromTable();
//        assertNotEquals(spectatorRepository.getSpectatorsFromTable().size(), size + 1);
//    }
//
//    public void spectatorIsAddedToTheTable() throws SQLException {
//        Spectator spectator = new Spectator("KX", "FirstName", "LastName");
//        spectatorRepository.addSpectatorToTable(spectator);
//    }
//
//    public void spectatorIsRemovedFromTable() throws SQLException {
//        spectatorRepository.deleteSpectatorWithIdFromTable("KX");
//    }
//}

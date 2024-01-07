package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.SpectatorController;
import map.project.demo.Domain.Spectator;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.ISpectatorRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SpectatorController.class)
public class SpectatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpectatorController spectatorController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");

        mockMvc.perform(post("/api/spectator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spectator)))
                .andExpect(status().isOk());

        Spectator spectator1 = spectatorController.findSpectatorById("KX");
        assertNotSame(spectator1, null);
        spectatorController.deleteSpectator("KX");

    }

//    @Test
//    void testFindSpectatorById() throws Exception {
//        Spectator spectator = new Spectator();
//        spectator.setFirstName("John");
//        spectator.setLastName("Doe");
//
//        when(spectatorRepository.getSpectatorWithIdFromTable("1")).thenReturn(spectator);
//
//        mockMvc.perform(get("/api/spectator/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value("John"))
//                .andExpect(jsonPath("$.lastName").value("Doe"));
//    }
//
//    @Test
//    void testDeleteSpectator() throws Exception {
//        doNothing().when(spectatorRepository).deleteSpectatorWithIdFromTable(anyString());
//
//        mockMvc.perform(delete("/api/spectator/{id}", "1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteAllSpectators() throws Exception {
//        doNothing().when(spectatorRepository).deleteAllFromSpectatorTable();
//
//        mockMvc.perform(delete("/api/spectator"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdateSpectator() throws Exception {
//        Spectator spectator = new Spectator();
//        spectator.setFirstName("UpdatedFirstName");
//        spectator.setLastName("UpdatedLastName");
//
//        doNothing().when(spectatorRepository).deleteSpectatorWithIdFromTable(anyString());
//        when(spectatorRepository.getSpectatorWithIdFromTable("1")).thenReturn(spectator);
//
//        mockMvc.perform(put("/api/spectator/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(spectator)))
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    void testSetTicket() throws Exception {
//        Ticket ticket = new Ticket();
//        ticket.setId("1");
//        ticket.setPrice(20);
//
//        Spectator spectator = new Spectator("1", "FirstName", "LastName");
//
//        doNothing().when(spectatorRepository).deleteSpectatorWithIdFromTable(anyString());
//        when(spectatorRepository.getSpectatorWithIdFromTable("1")).thenReturn(spectator);
//
//        mockMvc.perform(put("/api/spectator/1/setTicket")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(ticket)))
//                .andExpect(status().isOk());
//    }
//
//
//    @Test
//    void testGetAllSpectators() throws Exception {
//        Spectator spectator1 = new Spectator();
//        spectator1.setFirstName("John");
//        spectator1.setLastName("Doe");
//
//        Spectator spectator2 = new Spectator();
//        spectator2.setFirstName("Jane");
//        spectator2.setLastName("Smith");
//
//        Vector<Spectator> spectators = new Vector<>(Arrays.asList(spectator1, spectator2));
//
//        when(spectatorRepository.getSpectatorsFromTable()).thenReturn(spectators);
//
//        mockMvc.perform(get("/api/spectator"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2))
//                .andExpect(jsonPath("$[0].firstName").value("John"))
//                .andExpect(jsonPath("$[1].firstName").value("Jane"));
//    }
}

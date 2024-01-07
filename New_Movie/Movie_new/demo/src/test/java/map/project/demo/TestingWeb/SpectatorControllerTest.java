package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.SpectatorController;
import map.project.demo.Domain.Spectator;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.ISpectatorRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SpectatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SpectatorController spectatorController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void expectCorrectAddingOfSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");

        mockMvc.perform(post("/api/spectator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spectator)))
                .andExpect(status().isOk());

        Spectator spectator1 = spectatorController.findSpectatorById("KX");
        assertNotSame(spectator1, null);
        assertEquals(spectator1.getId(), "KX");
        spectatorController.deleteSpectator("KX");

    }

    @Test
    void expectIncorrectAddingOfSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");

        mockMvc.perform(post("/api/spectator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spectator)))
                .andExpect(status().isOk());

        Spectator spectator1 = spectatorController.findSpectatorById("KX");
        assertNotSame(spectator1, null);
        assertNotEquals(spectator1.getId(), "XK");
        spectatorController.deleteSpectator("KX");

    }

    @Test
    void expectCorrectFindingOfSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");
        spectatorController.addSpectator(spectator);
        mockMvc.perform(get("/api/spectator/KX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));

        spectatorController.deleteSpectator("KX");
    }

    @Test
    void testDeleteSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");
        spectatorController.addSpectator(spectator);
        mockMvc.perform(delete("/api/spectator/{id}", "KX"))
                .andExpect(status().isOk());
        assertFalse(spectatorController.isPresentWithId("KX"));
    }

    @Test
    void testUpdateSpectator() throws Exception {
        Spectator spectator = new Spectator("KX", "John", "Doe");

        mockMvc.perform(post("/api/spectator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(spectator)))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/spectator/updateFirstName/KX")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString("Lulu")))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/spectator/KX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("\"Lulu\""));

        spectatorController.deleteSpectator("KX");
    }


}

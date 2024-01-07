package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.TicketController;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.ITicketRepo;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TicketController ticketController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(25.0f);
        ticket.setSeatNumber(1);
        ticket.setId("KX");

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());

        ticketController.deleteTicket("KX");
    }

    @Test
    void testGetTicketWithId() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(30);
        ticket.setSeatNumber(2);
        ticket.setId("KX");

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/ticket/KX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30))
                .andExpect(jsonPath("$.seatNumber").value(2));

        ticketController.deleteTicket("KX");
    }

    @Test
    void testDeleteTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(30);
        ticket.setSeatNumber(2);
        ticket.setId("KX");

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/ticket/{id}", "KX"))
                .andExpect(status().isOk());

        assertFalse(ticketController.existsWithId("KX"));
    }

    @Test
    void testUpdateTicketPrice() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(30);
        ticket.setSeatNumber(2);
        ticket.setId("KX");

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/ticket/KX/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("35"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/ticket/KX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(35));

        ticketController.deleteTicket("KX");
    }

    @Test
    void testUpdateTicketSeatNumber() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(30);
        ticket.setSeatNumber(2);
        ticket.setId("KX");

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());

        mockMvc.perform(put("/api/ticket/KX/seatNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("35"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/ticket/KX"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seatNumber").value(35));

        ticketController.deleteTicket("KX");
    }
}

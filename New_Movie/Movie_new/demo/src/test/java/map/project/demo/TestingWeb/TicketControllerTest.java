package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.TicketController;
import map.project.demo.Domain.Ticket;
import map.project.demo.Repository.TicketRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TicketController.class)
public class TicketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketController ticketController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddTicket() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(25.0f);
        ticket.setSeatNumber(1);

        doNothing().when(ticketRepository).addTicketToTable(any(Ticket.class));

        mockMvc.perform(post("/api/ticket")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ticket)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetTicketWithId() throws Exception {
        Ticket ticket = new Ticket();
        ticket.setPrice(30);
        ticket.setSeatNumber(2);

        when(ticketRepository.getTicketWithIdFromTable("1")).thenReturn(ticket);

        mockMvc.perform(get("/api/ticket/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(30))
                .andExpect(jsonPath("$.seatNumber").value(2));
    }

    @Test
    void testDeleteTicket() throws Exception {
        doNothing().when(ticketRepository).deleteTicketWithId(anyString());

        mockMvc.perform(delete("/api/ticket/{id}", "1"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAllTickets() throws Exception {
        doNothing().when(ticketRepository).deleteAllFromTicketTable();

        mockMvc.perform(delete("/api/ticket"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTicketPrice() throws Exception {
        doNothing().when(ticketRepository).updatePrice(anyString(), anyFloat());

        mockMvc.perform(put("/api/ticket/1/price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("35"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateTicketSeatNumber() throws Exception {
        doNothing().when(ticketRepository).updateSeatNumber(anyString(), anyInt());

        mockMvc.perform(put("/api/ticket/1/seatNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("3"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllTickets() throws Exception {
        Ticket ticket1 = new Ticket();
        ticket1.setPrice(25);
        ticket1.setSeatNumber(1);

        Ticket ticket2 = new Ticket();
        ticket2.setPrice(30);
        ticket2.setSeatNumber(2);

        Vector<Ticket> tickets = new Vector<>(Arrays.asList(ticket1, ticket2));

        when(ticketRepository.getTicketsFromTable()).thenReturn(tickets);

        mockMvc.perform(get("/api/ticket"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].price").value(25))
                .andExpect(jsonPath("$[1].price").value(30));
    }
}

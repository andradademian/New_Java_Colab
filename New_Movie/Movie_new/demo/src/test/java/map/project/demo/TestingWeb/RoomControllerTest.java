package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.RoomController;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.IRoomRepo;
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
import java.util.Optional;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IRoomRepo roomRepository;


    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddRoom() throws Exception {
        Room room = new Room();
        room.setId("10");
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk());

        Optional<Room> savedRoom = roomRepository.findById("10");
        assertTrue(savedRoom.isPresent());

        Room retrievedRoom = savedRoom.get();
        assertEquals("10", retrievedRoom.getId());

        roomRepository.deleteById("10");
    }

    @Test
    void testFindRoomById() throws Exception {
        Room room = new Room();
        room.setId("10");
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk());

        mockMvc.perform(get("/api/room/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber").value(101))
                .andExpect(jsonPath("$.numberOfSeats").value(50));

        roomRepository.deleteById("10");

    }

    @Test
    void testDeleteRoomWithId() throws Exception {
        Room room = new Room();
        room.setId("10");

        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk());

        mockMvc.perform(delete("/api/room/{id}", "10"))
                .andExpect(status().isOk());

        assertFalse(roomRepository.existsById("10"));
        roomRepository.deleteById("10");
    }


}

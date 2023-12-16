package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.RoomController;
import map.project.demo.Domain.Room;
import map.project.demo.Repository.RoomRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomController roomController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddRoom() throws Exception {
        Room room = new Room();
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        doNothing().when(roomRepository).addRoomToTable(any(Room.class));

        mockMvc.perform(post("/api/room")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(room)))
                .andExpect(status().isOk());
    }

    @Test
    void testFindRoomById() throws Exception {
        Room room = new Room();
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        when(roomRepository.getRoomWithIdFromTable("1")).thenReturn(room);

        mockMvc.perform(get("/api/room/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomNumber").value(101))
                .andExpect(jsonPath("$.numberOfSeats").value(50));
    }

    @Test
    void testDeleteRoomWithIdFromTable() throws Exception {
        doNothing().when(roomRepository).deleteRoomWithIdFromTable(anyString());

        mockMvc.perform(delete("/api/room/{id}", "1")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllRooms() throws Exception {
        doNothing().when(roomRepository).deleteAllFromRoomTable();
        mockMvc.perform(delete("/api/room")).andExpect(status().isOk());
    }

    @Test
    void testUpdateRoomNumber() throws Exception {
        Room room = new Room();
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        when(roomRepository.getRoomWithIdFromTable("1")).thenReturn(room);
        doNothing().when(roomRepository).addRoomToTable(any(Room.class));

        mockMvc.perform(put("/api/room/1/updateRoomNumber")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("102"))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateNumberOfSeats() throws Exception {
        Room room = new Room();
        room.setRoomNumber(101);
        room.setNumberOfSeats(50);

        when(roomRepository.getRoomWithIdFromTable("1")).thenReturn(room);
        doNothing().when(roomRepository).addRoomToTable(any(Room.class));

        mockMvc.perform(put("/api/room/1/updateNumberOfSeats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("60"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllRooms() throws Exception {
        Room room1 = new Room();
        room1.setRoomNumber(101);
        room1.setNumberOfSeats(50);

        Room room2 = new Room();
        room2.setRoomNumber(102);
        room2.setNumberOfSeats(60);

        Vector<Room> rooms = new Vector<>(Arrays.asList(room1, room2));

        when(roomRepository.getRoomsFromTable()).thenReturn(rooms);

        mockMvc.perform(get("/api/room"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].roomNumber").value(101))
                .andExpect(jsonPath("$[1].roomNumber").value(102));
    }
}

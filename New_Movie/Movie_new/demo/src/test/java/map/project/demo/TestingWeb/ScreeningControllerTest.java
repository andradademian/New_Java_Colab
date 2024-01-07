//package map.project.demo.TestingWeb;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import map.project.demo.Controller.ScreeningController;
//import map.project.demo.Domain.*;
//import map.project.demo.Repository.IScreeningRepo;
//import map.project.demo.Strategy.Screening;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.sql.SQLException;
//import java.sql.Time;
//import java.util.Arrays;
//import java.util.Vector;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest(ScreeningController.class)
//public class ScreeningControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IScreeningRepo screeningRepository;
//
//    @InjectMocks
//    private ScreeningController screeningController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testAddScreening4DX() throws Exception {
//        Screening4DX screening4DX = new Screening4DX();
//        Room room1 = new Room("1", 5, 50);
//        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());
//
//        screening4DX.setRoom(room1);
//        screening4DX.setStartTime(Time.valueOf("12:00:00"));
//        screening4DX.setMovie(movie1);
//
//        doNothing().when(screeningRepository).addScreeningToTable(any(Screening4DX.class));
//
//        mockMvc.perform(post("/api/screening/4DX")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(screening4DX)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testAddScreening3D() throws Exception {
//        Screening3D screening3D = new Screening3D();
//        Room room1 = new Room("1", 5, 50);
//        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());
//
//        screening3D.setRoom(room1);
//        screening3D.setStartTime(Time.valueOf("14:00:00"));
//        screening3D.setMovie(movie1);
//
//        doNothing().when(screeningRepository).addScreeningToTable(any(Screening3D.class));
//
//        mockMvc.perform(post("/api/screening/3D")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(screening3D)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testAddScreening2D() throws Exception {
//        Screening2D screening2D = new Screening2D();
//        Room room1 = new Room("1", 5, 50);
//        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());
//
//        screening2D.setRoom(room1);
//        screening2D.setStartTime(Time.valueOf("16:00:00"));
//        screening2D.setMovie(movie1);
//
//        doNothing().when(screeningRepository).addScreeningToTable(any(Screening2D.class));
//
//        mockMvc.perform(post("/api/screening/2D")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(screening2D)))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdateScreeningRoom() throws Exception {
//        doNothing().when(screeningRepository).updateRoom(anyString(), anyString());
//
//        mockMvc.perform(put("/api/screening/1/room")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"NewRoom\""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdateScreeningStartTime() throws Exception {
//        doNothing().when(screeningRepository).updateStartTime(anyString(), any(Time.class));
//
//        mockMvc.perform(put("/api/screening/1/startTime")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"17:00:00\""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testUpdateScreeningMovie() throws Exception {
//        doNothing().when(screeningRepository).updateMovie(anyString(), anyString());
//
//        mockMvc.perform(put("/api/screening/1/movie")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"NewMovie\""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testGetAllScreenings() throws Exception {
//        Screening screening1 = new Screening2D();
//        Room room1 = new Room("1", 5, 50);
//        Room room2 = new Room("2", 7, 50);
//        Movie movie1 = new Movie("1", "Title1", 120, new Vector<>(), new Vector<>(), new Vector<>());
//        Movie movie2 = new Movie("2", "Title2", 100, new Vector<>(), new Vector<>(), new Vector<>());
//
//        screening1.setRoom(room1);
//        screening1.setStartTime(Time.valueOf("12:00:00"));
//        screening1.setMovie(movie1);
//
//        Screening screening2 = new Screening3D();
//        screening2.setRoom(room2);
//        screening2.setStartTime(Time.valueOf("14:00:00"));
//        screening2.setMovie(movie2);
//
//        Vector<Screening> screenings = new Vector<>(Arrays.asList(screening1, screening2));
//
//        when(screeningRepository.getScreeningsFromTable()).thenReturn(screenings);
//
//        mockMvc.perform(get("/api/screening"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2))
//                .andExpect(jsonPath("$[0].room.roomNumber").value(5))
//                .andExpect(jsonPath("$[1].room.roomNumber").value(7));
//
//    }
//}

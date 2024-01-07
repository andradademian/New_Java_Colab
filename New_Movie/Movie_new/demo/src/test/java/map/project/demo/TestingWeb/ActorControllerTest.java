//package map.project.demo.TestingWeb;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import map.project.demo.Controller.ActorController;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotSame;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//import map.project.demo.Domain.Actor;
//import map.project.demo.Repository.ActorRepository;
//import map.project.demo.Repository.IActorRepository;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.util.Arrays;
//import java.util.Vector;
//
//@WebMvcTest(ActorController.class)
//public class ActorControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private IActorRepository actorRepository;
//
//    @Autowired
//    private ActorController actorController;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void testAddActor() throws Exception {
//        Actor actor = new Actor();
//        actor.setId("KX");
//        actor.setFirstName("John");
//        actor.setLastName("Doe");
//
////        doNothing().when(actorRepository).addActorToTable(any(Actor.class));
//
//        mockMvc.perform(post("/api/actor")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(actor)))
//                .andExpect(status().isOk());
//
//        Actor actor1 = actorRepository.findById("KX").get();
//        //assertNotSame(actor1, null);
//        actorRepository.deleteById("KX");
//    }
//
//    @Test
//    void testFindActorById() throws Exception {
//        Actor actor = new Actor();
//        actor.setFirstName("John");
//        actor.setLastName("Doe");
//
////        when(actorRepository.getActorWithIdFromTable("1")).thenReturn(actor);
//
//        mockMvc.perform(get("/api/actor/1"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.firstName").value("John"))
//                .andExpect(jsonPath("$.lastName").value("Doe"));
//    }
//
//    @Test
//    void testDeleteAllActors() throws Exception {
////        doNothing().when(actorRepository).deleteAllFromActorTable();
//
//        mockMvc.perform(delete("/api/actor"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testGetAllActors() throws Exception {
//        Actor actor1 = new Actor();
//        actor1.setFirstName("John");
//        actor1.setLastName("Doe");
//
//        Actor actor2 = new Actor();
//        actor2.setFirstName("Jane");
//        actor2.setLastName("Doe");
//
//        Vector<Actor> actors = new Vector<>(Arrays.asList(actor1, actor2));
//
////        when(actorRepository.getActorsFromTable()).thenReturn(actors);
//
//        mockMvc.perform(get("/api/actor"))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.size()").value(2))
//                .andExpect(jsonPath("$[0].firstName").value("John"))
//                .andExpect(jsonPath("$[1].lastName").value("Doe"));
//    }
//
//    @Test
//    void testAddMovie() throws Exception {
////        doNothing().when(actorRepository).addMovie(anyString(), anyString());
//
//        mockMvc.perform(post("/api/actor/{actorId}/movies", "1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"movieId123\""))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteAllFromActorMovieTable() throws Exception {
////        doNothing().when(actorRepository).deleteAllFromActorMovieTable();
//
//        mockMvc.perform(delete("/api/actor/movies"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteAllFromActorAwardTable() throws Exception {
////        doNothing().when(actorRepository).deleteAllFromActorAwardTable();
//
//        mockMvc.perform(delete("/api/actor/awards"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteActorWithIdFromTable() throws Exception {
////        doNothing().when(actorRepository).deleteActorWithIdFromTable(anyString());
//
//        mockMvc.perform(delete("/api/actor/{id}", "1"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteMovie() throws Exception {
////        doNothing().when(actorRepository).deleteMovie(anyString(), anyString());
//
//        mockMvc.perform(delete("/api/actor/{actorId}/movies/{movieId}", "1", "movieId123"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testDeleteAward() throws Exception {
////        doNothing().when(actorRepository).deleteAward(anyString(), anyString());
//
//        mockMvc.perform(delete("/api/actor/{actorId}/awards/{awardId}", "1", "awardId456"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void testAddAward() throws Exception {
////        doNothing().when(actorRepository).addAward(anyString(), anyString());
//
//        mockMvc.perform(post("/api/actor/{actorId}/awards", "1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("\"awardId456\""))
//                .andExpect(status().isOk());
//    }
//}

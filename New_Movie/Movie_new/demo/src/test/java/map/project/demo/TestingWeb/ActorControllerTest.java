package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.Controller.ActorController;
import map.project.demo.Domain.Actor;
import map.project.demo.Repository.ActorRepository;
import map.project.demo.Repository.IActorRepository;
import map.project.demo.Service.ActorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Vector;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(ActorController.class)
@ExtendWith(MockitoExtension.class)
public class ActorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IActorRepository actorRepository;
    @Mock
    private ActorService actorService;

    @InjectMocks
    private ActorController actorController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddActor() throws Exception {
        Actor actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");

        when(actorRepository.save(actor)).thenReturn(actor);

        mockMvc.perform(post("/api/actor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(actor)))
                .andExpect(status().isOk());
    }


    @Test
    void testFindActorById() throws Exception {
        Actor actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");

        when(actorRepository.findById("1")).thenReturn(java.util.Optional.of(actor));

        mockMvc.perform(get("/api/actor/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John"))
                .andExpect(jsonPath("$.lastName").value("Doe"));
    }
    @Test
    void testDeleteAllActors() throws Exception {
        doNothing().when(actorRepository).deleteAll();

        mockMvc.perform(delete("/api/actor"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllActors() throws Exception {
        Actor actor1 = new Actor();
        actor1.setFirstName("John");
        actor1.setLastName("Doe");

        Actor actor2 = new Actor();
        actor2.setFirstName("Jane");
        actor2.setLastName("Doe");

        Vector<Actor> actors = new Vector<>(Arrays.asList(actor1, actor2));

        when(actorService.getAll()).thenReturn(actors);

        mockMvc.perform(get("/api/actor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"));
    }

    @Test
    void testAddMovie() throws Exception {
        doNothing().when(actorService).addMovie(anyString(), anyString());

        mockMvc.perform(post("/api/actor/{actorId}/movies", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"movieId123\""))
                .andExpect(status().isOk());
    }

//    @Test
//    void testDeleteAllFromActorMovieTable() throws Exception {
//        doNothing().when(actorService).delete();
//
//        mockMvc.perform(delete("/api/actor/movies"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void testDeleteAllFromActorAwardTable() throws Exception {
//        doNothing().when(actorService).deleteAllFromActorAwardTable();
//
//        mockMvc.perform(delete("/api/actor/awards"))
//                .andExpect(status().isOk());
//    }

//    @Test
//    void testDeleteActorWithIdFromTable() throws Exception {
//        doNothing().when(actorRepository).deleteActorWithIdFromTable(anyString());
//
//        mockMvc.perform(delete("/api/actor/{id}", "1"))
//                .andExpect(status().isOk());
//    }

    @Test
    void testDeleteMovie() throws Exception {
        doNothing().when(actorService).deleteMovie(anyString(), anyString());

        mockMvc.perform(delete("/api/actor/{actorId}/movies/{movieId}", "1", "movieId123"))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteAward() throws Exception {
        doNothing().when(actorService).deleteAward(anyString(), anyString());

        mockMvc.perform(delete("/api/actor/{actorId}/awards/{awardId}", "1", "awardId456"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddAward() throws Exception {
        doNothing().when(actorService).addAward(anyString(), anyString());

        mockMvc.perform(post("/api/actor/{actorId}/awards", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("\"awardId456\""))
                .andExpect(status().isOk());
    }
}

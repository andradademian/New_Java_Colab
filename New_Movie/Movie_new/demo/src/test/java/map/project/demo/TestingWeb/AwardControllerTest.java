package map.project.demo.TestingWeb;

import com.fasterxml.jackson.databind.ObjectMapper;
import map.project.demo.AwardFactory.AwardFactory;
import map.project.demo.Controller.AwardController;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Award;
import map.project.demo.Repository.AwardRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AwardController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AwardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AwardRepository awardRepository;

    @Autowired
    private AwardRepository actualawardRepository;


    @InjectMocks
    private AwardController awardController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testAddAward() throws Exception {
        AwardFactory awardFactory = AwardFactory.getInstance();
        Award award = awardFactory.buildAward("Oscar", "KX", "BestActor");
//        award.setId("KX");
//        award.setCategory("Best Actor");
//        doNothing().when(awardRepository).addAwardToTable(any(Award.class));

        mockMvc.perform(post("/api/award")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(award)))
                .andExpect(status().isOk());

//        when(awardRepository.getAwardWithIdFromTable).thenReturn(awardRepository.getAwardWithIdFromTable("KX"));
//        mockMvc.perform(get("/api/award/KX"))
//                .andDo((x) -> System.out.println(x.getResponse().))
//                .andExpect(jsonPath("$.category").value("Best Actor"));
        actualawardRepository.getAwardsFromTable().forEach(System.out::println);
    }

    @Test
    void testDeleteAwardWithIdFromTable() throws Exception {
        doNothing().when(awardRepository).deleteAwardWithIdFromTable(anyString());

        mockMvc.perform(delete("/api/award/{id}", "1")).andExpect(status().isOk());
    }

    @Test
    void testDeleteAllAwards() throws Exception {
        doNothing().when(awardRepository).deleteAllFromAwards();
        mockMvc.perform(delete("/api/award")).andExpect(status().isOk());
    }

    @Test
    void testFindAwardById() throws Exception {
        Award award = new Award();
        award.setCategory("Best Actor");

        when(awardRepository.getAwardWithIdFromTable("1")).thenReturn(award);
        mockMvc.perform(get("/api/award/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.category").value("Best Actor"));
    }

    @Test
    void testGetAllAwards() throws Exception {
        Award award1 = new Award();
        award1.setCategory("Best Actor");

        Award award2 = new Award();
        award2.setCategory("Best Movie");

        Vector<Award> awards = new Vector<>(Arrays.asList(award1, award2));

        when(awardRepository.getAwardsFromTable()).thenReturn(awards);

        mockMvc.perform(get("/api/award"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].category").value("Best Actor"))
                .andExpect(jsonPath("$[1].category").value("Best Movie"));
    }


}

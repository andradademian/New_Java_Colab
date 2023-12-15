package map.project.demo.TestingWeb;

import map.project.demo.Controller.ActorController;

import static org.assertj.core.api.Assertions.*;

import map.project.demo.Main;
import map.project.demo.Repository.ActorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActorControllerTest {

    @Autowired
    private ActorController actorController;

    @Test
    void contextLoads() {
        assertThat(actorController).isNotNull();
    }
}

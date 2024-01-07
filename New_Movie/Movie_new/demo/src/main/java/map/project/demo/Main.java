package map.project.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "map.project.demo.Repository")
//@ComponentScan(basePackages = "map.project.demo.Controller")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
//		UI.mainUI();
    }

}

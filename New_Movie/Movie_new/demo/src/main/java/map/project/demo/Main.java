package map.project.demo;

import map.project.demo.Repository.RoomRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import map.project.demo.UI.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.*;
import java.text.ParseException;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class);
//		UI.mainUI();
    }

}

package map.project.demo;

import map.project.demo.Repository.RoomRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import map.project.demo.UI.*;

import java.sql.*;
import java.text.ParseException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws ParseException, SQLException {
		UI.mainUI();
	}

}

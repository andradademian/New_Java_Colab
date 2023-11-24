package map.project.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import map.project.demo.UI.*;

import java.text.ParseException;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws ParseException {
		UI.mainUI();
	}

}

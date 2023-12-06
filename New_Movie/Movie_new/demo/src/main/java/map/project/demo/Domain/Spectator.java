package map.project.demo.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import map.project.demo.ObserverPattern.Observer;

import java.sql.*;

public class Spectator implements Observer {
    private String id;
    private String firstName;
    private String lastName;
    private Ticket ticket;

    public Spectator() {

    }

    public Spectator(String id, String firstName, String lastName) throws SQLException {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public String toString() {
        return "Spectator{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public void getNotified(String news) {
        System.out.println("Spectator with id " + this.id + " was notified.");
    }
}

package map.project.demo.Domain;

import jakarta.persistence.*;
import map.project.demo.ObserverPattern.Observer;

import java.sql.*;

@Entity
@Table(name = "Spectator")
public class Spectator implements Observer {
    @Id
    private String id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Transient
    private Ticket ticket;

    @Column(name = "ticketId")
    private String ticketId;

    public Spectator() {

    }

    public Spectator(String id, String firstName, String lastName) {
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

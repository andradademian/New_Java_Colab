package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import map.project.demo.ObserverPattern.Observer;

import java.sql.*;

@Entity
@Table(name = "Spectator")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Spectator implements Observer {
    @Getter
    @Id
    private String id;

    @Getter
    @Column(name = "firstname")
    private String firstName;

    @Getter
    @Column(name = "lastname")
    private String lastName;

    @Getter
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

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

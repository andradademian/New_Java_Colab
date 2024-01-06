package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Entity
@Table(name = "Cinema")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cinema implements Observable {
    @Getter
    @Id
    private String id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @Column(name = "address")
    private String address;

    @Getter
    @Transient
    @ElementCollection
    @CollectionTable(name = "cinema_rooms", joinColumns = @JoinColumn(name = "cinema_id"))
    @Column(name = "room_id")
    private Vector<String> listOfRooms;

    @Column(name = "roomId")
    private String roomId;

    @Getter
    @Transient
    private List<Observer> observers;

    public Cinema() {

    }

    public Cinema(String id, String name, String address, Vector<String> listOfRooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.listOfRooms = listOfRooms;
        this.observers = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setListOfRooms(Vector<String> listOfRooms) {
        this.listOfRooms = listOfRooms;
    }

    public void addRoom(String roomId) {
        listOfRooms.add(roomId);
    }

    public void removeRoom(String roomId) {
        listOfRooms.remove(roomId);
    }


    @Override
    public String toString() {
        return "Cinema{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", listOfRooms=" + listOfRooms +
                '}';
    }

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(String news) {
        for (Observer observer : observers) {
            observer.getNotified(news);
        }
    }
}

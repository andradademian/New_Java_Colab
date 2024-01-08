package map.project.demo.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "Cinema")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Cinema implements Observable {
    @Id
    private String id;

    @Setter
    @Column(name = "name")
    private String name;

    @Setter
    @Column(name = "address")
    private String address;

    @Transient
    @ElementCollection
    @CollectionTable(name = "CinemaRoom", joinColumns = @JoinColumn(name = "cinemaid"))
    @Column(name = "roomid")
    private List<String> listOfRooms;

//    @Column(name = "roomid")
//    private String roomId;

    @Transient
    private List<Observer> observers;

    public Cinema() {

    }

    public Cinema(String id, String name, String address, List<String> listOfRooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.listOfRooms = listOfRooms;
        this.observers = new ArrayList<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setListOfRooms(List<String> listOfRooms) {
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

package map.project.demo.Domain;

import map.project.demo.ObserverPattern.Observable;
import map.project.demo.ObserverPattern.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Cinema implements Observable {
    private String id;
    private String name;
    private String address;
    private Vector<Room> listOfRooms;
    private final List<Observer> observers;

    public Cinema(String id, String name, String address, Vector<Room> listOfRooms) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.listOfRooms = listOfRooms;
        this.observers = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Vector<Room> getListOfRooms() {
        return listOfRooms;
    }

    public void setListOfRooms(Vector<Room> listOfRooms) {
        this.listOfRooms = listOfRooms;
    }

    public void addRoom(Room room) {
        listOfRooms.add(room);
    }

    public void removeRoom(Room room) {
        listOfRooms.remove(room);
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

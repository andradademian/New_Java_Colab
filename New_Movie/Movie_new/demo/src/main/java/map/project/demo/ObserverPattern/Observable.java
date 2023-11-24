package map.project.demo.ObserverPattern;

import map.project.demo.Domain.Spectator;

import java.util.List;

public interface Observable {
    void addObserver(Observer observer);

    void notifyObservers(String news);

    List<Observer> getObservers();
}

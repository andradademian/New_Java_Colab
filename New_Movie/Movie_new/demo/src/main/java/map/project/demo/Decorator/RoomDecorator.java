package map.project.demo.Decorator;

import map.project.demo.Domain.Cinema;
import map.project.demo.Repository.CinemaRepositoryComponent;

import java.util.Vector;

public class RoomDecorator implements CinemaRepositoryComponent {
    private final CinemaRepositoryComponent decoratedComponent;

    public RoomDecorator(CinemaRepositoryComponent decoratedComponent) {
        this.decoratedComponent = decoratedComponent;
    }

    @Override
    public Vector<Cinema> getAll() {
        return decoratedComponent.getAll();
    }

    @Override
    public void add(Cinema cinema) {
        decoratedComponent.add(cinema);
    }

    @Override
    public void delete(Cinema cinema) {
        decoratedComponent.delete(cinema);
    }

    @Override
    public void deleteAll() {
        decoratedComponent.deleteAll();
    }

    @Override
    public void printAll() {
        decoratedComponent.printAll();
    }

    @Override
    public void addRoom(Cinema cinema, String roomId) {
        System.out.println("Decorator: Adding room with ID " + roomId);
        decoratedComponent.addRoom(cinema, roomId);
    }

    @Override
    public void deleteRoom(Cinema cinema, String roomId) {
        System.out.println("Decorator: Deleting room with ID " + roomId);
        decoratedComponent.deleteRoom(cinema, roomId);
    }
}

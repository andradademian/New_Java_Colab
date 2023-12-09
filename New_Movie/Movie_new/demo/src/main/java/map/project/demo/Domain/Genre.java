package map.project.demo.Domain;

import java.util.List;

public class Genre {
    private String id;
    private String name;
    private List<String> listOfMovies;

    public Genre() {

    }

    public Genre(String id, String name, List<String> listOfMovies) {
        this.id = id;
        this.name = name;
        this.listOfMovies = listOfMovies;
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

    public List<String> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<String> listOfMovies) {
        this.listOfMovies = listOfMovies;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", listOfMovies=" + listOfMovies +
                '}';
    }

    public void addMovie(String movieId) {
        this.listOfMovies.add(movieId);
    }

    public void deleteMovie(String movieId) {
        this.listOfMovies.remove(movieId);
    }

    public void deleteAllMovies() {
        this.listOfMovies.clear();
    }
}

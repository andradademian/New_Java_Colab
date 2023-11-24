package map.project.demo.Domain;

import java.util.List;

public class Genre {
    private String id;
    private String name;
    private List<Movie> listOfMovies;

    public Genre(String id, String name, List<Movie> listOfMovies) {
        this.id = id;
        this.name = name;
        this.listOfMovies = listOfMovies;
    }

    public String getId() {return id;}

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getListOfMovies() {
        return listOfMovies;
    }

    public void setListOfMovies(List<Movie> listOfMovies) {
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

    public void addMovie(Movie movie) {
        this.listOfMovies.add(movie);
    }

    public void deleteMovie(Movie movie) {
        this.listOfMovies.remove(movie);
    }

    public void deleteAllMovies() {
        this.listOfMovies.clear();
    }
}

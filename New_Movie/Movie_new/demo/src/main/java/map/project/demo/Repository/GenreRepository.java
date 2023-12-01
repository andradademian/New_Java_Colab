package map.project.demo.Repository;

import map.project.demo.*;

import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Cinema;
import map.project.demo.Domain.Movie;
import map.project.demo.Domain.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GenreRepository {
    private static GenreRepository instance;
    private final Vector<Genre> genres;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "slay");
    Statement insert = connection.createStatement();
    String insertStringFancy = "insert into \"Genre\"(id,genrename) VALUES (?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyIntoMovieGenre = "insert into \"MovieGenre\"(movieid, genreid) values (?, ?) on conflict (movieid, genreid) do nothing";
    PreparedStatement insertFancyIntoMovieGenre = connection.prepareStatement(insertStringFancyIntoMovieGenre);
    Statement select = connection.createStatement();

    public GenreRepository() throws SQLException {
        genres = getGenresFromTable();
    }

    public static GenreRepository getInstance() throws SQLException {
        if (instance == null) {
            instance = new GenreRepository();
        }
        return instance;
    }

    public Vector<Genre> getGenresFromTable() throws SQLException {
        Vector<Genre> genreVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Genre\"");
        while (result.next()) {
            String id = result.getString("id");
            String name = result.getString("genrename");
            genreVector.add(new Genre(id, name, getMoviesFromMovieGenreTable(id)));
        }
        return genreVector;
    }

    public List<String> getMoviesFromMovieGenreTable(String genreId) throws SQLException {
        List<String> moviesIds = new ArrayList<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT MG.movieid FROM \"Genre\" G join \"MovieGenre\" MG on MG.genreid=?");
        movieStatement.setString(1, genreId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            moviesIds.add(result.getString("movieid"));
        }
        return moviesIds;
    }

    public void deleteAllFromGenreTable() throws SQLException {
        select.execute("delete from \"Genre\"");
    }

    public void addGenresToTable() throws SQLException {
        for (Genre genre : genres) {
            insertFancy.setString(1, genre.getId());
            insertFancy.setString(2, genre.getName());
            insertFancy.executeUpdate();
        }
    }

    public void deleteAllFromMovieGenreTable() throws SQLException {
        select.execute("delete from \"MovieGenre\"");
    }

    public void addToMovieGenreTable() throws SQLException {
        for (Genre genre : genres) {
            for (String movieId : genre.getListOfMovies()) {
                insertFancyIntoMovieGenre.setString(1, movieId);
                insertFancyIntoMovieGenre.setString(2, genre.getId());
                insertFancyIntoMovieGenre.executeUpdate();
            }
        }
    }

    public void add(Genre genre) {
        genres.add(genre);
    }

    public void delete(Genre genre) {
        genres.remove(genre);
    }

    public void deleteAll() {
        genres.clear();
    }

    public void printAll() {
        System.out.println(genres);
    }

    public void updateName(Genre genre, String name) {
        genres.get(getAll().indexOf(genre)).setName(name);
    }

    public void deleteMovie(Genre genre, String movie) {
        genres.get(getAll().indexOf(genre)).deleteMovie(movie);
    }

    public void addMovie(Genre genre, String movie) {
        genres.get(getAll().indexOf(genre)).addMovie(movie);
    }

    public Vector<Genre> getAll() {
        return this.genres;
    }
}

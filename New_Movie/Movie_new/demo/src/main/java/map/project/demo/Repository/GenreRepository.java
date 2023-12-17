package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.Actor;
import map.project.demo.Domain.Movie;
import org.springframework.stereotype.Repository;
import map.project.demo.Domain.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Repository
public class GenreRepository {
    private static GenreRepository instance;
    private final Vector<Genre> genres;
    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancy = "insert into \"Genre\"(id,genrename) VALUES (?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancy = connection.prepareStatement(insertStringFancy);
    String insertStringFancyIntoMovieGenre = "insert into \"MovieGenre\"(movieid, genreid) values (?, ?) on conflict (movieid, genreid) do nothing";
    PreparedStatement insertFancyIntoMovieGenre = connection.prepareStatement(insertStringFancyIntoMovieGenre);
    Statement select = connection.createStatement();

    public GenreRepository() throws SQLException {
        genres = getGenresFromTable();
    }

    @Transactional
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

    @Transactional
    public Genre getGenreWithIdFromTable(String id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Genre\" where Id=?");
        statement.setString(1, id);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String genreName = result.getString("genreName");

            return new Genre(id, genreName, getMoviesFromMovieGenreTable(id));
        }
        return null;
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

    @Transactional
    public void deleteGenreWithIdFromTable(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Genre\" where id=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteAllFromGenreTable() throws SQLException {
        select.execute("delete from \"Genre\"");
    }

    @Transactional
    public void addGenresToTable() throws SQLException {
        for (Genre genre : genres) {
            insertFancy.setString(1, genre.getId());
            insertFancy.setString(2, genre.getName());
            insertFancy.executeUpdate();
        }
    }

    @Transactional
    public void addGenreToTable(Genre genre) throws SQLException {
        insertFancy.setString(1, genre.getId());
        insertFancy.setString(2, genre.getName());
        insertFancy.executeUpdate();
    }

    @Transactional
    public void deleteAllFromMovieGenreTable() throws SQLException {
        select.execute("delete from \"MovieGenre\"");
    }

    @Transactional
    public void deleteAllMoviesFromGenreWithId(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"MovieGenre\" where genreid=?");
        preparedStatement.setString(1, id);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void addToMovieGenreTable() throws SQLException {
        for (Genre genre : genres) {
            for (String movie : genre.getListOfMovies()) {
                insertFancyIntoMovieGenre.setString(1, genre.getId());
                insertFancyIntoMovieGenre.setString(2, movie);
                insertFancyIntoMovieGenre.executeUpdate();
            }
        }
    }


    @Transactional
    public void add(Genre genre) {
        genres.add(genre);
    }

    @Transactional
    public void delete(Genre genre) {
        genres.remove(genre);
    }

    @Transactional
    public void deleteAll() {
        genres.clear();
    }

    @Transactional
    public void printAll() {
        System.out.println(genres);
    }

    @Transactional
    public void updateName(Genre genre, String name) {
        genres.get(getAll().indexOf(genre)).setName(name);
    }

    @Transactional
    public void deleteMovie(String genreId, String movieId) throws SQLException {
        PreparedStatement movieStatement = connection.prepareStatement(" delete FROM \"MovieGenre\" MG where MG.genreid=? and MG.movieid=?");
        movieStatement.setString(1, genreId);
        movieStatement.setString(2, movieId);
        movieStatement.execute();
    }

    @Transactional
    public void addMovie(String genreId, String movieId) throws SQLException {
        insertFancyIntoMovieGenre.setString(1, genreId);
        insertFancyIntoMovieGenre.setString(2, movieId);
        insertFancyIntoMovieGenre.executeUpdate();

    }

    @Transactional
    public Vector<Genre> getAll() {
        return this.genres;
    }
}

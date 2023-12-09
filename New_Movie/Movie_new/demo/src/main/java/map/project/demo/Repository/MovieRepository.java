package map.project.demo.Repository;

import jakarta.transaction.Transactional;
import map.project.demo.Domain.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Vector;

@Repository
public class MovieRepository {
    //    private static MovieRepository instance;
    private final Vector<Movie> movies;

    Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Movie", "MyUser", "castravete");
    Statement insert = connection.createStatement();
    String insertStringFancyIntoMovie = "INSERT INTO \"Movie\"(id, title, durationInMinutes) VALUES (?, ?, ?) on conflict (id) do nothing";
    PreparedStatement insertFancyIntoMovie = connection.prepareStatement(insertStringFancyIntoMovie);
    String insertStringFancyIntoActorMovie = "INSERT INTO \"ActorMovie\"(actorid, movieid) VALUES (?, ?) ON CONFLICT (actorid, movieid) DO NOTHING";
    PreparedStatement insertFancyIntoActorMovie = connection.prepareStatement(insertStringFancyIntoActorMovie);
    String insertStringFancyIntoMovieGenre = "INSERT INTO \"MovieGenre\"(movieid, genreid) VALUES (?, ?) ON CONFLICT (movieid, genreid) DO NOTHING";
    PreparedStatement insertFancyIntoMovieGenre = connection.prepareStatement(insertStringFancyIntoMovieGenre);
    String insertStringFancyIntoMovieDirector = "INSERT INTO \"MovieDirector\"(movieid, directorid) VALUES (?, ?) ON CONFLICT (movieid, directorid) DO NOTHING";
    PreparedStatement insertFancyIntoMovieDirector = connection.prepareStatement(insertStringFancyIntoMovieDirector);

    Statement select = connection.createStatement();


    public MovieRepository() throws SQLException {
        movies = getMoviesFromTable();
    }

//    public static MovieRepository getInstance() throws SQLException {
//        if (instance == null) {
//            instance = new MovieRepository();
//        }
//        return instance;
//    }

    @Transactional
    public Vector<Movie> getMoviesFromTable() throws SQLException {
        Vector<Movie> movieVector = new Vector<>();
        ResultSet result = select.executeQuery(" SELECT * FROM \"Movie\"");
        while (result.next()) {
            String id = result.getString("id");
            String movieTitle = result.getString("title");
            int durationInMinutes = result.getInt("durationInMinutes");
            movieVector.add(new Movie(id, movieTitle, durationInMinutes, getDirectorsFromMovieDirectorTable(id), getActorsFromActorMovieTable(id), getGenresFromMovieGenreTable(id)));
        }
        return movieVector;
    }

    @Transactional
    public Movie getMovieWithIdFromTable(String movieId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(" SELECT * FROM \"Movie\" where Id=?");
        statement.setString(1, movieId);
        ResultSet result = statement.executeQuery();
        if (result.next()) {
            String id = result.getString("id");
            String movieTitle = result.getString("title");
            int durationInMinutes = result.getInt("durationInMinutes");
            return new Movie(id, movieTitle, durationInMinutes, getDirectorsFromMovieDirectorTable(id), getActorsFromActorMovieTable(id), getGenresFromMovieGenreTable(id));
        }
        return null;
    }

    @Transactional
    public void deleteMovieWithIdFromTable(String movieId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"Movie\" where id=?");
        preparedStatement.setString(1, movieId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteAllFromMovieTable() throws SQLException {
        select.execute("delete from \"Movie\"");
    }

    @Transactional
    public void addMoviesToTable() throws SQLException {
        for (Movie movie : movies) {
            insertFancyIntoMovie.setString(1, movie.getId());
            insertFancyIntoMovie.setString(2, movie.getTitle());
            insertFancyIntoMovie.setInt(3, movie.getDurationInMinutes());
            insertFancyIntoMovie.executeUpdate();
        }
    }

    @Transactional
    public void addMovieToTable(Movie movie) throws SQLException {
        insertFancyIntoMovie.setString(1, movie.getId());
        insertFancyIntoMovie.setString(2, movie.getTitle());
        insertFancyIntoMovie.setInt(3, movie.getDurationInMinutes());
        insertFancyIntoMovie.executeUpdate();

        for (String actorId : movie.getActors()) {
            insertFancyIntoActorMovie.setString(1, actorId);
            insertFancyIntoActorMovie.setString(2, movie.getId());
            insertFancyIntoActorMovie.executeUpdate();
        }

        for (String genreId : movie.getGenres()) {
            insertFancyIntoMovieGenre.setString(1, movie.getId());
            insertFancyIntoMovieGenre.setString(2, genreId);
            insertFancyIntoMovieGenre.executeUpdate();
        }

        for (String stageDirectorId : movie.getStageDirectors()) {
            insertFancyIntoMovieDirector.setString(1, movie.getId());
            insertFancyIntoMovieDirector.setString(2, stageDirectorId);
            insertFancyIntoMovieDirector.executeUpdate();
        }
    }

    @Transactional
    public void deleteAllFromActorMovieTable() throws SQLException {
        select.execute("delete from \"ActorMovie\"");
    }

//    public void addToActorMovieTable() throws SQLException {
//        for (Movie movie : movies) {
//            for (String actorId : movie.getActors()) {
//                insertFancyIntoActorMovie.setString(1, actorId);
//                insertFancyIntoActorMovie.setString(2, movie.getId());
//                insertFancyIntoActorMovie.executeUpdate();
//            }
//        }
//    }

    @Transactional
    public void deleteAllFromMovieGenreTable() throws SQLException {
        select.execute("delete from \"MovieGenre\"");
    }

//    public void addToMovieGenreTable() throws SQLException {
//        for (Movie movie : movies) {
//            for (String genreId : movie.getGenres()) {
//                insertFancyIntoMovieGenre.setString(1, movie.getId());
//                insertFancyIntoMovieGenre.setString(2, genreId);
//                insertFancyIntoMovieGenre.executeUpdate();
//            }
//        }
//    }

    @Transactional
    public Vector<String> getDirectorsFromMovieDirectorTable(String movieId) throws SQLException {
        Vector<String> directorIds = new Vector<>();
        PreparedStatement awardStatement = connection.prepareStatement(" SELECT AA.directorid FROM \"MovieDirector\" AA where AA.movieid=?");
        awardStatement.setString(1, movieId);
        ResultSet result = awardStatement.executeQuery();
        while (result.next()) {
            directorIds.add(result.getString("directorid"));
        }
        return directorIds;
    }

    @Transactional
    public Vector<String> getActorsFromActorMovieTable(String movieId) throws SQLException {
        Vector<String> actorsIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT AM.actorid FROM  \"ActorMovie\" AM where AM.movieid=?");
        movieStatement.setString(1, movieId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            actorsIds.add(result.getString("actorid"));
        }
        return actorsIds;
    }

    @Transactional
    public Vector<String> getGenresFromMovieGenreTable(String movieId) throws SQLException {
        Vector<String> genreIds = new Vector<>();
        PreparedStatement movieStatement = connection.prepareStatement(" SELECT MG.genreid FROM \"MovieGenre\" MG where MG.movieid=?");
        movieStatement.setString(1, movieId);
        ResultSet result = movieStatement.executeQuery();
        while (result.next()) {
            genreIds.add(result.getString("genreid"));
        }
        return genreIds;
    }

    @Transactional
    public void deleteAllFromMovieDirectorTable() throws SQLException {
        select.execute("delete from \"MovieDirector\"");
    }

    @Transactional
    public void updateTitleForMovieWithId(String movieId, String title) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Movie\" set title=? where id=?");
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, movieId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void updateDurationForMovieWithId(String movieId, int duration) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("update \"Movie\" set durationinminutes=? where id=?");
        preparedStatement.setInt(1, duration);
        preparedStatement.setString(2, movieId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void addDirectorToMovie(String movieId, String directorId) throws SQLException {
        insertFancyIntoMovieDirector.setString(1, movieId);
        insertFancyIntoMovieDirector.setString(2, directorId);
        insertFancyIntoMovieDirector.executeUpdate();
    }

    @Transactional
    public void addActorToMovie(String movieId, String actorId) throws SQLException {
        insertFancyIntoActorMovie.setString(1, actorId);
        insertFancyIntoActorMovie.setString(2, movieId);
        insertFancyIntoActorMovie.executeUpdate();
    }

    @Transactional
    public void addGenreToMovie(String movieId, String genreId) throws SQLException {
        insertFancyIntoMovieGenre.setString(1, movieId);
        insertFancyIntoMovieGenre.setString(2, genreId);
        insertFancyIntoMovieGenre.executeUpdate();
    }

    @Transactional
    public void deleteGenreFromMovie(String movieId, String genreId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"MovieGenre\" where movieid=? and genreid=?");
        preparedStatement.setString(1, movieId);
        preparedStatement.setString(2, genreId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteActorFromMovie(String movieId, String actorId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"ActorMovie\" where actorid=? and movieid=?");
        preparedStatement.setString(1, actorId);
        preparedStatement.setString(2, movieId);
        preparedStatement.executeUpdate();
    }

    @Transactional
    public void deleteDirectorFromMovie(String movieId, String directorId) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("delete from \"MovieDirector\" where directorid=? and movieid=?");
        preparedStatement.setString(1, directorId);
        preparedStatement.setString(2, movieId);
        preparedStatement.executeUpdate();
    }

}

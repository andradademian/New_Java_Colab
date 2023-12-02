package map.project.demo.Proxy;

import map.project.demo.Domain.Movie;

import java.util.ArrayList;
import java.util.List;

public class ProxyMovieFilter implements IMovie {
    private static final List<String> bannedMovies;

    static {
        bannedMovies = new ArrayList<>();
        bannedMovies.add("Planeta maimutelor");
        bannedMovies.add("Lolita");
        bannedMovies.add("Visul lui Einstein");
    }

    @Override
    public void showMovie(Movie movie) throws Exception {
        if (bannedMovies.contains(movie.getTitle())) {
            throw new Exception("Movie \"" + movie.getTitle() + "\" is banned.");
        }
        movie.showMovie();
    }
}

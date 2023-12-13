package map.project.demo.Proxy;

import map.project.demo.Domain.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ProxyMovieFilter extends Movie {
    private static final List<String> bannedMovies;
    private final Movie movie;

    static {
        bannedMovies = new ArrayList<>();
        bannedMovies.add("Planeta maimutelor");
        bannedMovies.add("Lolita");
        bannedMovies.add("Visul lui Einstein");
    }

    public ProxyMovieFilter(Movie movie) {
        super();
        this.movie = movie;
    }

    @Override
    public void showMovie() throws Exception {

        if (bannedMovies.contains(movie.getTitle())) {
            throw new Exception("Movie \"" + movie.getTitle() + "\" is banned.");
        }
        movie.showMovie();
    }
}

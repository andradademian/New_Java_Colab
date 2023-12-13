package map.project.demo.Domain;

import map.project.demo.Proxy.IMovie;

import java.util.*;

public class Movie{
    private String id;
    private String title;
    private int durationInMinutes;
    private Vector<String> stageDirectors;
    private Vector<String> actors;
    private Vector<String> genres;

    public Movie(String id, String title, int durationInMinutes, Vector<String> stageDirectors, Vector<String> actors, Vector<String> genres) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.stageDirectors = stageDirectors;
        this.actors = actors;
        this.genres = genres;
    }

    public Movie() {

    }

    public Vector<String> getActors() {
        return actors;
    }

    public void setActors(Vector<String> actors) {
        this.actors = actors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Vector<String> getStageDirectors() {
        return stageDirectors;
    }

    public void setStageDirectors(Vector<String> stageDirectors) {
        this.stageDirectors = stageDirectors;
    }

    public Vector<String> getGenres() {
        return genres;
    }

    public void addGenre(String genre) {
        genres.add(genre);
    }

    public void deleteGenre(String genre) {
        genres.remove(genre);
    }

    public void deleteStageDirector(String stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    public void addStageDirector(String stageDirector) {
        stageDirectors.add(stageDirector);
    }

    public void addActor(String actor) {
        actors.add(actor);
    }

    public void deleteActor(String actor) {
        actors.remove(actor);
    }


    public void setGenres(Vector<String> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", durationInMinutes=" + durationInMinutes +
                ", stageDirectors=" + stageDirectors +
                ", actors=" + actors +
                ", genres=" + genres +
                '}';
    }


    public void showMovie() throws Exception {
        System.out.println("Movie \""+this.title+"\" is showed.");
    }
}

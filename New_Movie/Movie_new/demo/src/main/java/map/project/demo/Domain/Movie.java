package map.project.demo.Domain;

import java.util.*;

public class Movie {
    private String id;
    private String title;
    private int durationInMinutes;
    private Vector<StageDirector> stageDirectors;
    private Vector<Actor> actors;
    private Vector<Genre> genres;

    public Movie(String id, String title, int durationInMinutes, Vector<StageDirector> stageDirectors, Vector<Actor> actors, Vector<Genre> genres) {
        this.id = id;
        this.title = title;
        this.durationInMinutes = durationInMinutes;
        this.stageDirectors = stageDirectors;
        this.actors = actors;
        this.genres = genres;
    }

    public Vector<Actor> getActors() {
        return actors;
    }

    public void setActors(Vector<Actor> actors) {
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

    public Vector<StageDirector> getStageDirectors() {
        return stageDirectors;
    }

    public void setStageDirectors(Vector<StageDirector> stageDirectors) {
        this.stageDirectors = stageDirectors;
    }

    public Vector<Genre> getGenres() {
        return genres;
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
    }

    public void deleteGenre(Genre genre) {
        genres.remove(genre);
    }

    public void deleteStageDirector(StageDirector stageDirector) {
        stageDirectors.remove(stageDirector);
    }

    public void addStageDirector(StageDirector stageDirector) {
        stageDirectors.add(stageDirector);
    }

    public void addActor(Actor actor) {
        actors.add(actor);
    }

    public void deleteActor(Actor actor) {
        actors.remove(actor);
    }


    public void setGenres(Vector<Genre> genres) {
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
}

package edu.asu.bscs.a1203737023.lab3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by apsMac1 on 2/9/16.
 */
public class MovieLibrary extends Object implements Serializable {
    private List<String> movieTitles;
    private List<Movie> movies;
    private transient MainActivity parent;

    public void addMovie(Movie newMovie) {
        movies.add(newMovie);
    }

    public void setMovieList(List<Movie> movies) {
        this.movies = movies;
    }

    public String toJsonString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for(int i = 0; i < this.movies.size(); ++i) {
            stringBuilder.append("{");
            stringBuilder.append(this.movies.get(i).toJsonString());
            if (i == this.movies.size() - 1) stringBuilder.append("}");
            else stringBuilder.append("},");
        }
         stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public int getSize() {
        return movies.size();
    }

    public List<Movie> getMovieList() {
        return movies;
    }

    public Movie get(int element) {
        return movies.get(element);
    }

    public Movie getWithTitle(String title) {
        int index = movieTitles.indexOf(title);
        return movies.get(index);
    }

    // public constructor is necessary for collections
    public MovieLibrary() {
        movies = new ArrayList<Movie>();
    }
}

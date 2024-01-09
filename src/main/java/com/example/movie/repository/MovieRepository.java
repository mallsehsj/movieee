// Write your code here
package com.example.movie.repository;

import com.example.movie.model.Movie;
import java.util.ArrayList;

public interface MovieRepository {

    public ArrayList<Movie> getMovies();

    public Movie addMovie(Movie movie);

    public Movie getMovieById(int movieid);

    public Movie updateMovie(int movieid, Movie movie);

    public void deleteMovie(int movieid);

}
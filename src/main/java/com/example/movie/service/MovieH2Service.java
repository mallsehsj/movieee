/*
 * You can use the following import statements
 *
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.*;
 *
 */

// Write your code here
package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.ArrayList;

import com.example.movie.repository.*;
import com.example.movie.model.*;

@Service
public class MovieH2Service implements MovieRepository {

   @Autowired
   private JdbcTemplate db;

   @Override
   public ArrayList<Movie> getMovies() {
      List<Movie> list = db.query("select * from movielist", new MovieRowMapper());
      ArrayList<Movie> arraylist = new ArrayList<>(list);
      return arraylist;
   }

   @Override

   public Movie addMovie(Movie movie) {
      db.update("insert into movielist(movieName, leadActor) values(?,?)", movie.getMovieName(), movie.getLeadActor());
      return db.queryForObject("select * from movielist where movieId=?", new MovieRowMapper(), movie.getMovieId());
   }

   @Override

   public Movie getMovieById(int movieid) {
      try {
         return db.queryForObject("select * from movielist where movieId=?", new MovieRowMapper(), movieid);

      } catch (Exception w) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }

   }

   @Override

   public void deleteMovie(int movieid) {

      db.update("delete from movielist where movieId=?", movieid);

   }

   @Override

   public Movie updateMovie(int movieid, Movie movie) {
      try {
         if (movie.getMovieName() != null) {
            db.update("update movielist set movieName=? where movieId=?", movie.getMovieName(), movieid);
         }
         if (movie.getLeadActor() != null) {
            db.update("update movielist set leadActor=? where movieId=?", movie.getLeadActor(), movieid);
         }
         return db.queryForObject("select * from movielist where movieId=?", new MovieRowMapper(), movieid);

      } catch (Exception e) {
         throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
   }

}
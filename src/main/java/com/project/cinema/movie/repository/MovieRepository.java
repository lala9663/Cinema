package com.project.cinema.movie.repository;

import com.project.cinema.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    @Query("SELECT m FROM Movie m WHERE m.movieDeleted = false")
    List<Movie> findUnDeletedMovies();

    @Query("SELECT m FROM Movie m WHERE m.movieId = :movieId AND m.movieDeleted = false")
    Movie findUnDeletedMovieById(@Param("movieId") Long movieId);

}

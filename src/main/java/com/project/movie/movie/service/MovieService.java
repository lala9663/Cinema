package com.project.movie.movie.service;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.dto.request.UpdateMovieDto;
import com.project.movie.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    long registerMovie(RegisterMovieDto registerMovieDto);
    Movie getMovieById(long id);
    List<Movie> getAllMovies();
    void deleteMovie(long id);
    long updateMovie(long id, UpdateMovieDto updateMovieDto);
}


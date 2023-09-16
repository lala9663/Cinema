package com.project.movie.movie.service;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    Long registerMovie(RegisterMovieDto registerMovieDto);
    Movie getMovieById(Long id);
    List<Movie> getAllMovies();
    void deleteMovie(Long id);
}


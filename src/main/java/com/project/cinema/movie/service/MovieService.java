package com.project.cinema.movie.service;

import com.project.cinema.movie.dto.request.RegisterMovieDto;
import com.project.cinema.movie.dto.request.UpdateMovieDto;
import com.project.cinema.movie.dto.response.MovieDetailDto;
import com.project.cinema.movie.dto.response.MovieDto;
import com.project.cinema.movie.entity.Movie;

import java.util.List;

public interface MovieService {
    long registerMovie(RegisterMovieDto registerMovieDto);
    List<MovieDto> getAllMovies();
    MovieDetailDto getDetailMovieById(Long movieId);
    void deleteMovie(long id);
    long updateMovie(long id, UpdateMovieDto updateMovieDto);
}


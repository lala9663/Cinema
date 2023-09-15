package com.project.movie.movie.service.impl;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.entity.Movie;
import com.project.movie.movie.repository.MovieRepository;
import com.project.movie.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public Long registerMovie(RegisterMovieDto registerMovieDto) {
        Movie movie = registerMovieDto.toEntity();
        movieRepository.save(movie);
        Movie savedMovie = movieRepository.save(movie);

        return savedMovie.getMovieId();
    }
}

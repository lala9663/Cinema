package com.project.movie.movie.service.impl;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.entity.Movie;
import com.project.movie.movie.exception.MovieException;
import com.project.movie.movie.repository.MovieRepository;
import com.project.movie.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    public long registerMovie(RegisterMovieDto registerMovieDto) {
        Movie movie = registerMovieDto.toEntity();
        movieRepository.save(movie);
        Movie savedMovie = movieRepository.save(movie);

        return savedMovie.getMovieId();
    }

    @Override
    public Movie getMovieById(long id) {

        return movieRepository.findById(id).orElse(null);
    }
    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findUnDeletedMovies();
    }

    @Override
    public void deleteMovie(long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            movie.markAsDeleted();
            movieRepository.save(movie);
        } else {
            throw MovieException.deleteException(movieId);
        }
    }
}

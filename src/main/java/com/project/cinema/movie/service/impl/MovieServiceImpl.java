package com.project.cinema.movie.service.impl;

import com.project.cinema.movie.dto.request.RegisterMovieDto;
import com.project.cinema.movie.dto.request.UpdateMovieDto;
import com.project.cinema.movie.entity.Movie;
import com.project.cinema.movie.exception.MovieException;
import com.project.cinema.movie.repository.MovieRepository;
import com.project.cinema.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;

    @Override
    @Transactional
    public long registerMovie(RegisterMovieDto registerMovieDto) {

        if (registerMovieDto == null) {
            throw MovieException.nullFieldException();
        }

        Movie movie = registerMovieDto.toEntity();

        try {
            Movie savedMovie = movieRepository.save(movie);
            return savedMovie.getMovieId();
        } catch (Exception e) {
            e.printStackTrace();
            throw MovieException.failRegisterException();
        }
    }

    @Override
    @Transactional
    public Movie getMovieById(long movieId) {
        Optional<Movie> movieOptional = movieRepository.findById(movieId);
        if (movieOptional.isPresent()) {
            Movie movie = movieOptional.get();
            if (movie.isMovieDeleted()) {
                throw MovieException.movieAlreadyDeletedException(movieId);
            }
            return movie;
        } else {
            throw MovieException.movieNotFoundException(movieId);
        }
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

            if (movie.isMovieDeleted()) {
                throw MovieException.movieAlreadyDeletedException(movieId);
            }

            movie.markAsDeleted();
            movieRepository.save(movie);
        } else {
            throw MovieException.movieNotFoundException(movieId);
        }
    }
    @Override
    public long updateMovie(long movieId, UpdateMovieDto updateMovieDto) {

        Movie movieToUpdate = movieRepository.findUnDeletedMovies()
                .stream()
                .filter(movie -> movie.getMovieId().equals(movieId))
                .findFirst()
                .orElseThrow(() -> MovieException.movieNotFoundException(movieId));

        Movie updatedMovie = updateMovieDto.toEntity();

        movieToUpdate.updateFrom(updatedMovie);

        Movie savedMovie = movieRepository.save(movieToUpdate);

        return savedMovie.getMovieId();
    }

}

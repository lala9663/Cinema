package com.project.cinema.movie.service.impl;

import com.project.cinema.movie.dto.request.RegisterMovieDto;
import com.project.cinema.movie.dto.request.UpdateMovieDto;
import com.project.cinema.movie.dto.response.MovieDetailDto;
import com.project.cinema.movie.dto.response.MovieDto;
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
import java.util.stream.Collectors;

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
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findUnDeletedMovies();
        return movies.stream()
                .map(MovieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public MovieDetailDto getDetailMovieById(Long movieId) {
        Movie movie = movieRepository.findUnDeletedMovieById(movieId);

        return MovieDetailDto.fromEntity(movie);
    }

    @Override
    public long updateMovie(Long movieId, UpdateMovieDto updateMovieDto) {
        Movie existingMovie = movieRepository.findUnDeletedMovieById(movieId);

        Movie updateMovie = updateMovieDto.toEntity();

        existingMovie.updateFrom(updateMovie);

        movieRepository.save(existingMovie);

        return existingMovie.getMovieId();
    }

    @Override
    public void deleteMovie(Long movieId) {
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

}

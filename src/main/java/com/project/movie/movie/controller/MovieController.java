package com.project.movie.movie.controller;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.dto.request.UpdateMovieDto;
import com.project.movie.movie.entity.Movie;
import com.project.movie.movie.exception.MovieException;
import com.project.movie.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor

public class MovieController {
    private final MovieService movieService;

    @PostMapping("/register")
    public ResponseEntity<?> registerMovie(@RequestBody RegisterMovieDto registerMovieDto) {
        try {
            Long movieId = movieService.registerMovie(registerMovieDto);
            return ResponseEntity.ok("영화가 등록되었습니다. 영화 ID는 " + movieId + " 입니다.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 실패 했습니다.");
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movieId = movieService.getMovieById(id);

        if (movieId != null) {
            return ResponseEntity.ok(movieId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long movieId) {
        try {
            movieService.deleteMovie(movieId);
            return ResponseEntity.ok("성공적으로 삭제 되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the movie");
        }
    }
    @PutMapping("/{movieId}")
    public ResponseEntity<String> updateMovie(
            @PathVariable Long movieId,
            @RequestBody UpdateMovieDto updateMovieDto) {
        try {
            movieService.updateMovie(movieId, updateMovieDto);
            return ResponseEntity.ok("Movie updated successfully.");
        } catch (MovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found with ID: " + movieId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update movie.");
        }
    }

}


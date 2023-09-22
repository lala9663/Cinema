package com.project.movie.movie.controller;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.dto.request.UpdateMovieDto;
import com.project.movie.movie.entity.Movie;
import com.project.movie.movie.exception.MovieException;
import com.project.movie.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "영화 등록 요청", description = "영화가 등록됩니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerMovie(@RequestBody RegisterMovieDto registerMovieDto) {
        try {
            long movieId = movieService.registerMovie(registerMovieDto);
            return ResponseEntity.ok("영화가 등록되었습니다. 영화 ID는 " + movieId + " 입니다.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("등록 실패 했습니다.");
        }
    }

    @Operation(summary = "영화 상세 조회", description = "영화 조회합니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable long movieId) {
        Movie id = movieService.getMovieById(movieId);

        if (id != null) {
            return ResponseEntity.ok(id);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "등록된 영화 조회", description = "영화 목록을 보여줍니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @Operation(summary = "영화 삭제 요청", description = "영화를 삭제합니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{movieId}")
    public ResponseEntity<String> deleteMovie(@PathVariable long movieId) {
        try {
            movieService.deleteMovie(movieId);
            return ResponseEntity.ok("성공적으로 삭제 되었습니다.");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the movie");
        }
    }

    @Operation(summary = "영화 수정 요청", description = "영화가 수정됩니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/{movieId}")
    public ResponseEntity<String> updateMovie(
            @PathVariable long movieId,
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


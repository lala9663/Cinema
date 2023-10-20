package com.project.cinema.movie.controller;

import com.project.cinema.movie.dto.request.RegisterMovieDto;
import com.project.cinema.movie.dto.request.UpdateMovieDto;
import com.project.cinema.movie.dto.response.MovieDetailDto;
import com.project.cinema.movie.dto.response.MovieDto;
import com.project.cinema.movie.entity.Movie;
import com.project.cinema.movie.exception.MovieException;
import com.project.cinema.movie.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
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
    public ResponseEntity<String> registerMovie(@RequestBody @Valid RegisterMovieDto registerMovieDto) {
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
    public ResponseEntity<MovieDetailDto> getMovieById(@PathVariable Long movieId) {
        MovieDetailDto movieDetailDto = movieService.getDetailMovieById(movieId);
        return ResponseEntity.ok(movieDetailDto);
    }

    @Operation(summary = "등록된 영화 조회", description = "영화 목록을 보여줍니다.", tags = { "Movie Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/list")
    public ResponseEntity<List<MovieDto>> getAllMovies() {
        List<MovieDto> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
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
            @PathVariable Long movieId,
            @Valid @RequestBody UpdateMovieDto updateMovieDto) {
        try {
            movieService.updateMovie(movieId, updateMovieDto);
            return ResponseEntity.ok("성공적으로 수정 되었습니다.");
        } catch (MovieException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("해당 영화: " + movieId + " 를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("수정하는데 실패했습니다.");
        }
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("영화를 찾을 수 없습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("영화를 삭제 하던 중 오류 발생");
        }
    }
}


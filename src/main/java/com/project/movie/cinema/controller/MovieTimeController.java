package com.project.movie.cinema.controller;

import com.project.movie.cinema.dto.request.RegisterMovieTimeDto;
import com.project.movie.cinema.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cinemas/movies")
@RequiredArgsConstructor
public class MovieTimeController {
    private final CinemaService cinemaService;
    @Operation(summary = "영화시간 등록 요청", description = "영화시간을 등록합니다.", tags = {"MovieTime Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/register/{cinemaId}/{movieId}")
    public ResponseEntity<String> registerMovieTime(@PathVariable long cinemaId,
                                                    @PathVariable long movieId,
                                                    @RequestBody RegisterMovieTimeDto registerMovieTimeDto) {
        try {
            long movieTimeId = cinemaService.registerMovieTime(cinemaId, movieId, registerMovieTimeDto);
            return ResponseEntity.ok("영화시간이 등록되었습니다. 등록된 영화시간 ID는 " + movieTimeId + " 입니다.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록에 실패했습니다.");
        }
    }
}

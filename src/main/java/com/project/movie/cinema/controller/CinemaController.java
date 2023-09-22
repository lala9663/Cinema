package com.project.movie.cinema.controller;

import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinemas")
@RequiredArgsConstructor
public class CinemaController {
    private final CinemaService cinemaService;

    @Operation(summary = "영화관 등록 요청", description = "영화관이 등록됩니다.", tags = {"Cinema Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping("/register")
    public ResponseEntity<?> registerCinema(@RequestBody RegisterCinemaDto registerCinemaDto) {
        try {
            long cinemaId = cinemaService.registerCinema(registerCinemaDto);
            return ResponseEntity.ok("영화가 등록되었습니다. 등록된 영화관 ID는 " + cinemaId + " 입니다.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록에 실패했습니다.");
        }
    }
}

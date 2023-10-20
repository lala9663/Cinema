package com.project.cinema.cinema.controller;

import com.project.cinema.cinema.dto.request.RegisterCinemaDto;
import com.project.cinema.cinema.dto.request.UpdateCinemaDto;
import com.project.cinema.cinema.dto.response.CinemaDto;
import com.project.cinema.cinema.service.CinemaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public ResponseEntity<String> registerCinema(@RequestBody @Valid RegisterCinemaDto registerCinemaDto) {
        try {
            long cinemaId = cinemaService.registerCinema(registerCinemaDto);
            return ResponseEntity.ok("영화관이 등록되었습니다. 등록된 영화관 ID는 " + cinemaId + " 입니다.");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("등록에 실패했습니다.");
        }
    }

    @Operation(summary = "영화관 조회 요청", description = "영화관을 조회합니다.", tags = {"Cinema Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<List<CinemaDto>> getAllCinemas() {
        List<CinemaDto> cinemas = cinemaService.getAllCinemas();
        return ResponseEntity.ok(cinemas);
        }


    @Operation(summary = "영화관 상세 조회", description = "영화관을 조회합니다.", tags = { "Cinema Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping("/{cinemaId}")
    public ResponseEntity<CinemaDto> getMovieById(@PathVariable Long cinemaId) {
        try {
            CinemaDto cinemaDto = cinemaService.getCinemaById(cinemaId);
            return ResponseEntity.ok(cinemaDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }



    @Operation(summary = "영화관 수정", description = "영화관을 수정합니다.", tags = { "Cinema Controller" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PutMapping("/{cinemaId}")
    public ResponseEntity<String> updateCinema(
            @PathVariable Long cinemaId,
            @Valid @RequestBody UpdateCinemaDto updateCinemaDto) {
        try {
            cinemaService.updateCinema(cinemaId, updateCinemaDto);
            return ResponseEntity.ok("영화관이 수정되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류");
        }
    }

    @Operation(summary = "영화관 삭제", description = "영화관을 삭제합니다.", tags = {"Cinema Controller"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @DeleteMapping("/{cinemaId}")
    public ResponseEntity<String> deleteCinema(@PathVariable Long cinemaId) {
        try {
            cinemaService.deleteCinema(cinemaId);
            return ResponseEntity.ok("삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류?");
        }
    }
}

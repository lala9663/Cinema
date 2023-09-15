package com.project.movie.movie.controller;

import com.project.movie.movie.dto.request.RegisterMovieDto;
import com.project.movie.movie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


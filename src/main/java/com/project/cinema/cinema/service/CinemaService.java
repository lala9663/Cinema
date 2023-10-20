package com.project.cinema.cinema.service;

import com.project.cinema.cinema.dto.request.RegisterCinemaDto;
import com.project.cinema.cinema.dto.request.UpdateCinemaDto;
import com.project.cinema.cinema.dto.response.CinemaDto;

import java.util.List;

public interface CinemaService {
    long registerCinema(RegisterCinemaDto registerCinemaDto);
    List<CinemaDto> getAllCinemas();
    CinemaDto getCinemaById(Long cinemaId);
    long updateCinema(Long cinemaId, UpdateCinemaDto updateCinemaDto);
    void deleteCinema(Long cinemaId);
//    long registerMovieTime(long cinemaId, long movieId, RegisterMovieTimeDto registerMovieTimeDto) throws Exception;
}

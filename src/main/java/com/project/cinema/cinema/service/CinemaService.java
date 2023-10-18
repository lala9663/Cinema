package com.project.cinema.cinema.service;

import com.project.cinema.cinema.dto.request.DeleteCinemaDto;
import com.project.cinema.cinema.dto.request.RegisterCinemaDto;
import com.project.cinema.cinema.dto.request.UpdateCinemaDto;
import com.project.cinema.cinema.entity.Cinema;

import java.util.List;

public interface CinemaService {
    long registerCinema(RegisterCinemaDto registerCinemaDto);
    List<Cinema> allCinemas();
    Cinema getCinemaById(long cinemaId);
    long updateCinema(long cinemaId, UpdateCinemaDto updateCinemaDto);
    void deleteCinema(DeleteCinemaDto deleteCinemaDto);
//    long registerMovieTime(long cinemaId, long movieId, RegisterMovieTimeDto registerMovieTimeDto) throws Exception;
}

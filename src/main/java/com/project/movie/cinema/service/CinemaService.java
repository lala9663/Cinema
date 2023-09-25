package com.project.movie.cinema.service;

import com.project.movie.cinema.dto.request.DeleteCinemaDto;
import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.dto.request.RegisterMovieTimeDto;
import com.project.movie.cinema.dto.request.UpdateCinemaDto;
import com.project.movie.cinema.entity.Cinema;

import java.util.List;

public interface CinemaService {
    long registerCinema(RegisterCinemaDto registerCinemaDto);
    List<Cinema> allCinemas();
    Cinema getCinemaById(long cinemaId);
    long updateCinema(long cinemaId, UpdateCinemaDto updateCinemaDto);
    void deleteCinema(DeleteCinemaDto deleteCinemaDto);
    long registerMovieTime(long cinemaId, long movieId, RegisterMovieTimeDto registerMovieTimeDto) throws Exception;
}

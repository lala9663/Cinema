package com.project.movie.cinema.service;

import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.entity.Cinema;

import java.util.List;

public interface CinemaService {
    long registerCinema(RegisterCinemaDto registerCinemaDto);
    List<Cinema> allCinemas();
    Cinema getCinemaById(long cinemaId);
}

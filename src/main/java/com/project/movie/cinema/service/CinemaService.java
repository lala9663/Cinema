package com.project.movie.cinema.service;

import com.project.movie.cinema.dto.request.RegisterCinemaDto;

public interface CinemaService {
    long registerCinema(RegisterCinemaDto registerCinemaDto);
}

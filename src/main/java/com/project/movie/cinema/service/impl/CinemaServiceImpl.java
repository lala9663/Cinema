package com.project.movie.cinema.service.impl;

import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.entity.Cinema;
import com.project.movie.cinema.exception.CinemaException;
import com.project.movie.cinema.repository.CinemaRepository;
import com.project.movie.cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    @Override
    public long registerCinema(RegisterCinemaDto registerCinemaDto) {
        try {
            Cinema cinema = registerCinemaDto.toEntity();
            Cinema savedCinema = cinemaRepository.save(cinema);
            return savedCinema.getCinemaId();
        } catch (Exception e) {
            e.printStackTrace();
            throw CinemaException.failRegisterException();
        }
    }
}

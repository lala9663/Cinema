package com.project.movie.cinema.service.impl;

import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.dto.request.UpdateCinemaDto;
import com.project.movie.cinema.entity.Cinema;
import com.project.movie.cinema.exception.CinemaException;
import com.project.movie.cinema.repository.CinemaRepository;
import com.project.movie.cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Override
    public List<Cinema> allCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        if (!cinemas.isEmpty()) {
            return cinemas;
        } else {
            throw CinemaException.failViewException();
        }
    }

    @Override
    public Cinema getCinemaById(long cinemaId) {
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(cinemaId);
        if (cinemaOptional.isPresent()) {
            Cinema cinema = cinemaOptional.get();
            return cinema;
        } else {
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }
    }

    @Override
    public long updateCinema(long cinemaId, UpdateCinemaDto updateCinemaDto) {
        Optional<Cinema> cinemaToUpdateOptional = cinemaRepository.findById(cinemaId);

        if (cinemaToUpdateOptional.isPresent()) {
            Cinema cinemaToUpdate = cinemaToUpdateOptional.get()
                    .toBuilder()
                    .cinemaName(updateCinemaDto.getCinemaName())
                    .cinemaAddress(updateCinemaDto.getCinemaAddress())
                    .cinemaTheater(updateCinemaDto.getCinemaTheater())
                    .cinemaParking(updateCinemaDto.isCinemaParking())
                    .build();

            cinemaRepository.save(cinemaToUpdate);

            return cinemaId;
        } else {
            throw CinemaException.failViewException();
        }
    }


}

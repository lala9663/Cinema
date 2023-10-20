package com.project.cinema.cinema.service.impl;

import com.project.cinema.cinema.dto.request.RegisterCinemaDto;
import com.project.cinema.cinema.dto.request.UpdateCinemaDto;
import com.project.cinema.cinema.dto.response.CinemaDto;
import com.project.cinema.cinema.entity.Cinema;
import com.project.cinema.cinema.exception.CinemaException;
import com.project.cinema.cinema.repository.CinemaRepository;
import com.project.cinema.cinema.repository.MovieTimeRepository;
import com.project.cinema.cinema.repository.ScreenRepository;
import com.project.cinema.cinema.service.CinemaService;
import com.project.cinema.movie.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    private final ScreenRepository screenRepository;
    private final MovieRepository movieRepository;
    private final MovieTimeRepository movieTimeRepository;
    @Override
    @Transactional
    public long registerCinema(RegisterCinemaDto registerCinemaDto) {

        if (registerCinemaDto == null) {
            throw CinemaException.nullFieldException();
        }

        try {
            boolean isDuplicate = cinemaRepository.existsByCinemaNameAndCinemaAddress(
                    registerCinemaDto.getCinemaName(), registerCinemaDto.getCinemaAddress()
            );

            // 중복 검사 로직
            if (isDuplicate) {
                throw CinemaException.duplicateCinemaException();
            }

            Cinema cinema = registerCinemaDto.toEntity();
            Cinema savedCinema = cinemaRepository.save(cinema);

            return savedCinema.getCinemaId();
        } catch (Exception e) {
            throw CinemaException.failRegisterException();
        }
    }

    @Override
    public List<CinemaDto> getAllCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        if (!cinemas.isEmpty()) {
            return cinemas.stream()
                    .map(CinemaDto::fromEntity)
                    .collect(Collectors.toList());
        } else {
            throw CinemaException.failViewException();
        }
    }

    @Override
    public CinemaDto getCinemaById(Long cinemaId) {
        Cinema cinema = cinemaRepository.findByCinemaId(cinemaId);
        if (cinema != null) {
            return CinemaDto.fromEntity(cinema);
        } else{
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }
    }

    @Override
    @Transactional
    public long updateCinema(Long cinemaId, UpdateCinemaDto updateCinemaDto) {
        Cinema cinema = cinemaRepository.findByCinemaId(cinemaId);

        if (cinema == null) {
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }

        // 새로운 영화관명
        String newCinemaName = updateCinemaDto.getCinemaName();
        String newCinemaAddress = updateCinemaDto.getCinemaAddress();

        // 기존 영화관명과 새로운 영화관명이 같으면 업데이트 방지
        if (!cinema.getCinemaName().equals(newCinemaName) || !cinema.getCinemaAddress().equals(newCinemaAddress)) {
            // 중복 검사
            boolean isDuplicate = cinemaRepository.existsByCinemaNameAndCinemaAddress(
                    newCinemaName, newCinemaAddress
            );
            if (isDuplicate) {
                throw CinemaException.duplicateCinemaException();
            }
        }

        Cinema updateCinema = updateCinemaDto.toEntity();
        cinema.updateForm(updateCinema);
        cinemaRepository.save(cinema);

        return cinema.getCinemaId();
    }



    @Override
    @Transactional
    public void deleteCinema(Long cinemaId) {
        Cinema cinema = cinemaRepository.findByCinemaId(cinemaId);
        if (cinema != null) {
            cinemaRepository.delete(cinema);
        } else {
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }
    }
}

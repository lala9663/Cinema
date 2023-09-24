package com.project.movie.cinema.service.impl;

import com.project.movie.cinema.dto.request.DeleteCinemaDto;
import com.project.movie.cinema.dto.request.RegisterCinemaDto;
import com.project.movie.cinema.dto.request.UpdateCinemaDto;
import com.project.movie.cinema.entity.Cinema;
import com.project.movie.cinema.exception.CinemaException;
import com.project.movie.cinema.repository.CinemaRepository;
import com.project.movie.cinema.service.CinemaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CinemaServiceImpl implements CinemaService {
    private final CinemaRepository cinemaRepository;
    @Override
    public long registerCinema(RegisterCinemaDto registerCinemaDto) {
        try {
            boolean isDuplicate = cinemaRepository.existsByCinemaNameAndCinemaAddress(
                    registerCinemaDto.getCinemaName(), registerCinemaDto.getCinemaAddress()
            );

            // 중복 검사 로직
            if (isDuplicate) {
                throw CinemaException.duplicateCinemaException();
            }

            Cinema cinema = registerCinemaDto.toEntity();
            Cinema savedCinema;
            try {
                savedCinema = cinemaRepository.save(cinema);
            } catch (Exception e) {
                log.error("영화관 등록 중 오류 발생: {}", e.getMessage());
                throw CinemaException.cinemaRegisterException();
            }

            return savedCinema.getCinemaId();

        } catch (Exception e) {
            log.error("영화관 등록 실패: {}", e.getMessage());
            // 등록 실패
            throw CinemaException.failRegisterException();
        }
    }

    @Override
    public List<Cinema> allCinemas() {
        List<Cinema> cinemas = cinemaRepository.findAll();
        if (!cinemas.isEmpty()) {
            return cinemas;
        } else {
            // 단순 조회 실패
            throw CinemaException.failViewException();
        }
    }

    @Override
    public Cinema getCinemaById(long cinemaId) {
        Optional<Cinema> cinemaOptional = cinemaRepository.findById(cinemaId);

        // 영화관 조회시 해당 영화관 없을 때
        return cinemaOptional.orElseThrow(() -> CinemaException.cinemaNotFoundException(cinemaId));
    }


    @Override
    public long updateCinema(long cinemaId, UpdateCinemaDto updateCinemaDto) {
        Optional<Cinema> cinemaToUpdateOptional = cinemaRepository.findById(cinemaId);

        if (cinemaToUpdateOptional.isPresent()) {
            Cinema cinemaToUpdate = cinemaToUpdateOptional.get();
            String newCinemaName = updateCinemaDto.getCinemaName();
            String newCinemaAddress = updateCinemaDto.getCinemaAddress();

            // 중복 검사
            boolean isDuplicate = cinemaRepository.existsByCinemaNameAndCinemaAddress(newCinemaName, newCinemaAddress);

            if (isDuplicate) {
                // 중복된 영화관이 있을 때
                throw CinemaException.duplicateCinemaException();
            }

            cinemaToUpdate = cinemaToUpdate.toBuilder()
                    .cinemaName(newCinemaName)
                    .cinemaAddress(newCinemaAddress)
                    .cinemaTheater(updateCinemaDto.getCinemaTheater())
                    .cinemaParking(updateCinemaDto.isCinemaParking())
                    .build();

            try {
                cinemaRepository.save(cinemaToUpdate);
            } catch (Exception e) {
                // 영화관 수정 중 예외 발생 처리
                throw CinemaException.cinemaUpdateException(cinemaId);
            }

            return cinemaId;
        } else {
            // 해당 영화관이 없을 때
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }
    }



    @Override
    public void deleteCinema(DeleteCinemaDto deleteCinemaDto) {
        Long cinemaId = deleteCinemaDto.getCinemaId();
        Optional<Cinema> optionalCinema = cinemaRepository.findById(cinemaId);

        if (optionalCinema.isPresent()) {
            Cinema cinema = optionalCinema.get();
            try {
                cinemaRepository.delete(cinema);
            } catch (Exception e) {
                // 영화관 삭제 중 예외 발생 처리
                throw CinemaException.cinemaDeletionException(cinemaId);
            }
        } else {
            // 해당 아이디의 영화관을 찾을 수 없을 때
            throw CinemaException.cinemaNotFoundException(cinemaId);
        }
    }


}

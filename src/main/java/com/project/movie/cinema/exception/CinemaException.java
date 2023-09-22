package com.project.movie.cinema.exception;

import com.project.movie.movie.exception.MovieException;

public class CinemaException extends RuntimeException {
    public CinemaException(String message) {
        super(message);
    }

    public static CinemaException failRegisterException() {
        return new CinemaException("영화관 등록에 실패했습니다.");
    }

    public static CinemaException failViewException() {
        return new CinemaException("등록된 영화관이 없습니다.");
    }
    public static CinemaException cinemaNotFoundException(long cinemaId) {
        return new CinemaException("해당 영화관 " + cinemaId + " 는 찾을 수 없습니다.");
    }
}

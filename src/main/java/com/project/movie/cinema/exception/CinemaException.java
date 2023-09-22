package com.project.movie.cinema.exception;

public class CinemaException extends RuntimeException {
    public CinemaException(String message) {
        super(message);
    }

    public static CinemaException failRegisterException() {
        return new CinemaException("영화관 등록에 실패했습니다.");
    }
}

package com.project.cinema.cinema.exception;

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
    public static CinemaException duplicateCinemaException() {
        return new CinemaException("이미 등록된 영화관입니다.");
    }
    public static CinemaException cinemaDeletionException(long cinemaId) {
        return new CinemaException("영화관 삭제 중 오류가 발생했습니다. Cinema ID: " + cinemaId);
    }
    public static CinemaException cinemaUpdateException(long cinemaId) {
        return new CinemaException("영화관 수정 중 오류가 발생했습니다. Cinema ID: " + cinemaId);
    }
    public static CinemaException cinemaRegisterException() {
        return new CinemaException("영화관 생성 중 오류가 발생했습니다. Cinema ID: ");
    }

    public static CinemaException nullFieldException() {
        return new CinemaException("필드 값을 입력해주세요");
    }

    public static Exception invalidTheaterTypeException() {
        return new CinemaException("해당 영화관에는 존재하지 않습니다.");
    }
}

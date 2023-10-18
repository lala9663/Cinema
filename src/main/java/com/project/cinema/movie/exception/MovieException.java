package com.project.cinema.movie.exception;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }
    public static MovieException failRegisterException() {
        return new MovieException("영화등록에 실패했습니다.");
    }

    public static MovieException movieAlreadyDeletedException(long movieId) {
        return new MovieException("해당 영화 " + movieId + " 이미 삭제된 데이터입니다.");
    }

    public static MovieException movieNotFoundException(long movieId) {
        return new MovieException("해당 영화 " + movieId + " 는 찾을 수 없습니다.");
    }

    public static MovieException nullFieldException() {
        return new MovieException("필드값은 꼭 입력해야 합니다");
    }

}

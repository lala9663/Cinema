package com.project.movie.movie.exception;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }

    public static MovieException deleteException(long movieId){
        return new MovieException("해당 영화 ID " + movieId + " 는 존재하지 않습니다.");
    }

}

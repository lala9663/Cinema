package com.project.movie.movie.exception;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }
    public static MovieException failRegisterException() {
        return new MovieException("Failed to register the movie");
    }


    public static MovieException movieAlreadyDeletedException(long movieId) {
        return new MovieException("Movie with ID " + movieId + " is already deleted");
    }

    public static MovieException movieNotFoundException(long movieId) {
        return new MovieException("Movie with ID " + movieId + " not found");
    }

}

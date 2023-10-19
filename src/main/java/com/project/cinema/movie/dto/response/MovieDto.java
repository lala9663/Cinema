package com.project.cinema.movie.dto.response;

import com.project.cinema.movie.entity.Movie;
import com.project.cinema.movie.entity.MovieGenre;
import com.project.cinema.movie.entity.MovieRate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    private String movieTitle;
    private MovieGenre movieGenre;
    private MovieRate movieRate;

    public static MovieDto fromEntity(Movie movie) {
        return MovieDto.builder()
                .movieTitle(movie.getMovieTitle())
                .movieGenre(movie.getMovieGenre())
                .movieRate(movie.getMovieRate())
                .build();
    }
}
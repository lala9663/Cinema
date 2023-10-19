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
public class MovieDetailDto {
    private String movieTitle;
    private MovieGenre movieGenre;
    private MovieRate movieRate;
    private String movieDirector;
    private String movieActor;
    private String movieRunningTime;
    private String movieContent;
    private String movieReleaseDate;

    public static MovieDetailDto fromEntity(Movie movie) {
        return MovieDetailDto.builder()
                .movieTitle(movie.getMovieTitle())
                .movieGenre(movie.getMovieGenre())
                .movieRate(movie.getMovieRate())
                .movieDirector(movie.getMovieDirector())
                .movieActor(movie.getMovieActor())
                .movieRunningTime(movie.getMovieRunningTime())
                .movieContent(movie.getMovieContent())
                .movieReleaseDate(movie.getMovieReleaseDate())
                .build();
    }
}
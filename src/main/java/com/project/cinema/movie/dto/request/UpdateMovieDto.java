package com.project.cinema.movie.dto.request;

import com.project.cinema.movie.entity.Movie;
import com.project.cinema.movie.entity.MovieGenre;
import com.project.cinema.movie.entity.MovieRate;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieDto {
    private String movieTitle;
    private MovieGenre movieGenre;
    private MovieRate movieRate;
    private String movieDirector;
    private String movieActor;
    private String movieRunningTime;
    private String movieContent;
    private String movieReleaseDate;

    public Movie toEntity() {
        return Movie.builder()
                .movieTitle(this.movieTitle)
                .movieGenre(this.movieGenre)
                .movieRate(this.movieRate)
                .movieDirector(this.movieDirector)
                .movieActor(this.movieActor)
                .movieRunningTime(this.movieRunningTime)
                .movieContent(this.movieContent)
                .movieReleaseDate(this.movieReleaseDate)
                .build();
    }
}

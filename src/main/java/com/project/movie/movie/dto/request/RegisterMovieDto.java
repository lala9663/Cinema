package com.project.movie.movie.dto.request;

import com.project.movie.movie.entity.Movie;
import com.project.movie.movie.entity.MovieGenre;
import com.project.movie.movie.entity.MovieRate;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RegisterMovieDto {
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

    @Builder
    public RegisterMovieDto(String movieTitle, MovieGenre movieGenre, MovieRate movieRate, String movieDirector, String movieActor,
                            String movieRunningTime, String movieContent, String movieReleaseDate) {
        this.movieTitle = movieTitle;
        this.movieGenre = movieGenre;
        this.movieRate = movieRate;
        this.movieDirector = movieDirector;
        this.movieActor = movieActor;
        this.movieRunningTime = movieRunningTime;
        this.movieContent = movieContent;
        this.movieReleaseDate = movieReleaseDate;
    }

}

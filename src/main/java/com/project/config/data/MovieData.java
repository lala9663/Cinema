package com.project.config.data;


import com.project.cinema.movie.entity.Movie;
import com.project.cinema.movie.entity.MovieGenre;
import com.project.cinema.movie.entity.MovieRate;
import com.project.cinema.movie.repository.MovieRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class MovieData {

    private final MovieRepository movieRepository;

    public MovieData(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    public void init() {
        Movie movie1 = Movie.builder()
                .movieTitle("영화 제목 1")
                .movieGenre(MovieGenre.액션)
                .movieRate(MovieRate.NC_17)
                .movieDirector("감독 1")
                .movieActor("주연 배우 1")
                .movieRunningTime("120 분")
                .movieContent("영화 내용 1")
                .movieReleaseDate("2023-10-18")
                .build();

        Movie movie2 = Movie.builder()
                .movieTitle("영화 제목 2")
                .movieGenre(MovieGenre.스릴러)
                .movieRate(MovieRate.R)
                .movieDirector("감독 2")
                .movieActor("주연 배우 2")
                .movieRunningTime("130 분")
                .movieContent("영화 내용 2")
                .movieReleaseDate("2023-10-19")
                .build();

        Movie movie3 = Movie.builder()
                .movieTitle("영화 제목 3")
                .movieGenre(MovieGenre.코미디)
                .movieRate(MovieRate.PG)
                .movieDirector("감독 3")
                .movieActor("주연 배우 3")
                .movieRunningTime("110 분")
                .movieContent("영화 내용 3")
                .movieReleaseDate("2023-10-20")
                .build();

        movieRepository.saveAll(List.of(movie1, movie2, movie3));
    }
}

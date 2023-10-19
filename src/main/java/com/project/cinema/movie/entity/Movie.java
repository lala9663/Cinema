package com.project.cinema.movie.entity;

import com.project.cinema.cinema.entity.MovieTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "movie_title", nullable = false)
    private String movieTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_genre", nullable = false)
    private MovieGenre movieGenre;

    @Enumerated(EnumType.STRING)
    @Column(name = "movie_rate", nullable = false)
    private MovieRate movieRate;

    @Column(name = "movie_director", nullable = false)
    private String movieDirector;

    @Column(name = "movie_acter", nullable = false)
    private String movieActor;

    @Column(name = "movie_running_time", nullable = false)
    private String movieRunningTime;

    @Column(name = "movie_content", nullable = false, columnDefinition = "TEXT")
    private String movieContent;

    @Column(name = "movie_release_date", nullable = false)
    private String movieReleaseDate;
    @Column(name = "movie_deleted", nullable = false)
    private boolean movieDeleted;
    @OneToMany(mappedBy = "movie")
    private List<MovieTime> movieTimes = new ArrayList<>();

    public void markAsDeleted() {
        this.movieDeleted = true;
    }
    public void updateFrom(Movie updateMovie) {
        if (updateMovie.getMovieTitle() != null) {
            this.movieTitle = updateMovie.getMovieTitle();
        }
        if (updateMovie.getMovieGenre() != null) {
            this.movieGenre = updateMovie.getMovieGenre();
        }
        if (updateMovie.getMovieRate() != null) {
            this.movieRate = updateMovie.getMovieRate();
        }
        if (updateMovie.getMovieDirector() != null) {
            this.movieDirector = updateMovie.getMovieDirector();
        }
        if (updateMovie.getMovieActor() != null) {
            this.movieActor = updateMovie.getMovieActor();
        }
        if (updateMovie.getMovieRunningTime() != null) {
            this.movieRunningTime = updateMovie.getMovieRunningTime();
        }
        if (updateMovie.getMovieContent() != null) {
            this.movieContent = updateMovie.getMovieContent();
        }
        if (updateMovie.getMovieReleaseDate() != null) {
            this.movieReleaseDate = updateMovie.getMovieReleaseDate();
        }
    }
}

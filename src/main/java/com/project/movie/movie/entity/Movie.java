package com.project.movie.movie.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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

}

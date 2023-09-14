package com.project.movie.cinema.entity;

import com.project.movie.movie.entity.Movie;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "movie_time")
@Data
public class MovieTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long timeId;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema_id", nullable = false)
    private Cinema cinema;

    @ManyToOne
    @JoinColumn(name = "movie_id", referencedColumnName = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "movie_start", nullable = false)
    private LocalDateTime movieStart;

    @Column(name = "movie_round", nullable = false)
    private int movieRound;

}

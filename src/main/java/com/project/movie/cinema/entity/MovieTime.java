package com.project.movie.cinema.entity;

import com.project.movie.member.entity.MovieTicket;
import com.project.movie.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "movie_time")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MovieTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_id")
    private Long timeId;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @Column(name = "movie_start")
    private LocalDateTime movieStart;

    @Column(name = "movie_round")
    private int movieRound;

    @Column(name = "start_at")
    private LocalDateTime startAt;

    @Column(name = "end_at")
    private LocalDateTime endAt;
    @OneToMany(mappedBy = "movieTime")
    private List<MovieTicket> movieTickets;

}

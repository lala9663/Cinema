package com.project.movie.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Cinema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cinema_id")
    private Long cinemaId;

    @Column(name = "cinema_name", nullable = false)
    private String cinemaName;

    @Column(name = "cinema_addr", nullable = false)
    private String cinemaAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "cinema_theater", nullable = false)
    private TheaterType cinemaTheater;

    @Column(name = "cinema_parking", nullable = false)
    private boolean cinemaParking;

    public CinemaBuilder toBuilder() {
        return builder()
                .cinemaId(cinemaId)
                .cinemaName(cinemaName)
                .cinemaAddress(cinemaAddress)
                .cinemaTheater(cinemaTheater)
                .cinemaParking(cinemaParking);
    }

}

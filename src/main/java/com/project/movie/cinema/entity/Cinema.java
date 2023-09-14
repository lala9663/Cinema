package com.project.movie.cinema.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cinema")
@Data
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

}

package com.project.movie.cinema.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "seat")
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "cinema_id", referencedColumnName = "cinema_id", nullable = false)
    private Cinema cinema;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @Column(name = "seat_reserve", nullable = false)
    private boolean seatReserved;

}

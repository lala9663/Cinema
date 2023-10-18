package com.project.movie.cinema.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id")
    private Long seatId;

    @ManyToOne
    @JoinColumn(name = "screen_id")
    private Screen screen;

    @Column(name = "seat_number", nullable = false)
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "seat_Type_id")
    private SeatType seatType;

    @Column(name = "purchased")
    private boolean purchased;

}

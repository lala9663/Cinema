package com.project.movie.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "price")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private Long priceId;

    @ManyToOne
    @JoinColumn(name = "seat_type_id")
    private SeatType seatType;
    @ManyToOne
    @JoinColumn(name = "screen_type_id")
    private ScreenType screenType;

    @Column(name = "seat_price", nullable = false)
    private int seatPrice;
}

package com.project.movie.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "cinema_address", nullable = false)
    private String cinemaAddress;

    @Column(name = "cinema_parking", nullable = false)
    private boolean cinemaParking;

    @OneToMany(mappedBy = "cinema")
    private List<Screen> screens;
    public CinemaBuilder toBuilder() {
        return builder()
                .cinemaId(cinemaId)
                .cinemaName(cinemaName)
                .cinemaAddress(cinemaAddress)
                .cinemaParking(cinemaParking);
    }

}

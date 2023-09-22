package com.project.movie.cinema.dto.request;

import com.project.movie.cinema.entity.Cinema;
import com.project.movie.cinema.entity.TheaterType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UpdateCinemaDto {
    private String cinemaName;
    private String cinemaAddress;
    private TheaterType cinemaTheater;
    private boolean cinemaParking;

    public Cinema toEntity() {
        return Cinema.builder()
                .cinemaName(this.cinemaName)
                .cinemaAddress(this.cinemaAddress)
                .cinemaTheater(this.cinemaTheater)
                .cinemaParking(this.cinemaParking)
                .build();
    }
}

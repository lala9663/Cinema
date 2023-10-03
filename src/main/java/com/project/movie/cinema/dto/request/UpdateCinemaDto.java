package com.project.movie.cinema.dto.request;

import com.project.movie.cinema.entity.Cinema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class UpdateCinemaDto {
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaScreen;
    private boolean cinemaParking;

    public Cinema toEntity() {
        return Cinema.builder()
                .cinemaName(this.cinemaName)
                .cinemaAddress(this.cinemaAddress)
                .cinemaParking(this.cinemaParking)
                .build();
    }
}

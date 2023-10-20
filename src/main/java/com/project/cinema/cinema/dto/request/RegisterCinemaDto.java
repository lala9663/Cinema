package com.project.cinema.cinema.dto.request;

import com.project.cinema.cinema.entity.Cinema;
import com.project.cinema.cinema.entity.ScreenType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class RegisterCinemaDto {
    private String cinemaName;
    private String cinemaAddress;
    private boolean cinemaParking;
    public Cinema toEntity() {
        return Cinema.builder()
                .cinemaName(this.cinemaName)
                .cinemaAddress(this.cinemaAddress)
                .cinemaParking(this.cinemaParking)
                .build();
    }
}

package com.project.cinema.cinema.dto.response;


import com.project.cinema.cinema.entity.Cinema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CinemaDto {
    private String cinemaName;
    private String cinemaAddress;
    private boolean cinemaParking;
    public static CinemaDto fromEntity(Cinema cinema) {
        return CinemaDto.builder()
                .cinemaName(cinema.getCinemaName())
                .cinemaAddress(cinema.getCinemaName())
                .cinemaParking(cinema.isCinemaParking())
                .build();
    }

}

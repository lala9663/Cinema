package com.project.movie.cinema.dto.request;

import com.project.movie.cinema.entity.Cinema;
import com.project.movie.cinema.entity.MovieTime;
import com.project.movie.cinema.entity.TheaterType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegisterMovieTimeDto {
    private LocalDateTime movieStart;
    private LocalDateTime movieEnd;
    private TheaterType theaterType;

    public MovieTime toEntity() {
        return MovieTime.builder()
                .movieStart(this.movieStart)
                .movieEnd(this.movieEnd)
                .theaterType(this.theaterType)
                .build();
    }
}

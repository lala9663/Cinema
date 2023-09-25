package com.project.movie.cinema.dto.request;

import com.project.movie.cinema.entity.MovieTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegisterMovieTimeDto {
    private LocalDateTime movieStart;
    private LocalDateTime movieEnd;

    public MovieTime toEntity() {
        return MovieTime.builder()
                .movieStart(this.movieStart)
                .movieEnd(this.movieEnd)
                .build();
    }
}

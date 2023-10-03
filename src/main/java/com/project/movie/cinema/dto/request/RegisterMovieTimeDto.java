package com.project.movie.cinema.dto.request;

import com.project.movie.cinema.entity.MovieTime;
import com.project.movie.cinema.entity.Screen;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RegisterMovieTimeDto {
    private LocalDateTime movieStart;
    private LocalDateTime movieEnd;
    private Screen screen;

    public MovieTime toEntity() {
        return MovieTime.builder()
                .startAt(this.movieStart)
                .endAt(this.movieEnd)
                .screen(this.screen)
                .build();
    }
}

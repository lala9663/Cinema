package com.project.movie.cinema.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "screen")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "screen_id")
    private Long screenId;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @Column(name = "screen_name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "screen_type", nullable = false)
    private ScreenType screenType;

    @OneToMany(mappedBy = "screen")
    private List<Seat> seats;
    @OneToMany(mappedBy = "screen")
    private List<MovieTime> movieTimes;
}

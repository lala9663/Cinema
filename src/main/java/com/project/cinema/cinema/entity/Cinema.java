package com.project.cinema.cinema.entity;

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

    public void updateForm(Cinema updateCinema) {
        if (updateCinema.getCinemaName() != null) {
            this.cinemaName = updateCinema.getCinemaName();
        }
        if (updateCinema.getCinemaAddress() != null) {
            this.cinemaAddress = updateCinema.getCinemaAddress();
        }
        if (updateCinema.isCinemaParking()) {
            this.cinemaParking = true;
        }
    }

}

package com.project.cinema.cinema.repository;

import com.project.cinema.cinema.entity.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Long> {
    boolean existsByCinemaNameAndCinemaAddress(String cinemaName, String cinemaAddress);

}

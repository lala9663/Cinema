package com.project.movie.cinema.repository;

import com.project.movie.cinema.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScreenRepository extends JpaRepository<Screen, Long> {
}

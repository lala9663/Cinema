package com.project.cinema.cinema.repository;

import com.project.cinema.cinema.entity.MovieTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieTimeRepository extends JpaRepository<MovieTime, Long> {

}

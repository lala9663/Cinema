package com.project.movie.movie.service;

import com.project.movie.movie.dto.request.RegisterMovieDto;

public interface MovieService {
    Long registerMovie(RegisterMovieDto registerMovieDto);

}

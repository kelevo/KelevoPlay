package com.kelevo.play.domain.repository;

import com.kelevo.play.domain.dto.MovieDTO;
import com.kelevo.play.domain.dto.UpdateMovieDTO;

import java.util.List;

public interface MovieRepository {

    List<MovieDTO> getAll();
    MovieDTO getById(Long id);
    MovieDTO save(MovieDTO movieDto);
    MovieDTO update(Long id, UpdateMovieDTO updateMovieDTO);
    void delete(Long id);

}

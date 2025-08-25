package com.kelevo.play.domain.service;

import com.kelevo.play.domain.dto.MovieDTO;
import com.kelevo.play.domain.dto.UpdateMovieDTO;
import com.kelevo.play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Tool("Busca todas las peliculas que existan dentro de la plataforma")
    public List<MovieDTO> getAll() {
        return this.movieRepository.getAll();
    }

    public MovieDTO getById(Long id) {
        return this.movieRepository.getById(id);
    }

    public MovieDTO add(MovieDTO movieDto) {
        return this.movieRepository.save(movieDto);
    }

    public MovieDTO update(long id, UpdateMovieDTO updateMovieDTO) {
        return this.movieRepository.update(id, updateMovieDTO);
    }

    public void delete(Long id) {
        movieRepository.delete(id);
    }

}

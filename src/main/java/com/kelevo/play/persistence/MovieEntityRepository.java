package com.kelevo.play.persistence;

import com.kelevo.play.domain.dto.MovieDTO;
import com.kelevo.play.domain.dto.UpdateMovieDTO;
import com.kelevo.play.domain.exceptions.MovieAlreadyExistsException;
import com.kelevo.play.domain.exceptions.MovieNotExistsException;
import com.kelevo.play.domain.repository.MovieRepository;
import com.kelevo.play.persistence.crud.CrudMovieEntity;
import com.kelevo.play.persistence.entity.MovieEntity;
import com.kelevo.play.persistence.mapper.MovieMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {

    @Autowired
    private CrudMovieEntity crudMovieEntity;

    @Autowired
    private MovieMapper movieMapper;

    public List<MovieDTO> getAll() {
        return this.movieMapper.toDto(this.crudMovieEntity.findAll());
    }

    @Override
    public MovieDTO getById(Long id) {

        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);

        return this.movieMapper.toDto(movieEntity);
    }

    @Override
    public MovieDTO save(MovieDTO movieDto) {

        if (this.crudMovieEntity.findFirstByTitulo(movieDto.title()) != null) {
            throw new MovieAlreadyExistsException(movieDto.title());
        }

        MovieEntity movieEntity = this.movieMapper.toEntity(movieDto);
        movieEntity.setEstado("D");
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));

    }

    @Override
    public MovieDTO update(Long id, UpdateMovieDTO updateMovieDTO) {
        if (!crudMovieEntity.existsById(id)) throw new MovieNotExistsException(id);
        MovieEntity movieEntity = this.crudMovieEntity.findById(id).orElse(null);
        this.movieMapper.updateEntityFromDto(updateMovieDTO, movieEntity);
        return this.movieMapper.toDto(this.crudMovieEntity.save(movieEntity));
    }

    @Override
    public void delete(Long id) {
        if (!crudMovieEntity.existsById(id)) throw new MovieNotExistsException(id);
        crudMovieEntity.deleteById(id);
    }


}

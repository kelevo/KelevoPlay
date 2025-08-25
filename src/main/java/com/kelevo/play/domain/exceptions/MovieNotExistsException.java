package com.kelevo.play.domain.exceptions;

public class MovieNotExistsException extends RuntimeException {

    public MovieNotExistsException(Long movieId) {
        super("Movie with id '" + movieId + "' don`t exists");
    }

}

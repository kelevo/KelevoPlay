package com.kelevo.play.domain.exceptions;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException(String movieTitle) {
        super("Movie with title '" + movieTitle + "' already exists");
    }

}

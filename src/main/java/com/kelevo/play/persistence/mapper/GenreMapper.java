package com.kelevo.play.persistence.mapper;

import com.kelevo.play.domain.Genre;
import org.mapstruct.Named;

public class GenreMapper {

    @Named("stringToGenre")
    public static Genre stringToGenre(String genre) {

        if (genre == null) return null;

        return switch (genre.toUpperCase()) {
            case "ACCION"  -> Genre.ACTION;
            case "COMEDIA" -> Genre.COMEDY;
            case "DRAMA"   -> Genre.DRAMA;
            case "ANIMADA" -> Genre.ANIMATED;
            case "TERROR"  -> Genre.HORROR;
            case "CIENCIA_FICCION"  -> Genre.SCIENCE_FICTION;
            default        -> throw new IllegalArgumentException("Género no soportado: " + genre);
        };

    }

    @Named("genreToString")
    public static String genreToString(Genre genre) {

        if (genre == null) return null;

        return switch (genre) {
            case ACTION          -> "ACCION";
            case COMEDY          -> "COMEDIA";
            case DRAMA           -> "DRAMA";
            case ANIMATED        -> "ANIMADA";
            case HORROR          -> "TERROR";
            case SCIENCE_FICTION -> "SCI_FI";
            default              -> throw new IllegalArgumentException("Nombre de genero no soportado: " + genre);
        };

    }

}

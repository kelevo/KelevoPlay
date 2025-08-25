package com.kelevo.play.persistence.mapper;

import com.kelevo.play.domain.Genre;
import com.kelevo.play.domain.State;
import org.mapstruct.Named;

public class StateMapper {

    @Named("stringToState")
    public static State stringToState(String state) {

        if (state == null) return null;

        return switch (state.toUpperCase()) {
            case "D"  -> State.AVAILABLE;
            case "N"  -> State.NOT_AVAILABLE;
            default   -> throw new IllegalArgumentException("Estado no soportado: " + state);
        };

    }

    @Named("stateToString")
    public static String genreToString(State state) {

        if (state == null) return null;

        return switch (state) {
            case AVAILABLE     -> "D";
            case NOT_AVAILABLE -> "N";
            default            -> throw new IllegalArgumentException("Tipo de estado no soportado: " + state);
        };

    }

}

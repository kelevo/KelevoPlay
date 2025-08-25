package com.kelevo.play.domain.dto;

import com.kelevo.play.domain.Genre;

import java.time.LocalDate;

public record MovieDTO(
        Long id,
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double rating,
        String state
) {
}

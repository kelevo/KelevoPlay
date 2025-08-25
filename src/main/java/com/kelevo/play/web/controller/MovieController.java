package com.kelevo.play.web.controller;

import com.kelevo.play.domain.dto.MovieDTO;
import com.kelevo.play.domain.dto.SuggestRequestDTO;
import com.kelevo.play.domain.dto.UpdateMovieDTO;
import com.kelevo.play.domain.service.KelevoPlayAiService;
import com.kelevo.play.domain.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movies", description = "KelevoPlay API operations")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @Autowired
    private KelevoPlayAiService kelevoPlayAiService;

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll() {
        return ResponseEntity.ok(this.movieService.getAll());
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get movie by ID",
            description = "Returns the movie found by the ID provided by the user",
            responses = {
                @ApiResponse(responseCode = "200", description = "Movie found"),
                @ApiResponse(responseCode = "404", description = "Movie not found", content = @Content)
            },
            tags = {"Movies"}
    )
    public ResponseEntity<MovieDTO> getById(@Parameter(description = "Movie ID", example = "1", required = true) @PathVariable Long id) {
        MovieDTO movieDto = this.movieService.getById(id);
        if (movieDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(movieDto);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> add(@RequestBody MovieDTO movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.movieService.add(movieDto));
    }

    @PostMapping("/suggest")
    public ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDTO suggestRequestDTO) {
        return ResponseEntity.ok(this.kelevoPlayAiService.generateMoviesSuggestion(suggestRequestDTO.userPreferences()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody @Valid UpdateMovieDTO updateMovieDTO) {
        return ResponseEntity.ok(this.movieService.update(id, updateMovieDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

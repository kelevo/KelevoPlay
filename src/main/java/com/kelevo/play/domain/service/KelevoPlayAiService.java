package com.kelevo.play.domain.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface KelevoPlayAiService {

    @UserMessage(
            """
                Genera un saludo de beinvenida a la plataforma de Gestion de Peliculas
                Kelevo Play, usa menos de 120 caracteres y hazlo con un estilo
                formal pero no extremo tipo netflix
            """
    )
    String generateGreeting();

    @SystemMessage(
            """
                Eres un experto en cine que recomienda peliculas personalizadas segun los gustos del usuario.
                Debes recomendar maximo 3 peliculas.
                No incluyas peliculas que estan por fuera de la plataforma KelevoPlay.
            """
    )
    String generateMoviesSuggestion(@UserMessage String userMessage);

}

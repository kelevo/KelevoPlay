package com.kelevo.play.web.controller;

import com.kelevo.play.domain.service.KelevoPlayAiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final KelevoPlayAiService kelevoPlayAiService;
    public HelloController(KelevoPlayAiService kelevoPlayAiService) {
        this.kelevoPlayAiService = kelevoPlayAiService;
    }

    @GetMapping("/hello")
    public String hello() {
        return this.kelevoPlayAiService.generateGreeting();
    }

}

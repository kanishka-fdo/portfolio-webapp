package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.service.CVService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cv")
public class CVApiController {

    private final CVService cvService;

    public CVApiController(CVService cvService) {
        this.cvService = cvService;
    }

    @GetMapping("/current")
    public CV getCurrentCV() {
        return cvService.findActiveCV()
                .orElseGet(() -> cvService.findAll().stream()
                        .findFirst()
                        .orElse(null));
    }
}
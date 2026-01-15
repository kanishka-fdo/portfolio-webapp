package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.repository.CVRepository;
import com.kanishka.portfoliowebapp.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {

    private final ProjectRepository projectRepository;
    private final CVRepository cvRepository;

    public PublicController(ProjectRepository projectRepository, CVRepository cvRepository) {
        this.projectRepository = projectRepository;
        this.cvRepository = cvRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Get the active CV or the first CV available
        CV cv = cvRepository.findFirstByActiveTrue()
                .orElseGet(() -> cvRepository.findFirstByOrderByIdAsc().orElse(null));

        model.addAttribute("cv", cv);
        model.addAttribute("projects", projectRepository.findAll());
        model.addAttribute("featured", projectRepository.findByFeaturedTrue());
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        // Get the active CV or the first CV available
        CV cv = cvRepository.findFirstByActiveTrue()
                .orElseGet(() -> cvRepository.findFirstByOrderByIdAsc().orElse(null));

        model.addAttribute("cv", cv);
        return "about";
    }
}
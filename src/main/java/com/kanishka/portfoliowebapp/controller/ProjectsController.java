package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.Project;
import com.kanishka.portfoliowebapp.repository.ProjectRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProjectsController {

    private final ProjectRepository projectRepository;

    public ProjectsController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/projects")
    public String projectsPage(Model model) {
        List<Project> allProjects = projectRepository.findAll();
        model.addAttribute("projects", allProjects);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String projectDetail(@PathVariable Long id, Model model) {
        Project project = projectRepository.findById(id).orElse(null);
        model.addAttribute("project", project);
        return "project-detail";
    }
}
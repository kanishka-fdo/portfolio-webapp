package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.model.Project;
import com.kanishka.portfoliowebapp.service.CVService;
import com.kanishka.portfoliowebapp.service.ContactService;
import com.kanishka.portfoliowebapp.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final ProjectService projectService;
    private final ContactService contactService;
    private final CVService cvService;

    public AdminController(ProjectService projectService, ContactService contactService, CVService cvService) {
        this.projectService = projectService;
        this.contactService = contactService;
        this.cvService = cvService;
    }

    @GetMapping
    public String dashboard(Model model) {
        List<Project> projects = projectService.findAll();

        // Calculate statistics
        long featuredCount = projects.stream().filter(Project::isFeatured).count();
        int messageCount = contactService.findAll().size();
        int cvCount = cvService.findAll().size();

        // Get last update time
        LocalDateTime lastUpdate = projects.stream()
                .map(Project::getCreatedAt)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        // Get recent messages
        List<?> recentMessages = contactService.findAll().stream()
                .sorted((a, b) -> b.getCreatedAt().compareTo(a.getCreatedAt()))
                .limit(3)
                .collect(Collectors.toList());

        model.addAttribute("projects", projects);
        model.addAttribute("featuredCount", featuredCount);
        model.addAttribute("messageCount", messageCount);
        model.addAttribute("cvCount", cvCount);
        model.addAttribute("lastUpdate", lastUpdate);
        model.addAttribute("recentMessages", recentMessages);

        return "admin/dashboard";
    }

    @GetMapping("/projects/new")
    public String newProject(Model model) {
        model.addAttribute("project", new Project());
        return "admin/project-form";
    }

    @GetMapping("/projects/{id}/edit")
    public String editProject(@PathVariable Long id, Model model) {
        model.addAttribute("project", projectService.findById(id));
        return "admin/project-form";
    }

    @PostMapping("/projects/save")
    public String saveProject(@ModelAttribute Project project) {
        projectService.save(project);
        return "redirect:/admin?success=project";
    }

    @PostMapping("/projects/{id}/delete")
    public String deleteProject(@PathVariable Long id) {
        projectService.delete(id);
        return "redirect:/admin?success=delete";
    }

    @GetMapping("/messages")
    public String messages(Model model) {
        model.addAttribute("messages", contactService.findAll());
        return "admin/messages";
    }

    @PostMapping("/messages/{id}/delete")
    public String deleteMessage(@PathVariable Long id) {
        contactService.delete(id);
        return "redirect:/admin/messages?success=delete";
    }

    // CV Management
    @GetMapping("/cv")
    public String cvList(Model model) {
        model.addAttribute("cvList", cvService.findAll());
        return "admin/cv-list";
    }

    @GetMapping("/cv/new")
    public String newCV(Model model) {
        model.addAttribute("cv", new CV());
        return "admin/cv-form";
    }

    @GetMapping("/cv/{id}/edit")
    public String editCV(@PathVariable Long id, Model model) {
        model.addAttribute("cv", cvService.findById(id));
        return "admin/cv-form";
    }

    @PostMapping("/cv/save")
    public String saveCV(@ModelAttribute CV cv) {
        System.out.println("Saving CV with photo URL: " + cv.getProfilePhotoUrl());
        cvService.save(cv);
        return "redirect:/admin/cv?success=save";
    }

    @PostMapping("/cv/{id}/delete")
    public String deleteCV(@PathVariable Long id) {
        cvService.delete(id);
        return "redirect:/admin/cv?success=delete";
    }

    @PostMapping("/cv/{id}/activate")
    public String activateCV(@PathVariable Long id) {
        cvService.setActive(id);
        return "redirect:/admin/cv?success=activate";
    }

    // Profile Photo Upload Page
    @GetMapping("/profile-photo")
    public String profilePhoto() {
        return "admin/profile-photo";
    }

    // Test Upload Page
    @GetMapping("/test-upload")
    public String testUpload() {
        return "admin/test-upload";
    }

    // Quick Upload Page
    @GetMapping("/quick-upload")
    public String quickUpload() {
        return "admin/quick-upload";
    }
}
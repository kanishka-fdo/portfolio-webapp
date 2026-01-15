package com.kanishka.portfoliowebapp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ProjectForm {
    @NotBlank @Size(max = 200)
    public String title;

    @Size(max = 300)
    public String subtitle;

    @NotBlank
    public String description;

    @NotBlank @Size(max = 300)
    public String techStack;

    @Size(max = 500)
    public String githubUrl;

    @Size(max = 500)
    public String liveUrl;

    @Size(max = 500)
    public String imageUrl;

    public boolean featured;
}

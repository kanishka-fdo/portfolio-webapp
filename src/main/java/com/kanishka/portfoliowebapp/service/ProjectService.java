package com.kanishka.portfoliowebapp.service;

import com.kanishka.portfoliowebapp.model.Project;
import com.kanishka.portfoliowebapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) { this.repo = repo; }

    public List<Project> findAll() { return repo.findAll(); }
    public Project findById(Long id) { return repo.findById(id).orElseThrow(); }
    public Project save(Project p) { return repo.save(p); }
    public void delete(Long id) { repo.deleteById(id); }
}

package com.kanishka.portfoliowebapp.service;

import com.kanishka.portfoliowebapp.model.Project;
import com.kanishka.portfoliowebapp.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    public List<Project> findAll() {
        return repo.findAll();
    }

    public List<Project> findFeatured() {
        return (List<Project>) repo.findByFeaturedTrue();
    }

    public Project findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Transactional
    public Project save(Project p) {
        return repo.save(p);
    }

    @Transactional
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public long countAll() {
        return repo.count();
    }

    public long countFeatured() {
        return repo.countByFeaturedTrue();
    }
}
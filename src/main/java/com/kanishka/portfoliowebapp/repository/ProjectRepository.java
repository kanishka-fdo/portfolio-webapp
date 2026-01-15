package com.kanishka.portfoliowebapp.repository;

import com.kanishka.portfoliowebapp.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Object findByFeaturedTrue();
}

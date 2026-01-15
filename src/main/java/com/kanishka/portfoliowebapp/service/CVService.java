package com.kanishka.portfoliowebapp.service;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.repository.CVRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CVService {
    private final CVRepository repo;

    public CVService(CVRepository repo) {
        this.repo = repo;
    }

    public List<CV> findAll() {
        return repo.findAll();
    }

    public CV findById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public Optional<CV> findActiveCV() {
        return repo.findFirstByActiveTrue();  // Changed from findFirstByIsActiveTrue
    }

    public CV save(CV cv) {
        cv.setUpdatedAt(LocalDateTime.now());
        return repo.save(cv);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void setActive(Long id) {
        // Deactivate all CVs
        List<CV> allCVs = repo.findAll();
        allCVs.forEach(cv -> {
            cv.setActive(false);
            repo.save(cv);
        });

        // Activate the selected CV
        CV cv = findById(id);
        cv.setActive(true);
        repo.save(cv);
    }
}
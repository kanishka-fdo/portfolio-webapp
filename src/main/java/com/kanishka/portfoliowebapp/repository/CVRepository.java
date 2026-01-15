package com.kanishka.portfoliowebapp.repository;

import com.kanishka.portfoliowebapp.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CVRepository extends JpaRepository<CV, Long> {
    Optional<CV> findFirstByOrderByIdAsc();

    Optional<CV> findFirstByActiveTrue();  // Changed from findFirstByIsActiveTrue
}
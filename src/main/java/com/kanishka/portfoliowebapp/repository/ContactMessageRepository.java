package com.kanishka.portfoliowebapp.repository;

import com.kanishka.portfoliowebapp.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactMessageRepository extends JpaRepository<ContactMessage, Long> {}

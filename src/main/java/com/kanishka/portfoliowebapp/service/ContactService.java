package com.kanishka.portfoliowebapp.service;

import com.kanishka.portfoliowebapp.model.ContactMessage;
import com.kanishka.portfoliowebapp.repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactMessageRepository repo;

    public ContactService(ContactMessageRepository repo) {
        this.repo = repo;
    }

    public ContactMessage save(ContactMessage msg) {
        return repo.save(msg);
    }

    public List<ContactMessage> findAll() {
        return repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
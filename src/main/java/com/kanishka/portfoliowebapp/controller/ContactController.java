package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.ContactMessage;
import com.kanishka.portfoliowebapp.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ContactController {

    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String contactPage(Model model) {
        model.addAttribute("contactMessage", new ContactMessage());
        return "contact";
    }

    @PostMapping("/contact")
    public String submitContact(@ModelAttribute ContactMessage contactMessage,
                                RedirectAttributes redirectAttributes) {
        try {
            contactService.save(contactMessage);
            redirectAttributes.addFlashAttribute("success", "Thank you for your message! I'll get back to you soon.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Sorry, there was an error sending your message. Please try again.");
        }
        return "redirect:/contact";
    }
}
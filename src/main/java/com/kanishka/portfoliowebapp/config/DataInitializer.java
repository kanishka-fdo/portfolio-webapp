package com.kanishka.portfoliowebapp.config;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.model.Project;
import com.kanishka.portfoliowebapp.repository.CVRepository;
import com.kanishka.portfoliowebapp.repository.ProjectRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(CVRepository cvRepository, ProjectRepository projectRepository) {
        return args -> {
            if (cvRepository.count() == 0) {
                CV cv = new CV();
                cv.setFullName("Kanishka Fernando");
                cv.setTitle("Full-Stack Developer & Software Engineer");
                cv.setEmail("kanishkaf18@gmail.com");
                cv.setPhone("+94 70 460 6206");
                cv.setLocation("Colombo, Sri Lanka");
                cv.setGithubUrl("https://github.com/kanishka-fdo");
                cv.setLinkedinUrl("https://linkedin.com");
                cv.setWebsiteUrl("https://github.com/kanishka-fdo/portfolio-webapp");
                cv.setSummary("Information Systems Engineering undergraduate at SLIIT. Passionate about crafting resilient backend microservices, modern responsive interfaces, and high-impact digital experiences.");
                cv.setSkills("Java, Spring Boot, React, SQL Server, PostgreSQL, HTML/CSS, JavaScript, Git, REST APIs, Microservices");
                cv.setEducation("BSc (Hons) in Information Systems Engineering - Sri Lanka Institute of Information Technology (SLIIT)");
                cv.setExperience("Full-Stack Developer with expertise in Spring Boot, responsive UI/UX, and cloud deployments.");
                cv.setProfilePhotoUrl("/uploads/profile_1768827599286_3da6867b.jpeg");
                cv.setActive(true);
                cv.setCreatedAt(LocalDateTime.now());
                cv.setUpdatedAt(LocalDateTime.now());
                cvRepository.save(cv);
            }

            if (projectRepository.count() == 0) {
                Project p1 = new Project();
                p1.setTitle("Enterprise Portfolio Web Application");
                p1.setSubtitle("Modern Full-Stack Showcase");
                p1.setShortDescription("Production-grade personal portfolio built with Spring Boot, Thymeleaf, and pure luxury monochrome design system.");
                p1.setDescription("Enterprise-grade architecture featuring Spring Security, JPA/Hibernate, Flyway migrations, and dynamic luxury dark UI with high-contrast typography and fluid micro-interactions.");
                p1.setTechStack("Java, Spring Boot, PostgreSQL, Thymeleaf, Bootstrap 5");
                p1.setGithubUrl("https://github.com/kanishka-fdo/portfolio-webapp");
                p1.setLiveUrl("https://github.com/kanishka-fdo/portfolio-webapp");
                p1.setImageUrl("/img/default-profile.jpg");
                p1.setFeatured(true);
                p1.setCreatedAt(LocalDateTime.now());
                projectRepository.save(p1);

                Project p2 = new Project();
                p2.setTitle("Microservices E-Commerce Platform");
                p2.setSubtitle("Distributed Architecture");
                p2.setShortDescription("High-throughput microservices architecture with Spring Cloud, API Gateway, JWT security, and resilient event streaming.");
                p2.setDescription("Full-featured backend with service discovery, centralized configuration, distributed tracing, and transactional consistency.");
                p2.setTechStack("Spring Boot, Spring Cloud, Docker, PostgreSQL, Redis");
                p2.setGithubUrl("https://github.com/kanishka-fdo");
                p2.setLiveUrl("https://github.com/kanishka-fdo");
                p2.setFeatured(true);
                p2.setCreatedAt(LocalDateTime.now());
                projectRepository.save(p2);
            }
        };
    }
}

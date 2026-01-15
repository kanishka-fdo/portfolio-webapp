package com.kanishka.portfoliowebapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class FileUploadController {

    // Changed to match the resource handler configuration
    private final String UPLOAD_DIR = System.getProperty("user.dir") + "/uploads/";

    @PostConstruct
    public void init() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (created) {
                System.out.println("==============================================");
                System.out.println("✓ Created uploads directory at: " + UPLOAD_DIR);
                System.out.println("==============================================");
            } else {
                System.err.println("✗ Failed to create uploads directory!");
            }
        } else {
            System.out.println("✓ Uploads directory exists at: " + UPLOAD_DIR);
        }
    }

    @PostMapping("/upload-profile-photo")
    @ResponseBody
    public String uploadProfilePhoto(@RequestParam("file") MultipartFile file) {
        System.out.println("\n==================== PHOTO UPLOAD STARTED ====================");
        System.out.println("File received: " + (file != null ? file.getOriginalFilename() : "NULL"));

        try {
            if (file == null || file.isEmpty()) {
                System.out.println("✗ Error: File is empty or null");
                return "error:Please select a file";
            }

            String originalFilename = file.getOriginalFilename();
            System.out.println("Original filename: " + originalFilename);
            System.out.println("File size: " + file.getSize() + " bytes (" + (file.getSize() / 1024 / 1024) + " MB)");
            System.out.println("Content type: " + file.getContentType());

            if (originalFilename == null ||
                    !originalFilename.toLowerCase().matches(".*\\.(jpg|jpeg|png|gif|webp|bmp)$")) {
                System.out.println("✗ Invalid file type");
                return "error:Invalid file type. Please upload JPG, PNG, GIF, or WEBP";
            }

            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = "profile_" + System.currentTimeMillis() + "_" +
                    UUID.randomUUID().toString().substring(0, 8) + extension;

            System.out.println("New filename: " + newFilename);
            System.out.println("Upload directory: " + UPLOAD_DIR);

            Path filePath = Paths.get(UPLOAD_DIR + newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("✓ File saved successfully!");
            System.out.println("File path: " + filePath.toAbsolutePath());

            String urlPath = "/uploads/" + newFilename;
            System.out.println("✓ Returning URL: " + urlPath);
            System.out.println("==================== UPLOAD COMPLETE ====================\n");

            return urlPath;

        } catch (IOException e) {
            System.err.println("✗ Upload failed!");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return "error:Upload failed - " + e.getMessage();
        } catch (Exception e) {
            System.err.println("✗ Unexpected error!");
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return "error:Unexpected error - " + e.getMessage();
        }
    }
}
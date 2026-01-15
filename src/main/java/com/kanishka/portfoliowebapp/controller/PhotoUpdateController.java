package com.kanishka.portfoliowebapp.controller;

import com.kanishka.portfoliowebapp.model.CV;
import com.kanishka.portfoliowebapp.service.CVService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class PhotoUpdateController {

    private final CVService cvService;

    public PhotoUpdateController(CVService cvService) {
        this.cvService = cvService;
    }

    @PostMapping("/update-cv-photo")
    @ResponseBody
    public String updateCVPhoto(@RequestParam("photoUrl") String photoUrl,
                                @RequestParam(value = "cvId", required = false) Long cvId) {
        try {
            System.out.println("\n========== UPDATING CV PHOTO ==========");
            System.out.println("Photo URL: " + photoUrl);
            System.out.println("CV ID: " + cvId);

            CV cv;

            if (cvId != null) {
                // Update specific CV
                cv = cvService.findById(cvId);
            } else {
                // Update the active CV
                cv = cvService.findActiveCV().orElse(null);

                if (cv == null) {
                    // If no active CV, get the first one
                    List<CV> allCVs = cvService.findAll();
                    if (allCVs.isEmpty()) {
                        System.out.println("✗ No CVs found in database!");
                        return "error:No CV found. Please create a CV first.";
                    }
                    cv = allCVs.get(0);
                }
            }

            System.out.println("Found CV: " + cv.getFullName() + " (ID: " + cv.getId() + ")");

            // Update photo URL
            cv.setProfilePhotoUrl(photoUrl);
            cvService.save(cv);

            System.out.println("✓ CV photo updated successfully!");
            System.out.println("========================================\n");

            return "success:Photo updated for " + cv.getFullName();

        } catch (Exception e) {
            System.err.println("✗ Error updating CV photo: " + e.getMessage());
            e.printStackTrace();
            return "error:" + e.getMessage();
        }
    }
}
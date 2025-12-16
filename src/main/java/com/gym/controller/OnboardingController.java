package com.gym.controller;

import com.gym.entity.OnboardingSubmission;
import com.gym.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
// UPDATED: Allows localhost:5173 (Vite) and localhost:3000 (React default)
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:3000"}) 
public class OnboardingController {

    @Autowired
    private OnboardingService onboardingService;

    @PostMapping("/submit")
    public ResponseEntity<?> submitOnboarding(@RequestBody OnboardingSubmission submission) {
        try {
            // Check if data is coming in correctly
            System.out.println("Received Submission for: " + submission.getBusinessName());
            
            OnboardingSubmission saved = onboardingService.saveSubmission(submission);
            return ResponseEntity.ok(saved);
        } catch (Exception e) {
            e.printStackTrace(); // Print error to backend console for debugging
            return ResponseEntity.badRequest().body("Error saving onboarding: " + e.getMessage());
        }
    }
}
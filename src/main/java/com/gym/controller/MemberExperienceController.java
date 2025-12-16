package com.gym.controller;

import com.gym.entity.MemberExperience;
import com.gym.repository.MemberExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/experience")
public class MemberExperienceController {

    @Autowired
    private MemberExperienceRepository experienceRepo;

    // GET all sessions (Recent feedback and active sessions)
    @GetMapping("/sessions")
    public List<MemberExperience> getAllSessions() {
        return experienceRepo.findAllByOrderBySessionTimeDesc();
    }

    // CREATE or UPDATE a session with Feedback
    @PostMapping("/sessions")
    public MemberExperience createOrUpdateSession(@RequestBody MemberExperience payload) {
        if (payload.getSessionTime() == null) {
            payload.setSessionTime(LocalDateTime.now());
        }
        
        // Simple logic: If ratings are low, auto-flag
        if (payload.getOverall() != null && payload.getOverall() <= 2) {
            payload.setFlagged(true);
            payload.setNeedsFollowUp(true);
        }
        
        payload.setStatus("completed");
        return experienceRepo.save(payload);
    }
}
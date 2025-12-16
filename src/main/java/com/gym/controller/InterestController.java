package com.gym.controller;

import com.gym.entity.Interest;
import com.gym.repository.InterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/interests")
public class InterestController {

    @Autowired
    private InterestRepository interestRepo;

    @GetMapping
    public List<Interest> getAllInterests() {
        return interestRepo.findAll();
    }

    @PostMapping
    public Interest createInterest(@RequestBody Interest interest) {
        interest.setSubmittedAt(LocalDateTime.now());
        return interestRepo.save(interest);
    }
}
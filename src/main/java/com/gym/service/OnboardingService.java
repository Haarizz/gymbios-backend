package com.gym.service;

import com.gym.entity.OnboardingSubmission;
import com.gym.repository.OnboardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OnboardingService {

    @Autowired
    private OnboardingRepository repository;

    public OnboardingSubmission saveSubmission(OnboardingSubmission submission) {
        // Business logic can go here (e.g., send welcome email, validate data)
        return repository.save(submission);
    }
}
package com.gym.repository;

import com.gym.entity.OnboardingSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepository extends JpaRepository<OnboardingSubmission, Long> {
    // You can add custom queries here if needed, e.g., findByContactEmail(String email);
}
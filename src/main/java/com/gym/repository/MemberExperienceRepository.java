package com.gym.repository;

import com.gym.entity.MemberExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberExperienceRepository extends JpaRepository<MemberExperience, Long> {
    // Find sessions that have feedback (overall rating is not null)
    List<MemberExperience> findByOverallIsNotNullOrderBySessionTimeDesc();
    
    // Find all sessions sorted by time
    List<MemberExperience> findAllByOrderBySessionTimeDesc();
}
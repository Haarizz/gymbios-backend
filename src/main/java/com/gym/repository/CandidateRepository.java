package com.gym.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {

    // ✅ THIS USES JobOpening.id INTERNALLY
    List<Candidate> findByJobId(Long jobId);
}


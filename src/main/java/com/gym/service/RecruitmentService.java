package com.gym.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gym.entity.Candidate;
import com.gym.entity.Interview;
import com.gym.entity.JobOpening;
import com.gym.repository.CandidateRepository;
import com.gym.repository.InterviewRepository;
import com.gym.repository.JobOpeningRepository;

@Service
public class RecruitmentService {

    @Autowired
    private JobOpeningRepository jobRepo;

    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private InterviewRepository interviewRepo;

    // 1. Create Job Opening
    public JobOpening createJob(JobOpening job) {
        job.setCreatedAt(LocalDate.now());
        job.setStatus("active");
        return jobRepo.save(job);
    }

    public List<JobOpening> getAllJobs() {
        return jobRepo.findAll();
    }

    public JobOpening getJob(Long id) {
        return jobRepo.findById(id).orElseThrow();
    }

    // 2. Candidate applies
    public Candidate createCandidate(Candidate c) {
        c.setAppliedAt(LocalDate.now());
        c.setStage("applied");
        return candidateRepo.save(c);
    }

    public List<Candidate> getCandidates(Long jobId) {
        return candidateRepo.findByJobId(jobId);
    }

    // 3. Schedule interview
    public Interview scheduleInterview(Interview i) {
        return interviewRepo.save(i);
    }
}

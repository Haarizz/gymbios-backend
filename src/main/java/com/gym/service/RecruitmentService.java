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

    // ✅ CREATE JOB
    public JobOpening createJob(JobOpening job) {
        job.setCreatedAt(LocalDate.now());
        job.setStatus("active");
        return jobRepo.save(job);
    }

    // ✅ GET JOB BY ID
    public JobOpening getJob(Long id) {
        return jobRepo.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    // ⚠️ (NOT USED BY FRONTEND, BUT KEPT SAFE)
    public Candidate createCandidate(Candidate c) {
        c.setAppliedAt(LocalDate.now());
        c.setStage("applied");
        return candidateRepo.save(c);
    }

    // ✅ APPLY FOR JOB (USED BY FRONTEND)
    public Candidate addCandidate(Candidate candidate, Long jobId) {
        JobOpening job = jobRepo.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        candidate.setJob(job);
        candidate.setStage("applied");
        candidate.setAppliedAt(LocalDate.now()); //✅✅✅ FIXED

        return candidateRepo.save(candidate);
    }

    // ✅ FETCH CANDIDATES BY JOB
    public List<Candidate> getCandidatesByJob(Long jobId) {
        return candidateRepo.findByJobId(jobId);
    }

    // ✅ FETCH ALL JOBS
    public List<JobOpening> getAllJobs() {
        return jobRepo.findAll();
    }

    // ✅ SCHEDULE INTERVIEW
    public Interview scheduleInterview(Interview i) {
        return interviewRepo.save(i); //✅ SAFE
    }
}

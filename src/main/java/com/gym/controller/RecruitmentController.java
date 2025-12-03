package com.gym.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gym.entity.Candidate;
import com.gym.entity.Interview;
import com.gym.entity.JobOpening;
import com.gym.repository.CandidateRepository;
import com.gym.repository.InterviewRepository;
import com.gym.service.RecruitmentService;

@RestController
@RequestMapping("/recruitment")

public class RecruitmentController {

    @Autowired
    private RecruitmentService service;
    
    @Autowired
    private CandidateRepository candidateRepo;

    @Autowired
    private InterviewRepository interviewRepo;

    // JOB OPENINGS
    @PostMapping("/jobs")
    public JobOpening createJob(@RequestBody JobOpening job) {
        return service.createJob(job);
    }

    @GetMapping("/jobs")
    public List<JobOpening> getJobs() {
        return service.getAllJobs();
    }

    @GetMapping("/jobs/{id}")
    public JobOpening getJob(@PathVariable Long id) {
        return service.getJob(id);
    }

    // CANDIDATES
    @PostMapping("/candidates")
    public Candidate createCandidate(@RequestBody Candidate c) {
        return service.createCandidate(c);
    }

    @GetMapping("/candidates/{jobId}")
    public List<Candidate> getCandidates(@PathVariable Long jobId) {
        return service.getCandidates(jobId);
    }

    // INTERVIEWS
    @PostMapping("/interviews")
    public Interview scheduleInterview(@RequestBody Interview i) {
        return service.scheduleInterview(i);
    }
    
    @GetMapping("/interviews")
    public List<Interview> getInterviews(@RequestParam(required=false) Long jobId) {
        if (jobId != null) return interviewRepo.findByJobId(jobId);
        return interviewRepo.findAll();
    }

    @GetMapping("/candidate/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidateRepo.findById(id).orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

}


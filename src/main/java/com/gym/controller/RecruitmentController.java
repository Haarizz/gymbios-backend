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

    // ✅ CREATE JOB
    @PostMapping("/jobs")
    public JobOpening createJob(@RequestBody JobOpening job) {
        return service.createJob(job);
    }

    // ✅ GET ALL JOBS
    @GetMapping("/jobs")
    public List<JobOpening> getJobs() {
        return service.getAllJobs();
    }

    // ✅ GET JOB BY ID
    @GetMapping("/jobs/{id}")
    public JobOpening getJob(@PathVariable Long id) {
        return service.getJob(id);
    }

    // ✅ APPLY FOR JOB (ADD CANDIDATE)
    @PostMapping("/candidates/{jobId}")
    public Candidate applyForJob(
            @PathVariable Long jobId,
            @RequestBody Candidate candidate
    ) {
        return service.addCandidate(candidate, jobId);
    }

    // ✅ FETCH CANDIDATES BY JOB
    @GetMapping("/candidates/{jobId}")
    public List<Candidate> getCandidates(@PathVariable Long jobId) {
        return service.getCandidatesByJob(jobId);
    }

    // ✅ FETCH CANDIDATE BY ID
    @GetMapping("/candidate/{id}")
    public Candidate getCandidate(@PathVariable Long id) {
        return candidateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));
    }

    // ✅ SCHEDULE INTERVIEW
    @PostMapping("/interviews")
    public Interview scheduleInterview(@RequestBody Interview i) {
        return service.scheduleInterview(i);
    }

    // ✅ FETCH INTERVIEWS (BY JOB OR ALL)
    @GetMapping("/interviews")
    public List<Interview> getInterviews(@RequestParam(required = false) Long jobId) {
        if (jobId != null) return interviewRepo.findByJobId(jobId);
        return interviewRepo.findAll();
    }
}



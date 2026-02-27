package com.hrms.controller;

import com.hrms.entity.Candidate;
import com.hrms.entity.Job;
import com.hrms.repository.JobRepository;
import com.hrms.service.CandidatePipelineService;
import com.hrms.service.JobService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "http://localhost:3000")
public class JobController {

    private final JobService jobService;
    private final JobRepository jobRepo;
    private final CandidatePipelineService pipelineService;

    public JobController(JobService jobService,
                         JobRepository jobRepo,
                         CandidatePipelineService pipelineService) {
        this.jobService = jobService;
        this.jobRepo = jobRepo;
        this.pipelineService = pipelineService;
    }

    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public Job createJob(@RequestBody Job job) {
        return jobService.createJob(job);
    }

    @PutMapping("/{id}")
    public Job updateJob(@PathVariable Long id, @RequestBody Job job) {
        return jobService.updateJob(id, job);
    }

    @DeleteMapping("/{id}")
    public void deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
    }

    @PostMapping("/{id}/apply")
    public Candidate applyToJob(
            @PathVariable Long id,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String linkedinurl,
            @RequestParam String experience,
            @RequestParam MultipartFile resume
    ) throws IOException {

        Job job = jobRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        String uploadDir = System.getProperty("user.dir") + "/uploads/";
        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = uploadDir + resume.getOriginalFilename();
        File dest = new File(filePath);
        resume.transferTo(dest);
        Candidate candidate = new Candidate();
        candidate.setName(name);
        candidate.setEmail(email);
        candidate.setPhone(phone);
        candidate.setLinkedinurl(linkedinurl);
        candidate.setExperience(experience);
        candidate.setResumePath(filePath);
        candidate.setJob(job);

        Candidate savedCandidate = pipelineService.applyCandidate(candidate);

        Integer currentApplicants = job.getApplicants();

        if (currentApplicants == null) {
            currentApplicants = 0;
        }

        job.setApplicants(currentApplicants + 1);
        jobRepo.save(job);

        return savedCandidate;
    }}
package com.hrms.service;


import com.hrms.entity.Candidate;
import com.hrms.entity.Job;
import com.hrms.repository.CandidateRepository;
import com.hrms.repository.JobRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JobService {

    private final JobRepository jobRepository;
    private final CandidateRepository applicantRepository;
    private String normalizeLinkedIn(String url) {
        if (url == null || url.isBlank()) {
            return null;
        }

        url = url.trim();

        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            url = "https://" + url;
        }

        return url;
    }

    public JobService(JobRepository jobRepository, CandidateRepository applicantRepository) {
        this.jobRepository = jobRepository;
        this.applicantRepository = applicantRepository;
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    public Job getJobById(Long id) {
        return jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public void deleteJob(Long id) {
        jobRepository.deleteById(id);
    }

    public Job updateJob(Long id, Job updatedJob) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        job.setTitle(updatedJob.getTitle());
        job.setDept(updatedJob.getDept());
        job.setLocation(updatedJob.getLocation());
        job.setSalary(updatedJob.getSalary());
        job.setDescription(updatedJob.getDescription());
        job.setSkills(updatedJob.getSkills());
        job.setStatus(updatedJob.getStatus());

        return jobRepository.save(job);
    }

    public Candidate applyToJob(Long jobId, String name, String email,String phone,String linkedinurl, String experience, String resumePath) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        Candidate applicant = new Candidate();
        applicant.setName(name);
        applicant.setEmail(email);
        applicant.setPhone(phone);
        applicant.setLinkedinurl(normalizeLinkedIn(linkedinurl));
        applicant.setExperience(experience);
        applicant.setResumePath(resumePath);
        applicant.setStage("Applied");
        applicant.setJob(job);

        Candidate savedApplicant = applicantRepository.save(applicant);

        job.setApplicants(job.getApplicants() + 1);
        jobRepository.save(job);

        return savedApplicant;
    }
}
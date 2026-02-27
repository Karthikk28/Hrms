package com.hrms.controller;

import com.hrms.dto.QuickCandidateDTO;
import com.hrms.entity.Candidate;
import com.hrms.entity.CandidateStatus;
import com.hrms.entity.QuickCandidate;
import com.hrms.repository.CandidateRepository;
import com.hrms.repository.JobRepository;
import com.hrms.repository.QuickCandidateRepository;
import com.hrms.service.CandidatePipelineService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateController {

    private final CandidatePipelineService pipelineService;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final QuickCandidateRepository quickCandidateRepository;

    public CandidateController(
            CandidatePipelineService pipelineService,
            CandidateRepository candidateRepository,
            JobRepository jobRepository,
            QuickCandidateRepository quickCandidateRepository
    ) {
        this.pipelineService = pipelineService;
        this.candidateRepository = candidateRepository;
        this.jobRepository = jobRepository;
        this.quickCandidateRepository = quickCandidateRepository;
    }
    @PostMapping
    public Candidate apply(@RequestBody Candidate candidate) {
        return pipelineService.applyCandidate(candidate);
    }
    @PostMapping("/quick-add")
    public ResponseEntity<QuickCandidate> quickAdd(
            @RequestBody QuickCandidateDTO dto) {

        QuickCandidate quickCandidate = new QuickCandidate();
        quickCandidate.setName(dto.getName());
        quickCandidate.setRole(dto.getRole());
        quickCandidate.setStage("Applied");

        QuickCandidate saved = quickCandidateRepository.save(quickCandidate);

        return ResponseEntity.ok(saved);
    }
    @GetMapping("/quick")
    public List<QuickCandidate> getAllQuick() {
        return quickCandidateRepository.findAll();
    }
    @GetMapping
    public List<Candidate> getAll() {
        return pipelineService.getAllCandidates();
    }
    @PostMapping("/{id}/move")
    public Candidate moveNext(@PathVariable Long id) {
        return pipelineService.moveToNextStage(id);
    }
    @GetMapping("/onboarding")
    public List<Candidate> getOnboardingCandidates() {
        return candidateRepository.findByCurrentStatus(CandidateStatus.OFFERED);
    }
    @PostMapping("/{id}/reject")
    public Candidate reject(@PathVariable Long id) {
        return pipelineService.rejectCandidate(id);
    }
	public CandidateRepository getCandidateRepository() {
		return candidateRepository;
	}
	public JobRepository getJobRepository() {
		return jobRepository;
	}
}
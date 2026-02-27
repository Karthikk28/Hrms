package com.hrms.service;

import com.hrms.entity.*;
import com.hrms.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CandidatePipelineService {

    private final CandidateRepository candidateRepo;
    private final CandidateStageHistoryRepository historyRepo;

    public CandidatePipelineService(
            CandidateRepository candidateRepo,
            CandidateStageHistoryRepository historyRepo) {

        this.candidateRepo = candidateRepo;
        this.historyRepo = historyRepo;
    }
    public Candidate applyCandidate(Candidate candidate) {
        candidate.setCurrentStatus(CandidateStatus.APPLIED);
        Candidate saved = candidateRepo.save(candidate);

        historyRepo.save(new CandidateStageHistory(
                saved.getId(),
                CandidateStatus.APPLIED
        ));

        return saved;
    }

    public List<Candidate> getAllCandidates() {
        return candidateRepo.findAll();
    }
    public Candidate moveToNextStage(Long candidateId) {

        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        CandidateStatus current = candidate.getCurrentStatus();
        CandidateStatus next;

        switch (current) {
            case APPLIED:
                next = CandidateStatus.SCREENING;
                break;
            case SCREENING:
                next = CandidateStatus.INTERVIEW;
                break;
            case INTERVIEW:
                next = CandidateStatus.OFFERED;
                break;
            case OFFERED:
                next = CandidateStatus.HIRED;
                break;
            default:
                throw new RuntimeException("No further stage available");
        }

        candidate.setCurrentStatus(next);
        Candidate updated = candidateRepo.save(candidate);

        historyRepo.save(new CandidateStageHistory(
                candidateId,
                next
        ));

        return updated;
    }

    public Candidate rejectCandidate(Long candidateId) {

        Candidate candidate = candidateRepo.findById(candidateId)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        candidate.setCurrentStatus(CandidateStatus.REJECTED);
        Candidate updated = candidateRepo.save(candidate);

        historyRepo.save(new CandidateStageHistory(
                candidateId,
                CandidateStatus.REJECTED
        ));

        return updated;
    }

}
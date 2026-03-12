package com.hrms.controller;

import org.springframework.web.bind.annotation.*;
import com.hrms.entity.CandidateStageHistory;
import com.hrms.repository.CandidateStageHistoryRepository;
import java.util.List;

@RestController
@RequestMapping("/api/candidate-history")
@CrossOrigin(origins = "http://localhost:3000")
public class CandidateHistoryController {

    private final CandidateStageHistoryRepository repo;
    public CandidateHistoryController(CandidateStageHistoryRepository repo) {
        this.repo = repo;
    }
    
    @GetMapping("/{candidateId}")
    public List<CandidateStageHistory> getHistory(
            @PathVariable Long candidateId
    ) {
        return repo.findByCandidateIdOrderByChangedAtAsc(candidateId);
    }
}

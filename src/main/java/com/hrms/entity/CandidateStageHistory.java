package com.hrms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidate_stage_history")
public class CandidateStageHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long candidateId;

    @Enumerated(EnumType.STRING)
    private CandidateStatus stage;

    private LocalDateTime changedAt;

    public CandidateStageHistory() {}

    public CandidateStageHistory(Long candidateId, CandidateStatus stage) {
        this.candidateId = candidateId;
        this.stage = stage;
        this.changedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getCandidateId() {
        return candidateId;
    }

    public CandidateStatus getStage() {
        return stage;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public void setStage(CandidateStatus stage) {
        this.stage = stage;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }
}

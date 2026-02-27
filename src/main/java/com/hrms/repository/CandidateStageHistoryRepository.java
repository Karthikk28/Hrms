package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.CandidateStageHistory;

public interface CandidateStageHistoryRepository
extends JpaRepository<CandidateStageHistory, Long> {

List<CandidateStageHistory> findByCandidateIdOrderByChangedAtAsc(Long candidateId);
}

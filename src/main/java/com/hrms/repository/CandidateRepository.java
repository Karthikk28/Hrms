package com.hrms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hrms.entity.Candidate;
import com.hrms.entity.CandidateStatus;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> {
	List<Candidate> findByCurrentStatus(CandidateStatus status);
}
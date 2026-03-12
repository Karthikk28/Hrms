package com.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hrms.entity.QuickCandidate;

public interface QuickCandidateRepository 
        extends JpaRepository<QuickCandidate, Long> {
}
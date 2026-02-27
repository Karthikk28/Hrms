package com.hrms.repository;

import com.hrms.entity.OnboardingTaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OnboardingTaskStatusRepository
        extends JpaRepository<OnboardingTaskStatus, Long> {

    List<OnboardingTaskStatus> findByCandidateId(Long candidateId);

    Optional<OnboardingTaskStatus> findByCandidateIdAndStepNameAndTaskName(
            Long candidateId,
            String stepName,
            String taskName
    );
}
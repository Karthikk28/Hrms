package com.hrms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "onboarding_task_status")
public class OnboardingTaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @Column(nullable = false)
    private String stepName;

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private boolean completed;

    public OnboardingTaskStatus() {
    }


    public Long getId() {
        return id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public String getStepName() {
        return stepName;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
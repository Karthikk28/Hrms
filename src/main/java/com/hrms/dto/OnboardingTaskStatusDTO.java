package com.hrms.dto;

public class OnboardingTaskStatusDTO {

    private Long candidateId;
    private String stepName;
    private String taskName;
    private boolean completed;
	
	public Long getCandidateId() {
		return candidateId;
	}
	
	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}
		public String getStepName() {
		return stepName;
	}
		public void setStepName(String stepName) {
		this.stepName = stepName;
	}
		public String getTaskName() {
		return taskName;
	}
		public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
		public boolean isCompleted() {
		return completed;
	}
		public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
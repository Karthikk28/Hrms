package com.hrms.dto;

import java.util.List;

public class LeaveHistoryResponse {

    private Long id;
    private String type;
    private String reason;
    private String dates;
    private float days;
    private String requestedOn;
    private String status;
    private String approvedBy;
    private String pendingWith;
    private List<WorkflowNode> workflow;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }

    public String getDates() { return dates; }
    public void setDates(String dates) { this.dates = dates; }

    public float getDays() { return days; }
    public void setDays(float days) { this.days = days; }

    public String getRequestedOn() { return requestedOn; }
    public void setRequestedOn(String requestedOn) { this.requestedOn = requestedOn; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getApprovedBy() { return approvedBy; }
    public void setApprovedBy(String approvedBy) { this.approvedBy = approvedBy; }

    public String getPendingWith() { return pendingWith; }
    public void setPendingWith(String pendingWith) { this.pendingWith = pendingWith; }

    public List<WorkflowNode> getWorkflow() { return workflow; }
    public void setWorkflow(List<WorkflowNode> workflow) { this.workflow = workflow; }
    public static class WorkflowNode {
        private String step;
        private String by;
        private String role;
        private String time;
        private String status;
        private String comment;

        public String getStep() { return step; }
        public void setStep(String step) { this.step = step; }

        public String getBy() { return by; }
        public void setBy(String by) { this.by = by; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }

        public String getTime() { return time; }
        public void setTime(String time) { this.time = time; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getComment() { return comment; }
        public void setComment(String comment) { this.comment = comment; }
    }

	public void setPendingWith(Object pendingWith2) {
		
	}
}

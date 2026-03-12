package com.hrms.dto;

import java.util.List;

public class LeaveRequestResponse {

    private Long id;
    private String type;
    private Float days;
    private String status;
    private String dates;
    private String pendingWith;
    private String reason;          
    private List<WorkflowStep> workflow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getDays() {
        return days;
    }

    public void setDays(Float days) {
        this.days = days;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getPendingWith() {
        return pendingWith;
    }

    public void setPendingWith(String pendingWith) {
        this.pendingWith = pendingWith;
    }

    public String getReason() {          
        return reason;
    }

    public void setReason(String reason) {  
        this.reason = reason;
    }

    public List<WorkflowStep> getWorkflow() {
        return workflow;
    }

    public void setWorkflow(List<WorkflowStep> workflow) {
        this.workflow = workflow;
    }

    public static class WorkflowStep {
        private String step;
        private String by;
        private String role;
        private String status;
        private String time;
        private String comment;

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }
    }
}
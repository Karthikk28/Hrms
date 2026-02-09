package com.example.hrms.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TaskAssigneeId implements Serializable {

   
	private static final long serialVersionUID = 1L;
	private Integer taskId;
    private Integer employeeId;

    public TaskAssigneeId() {}

    public TaskAssigneeId(Integer taskId, Integer employeeId) {
        this.taskId = taskId;
        this.employeeId = employeeId;
    }

   
    public Integer getTaskId() { return taskId; }
    public void setTaskId(Integer taskId) { this.taskId = taskId; }

    public Integer getEmployeeId() { return employeeId; }
    public void setEmployeeId(Integer employeeId) { this.employeeId = employeeId; }

 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaskAssigneeId)) return false;
        TaskAssigneeId that = (TaskAssigneeId) o;
        return Objects.equals(getTaskId(), that.getTaskId()) &&
               Objects.equals(getEmployeeId(), that.getEmployeeId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getEmployeeId());
    }
}

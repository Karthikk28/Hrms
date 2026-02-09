package com.example.hrms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    @Enumerated(EnumType.STRING)
    private Status status = Status.TODO;

    private LocalDateTime dueDate;

    private Integer createdBy;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "employee_id")
    private Employee assignedTo;
   
    public Integer getId() {
    	return id;
    	}
    public void setId(Integer id) {
    	this.id = id; 
    	}

    public String getTitle() { 
    	return title;
    	}
    public void setTitle(String title) { 
    	this.title = title; 
    	}

    public String getDescription() { 
    	return description;
    	}
    public void setDescription(String description) { 
    	this.description = description; 
    	}

    public Priority getPriority() { 
    	return priority;
    	}
    public void setPriority(Priority priority) { 
    	this.priority = priority; 
    	}

    public Status getStatus() { 
    	return status; 
    	}
    public void setStatus(Status status) { 
    	this.status = status; 
    	}

    public LocalDateTime getDueDate() { 
    	return dueDate; 
    	}
    public void setDueDate(LocalDateTime dueDate) { 
    	this.dueDate = dueDate; 
    	}

    public Integer getCreatedBy() { 
    	return createdBy; 
    	}
    public void setCreatedBy(Integer createdBy) { 
    	this.createdBy = createdBy; 
    	}

    public Employee getAssignedTo() { 
    	return assignedTo; 
    	}
    public void setAssignedTo(Employee assignedTo) { 
    	this.assignedTo = assignedTo; 
    	}
    
    public enum Priority { 
    	LOW, MEDIUM, HIGH, CRITICAL
    	}
    public enum Status { 
    	TODO, IN_PROGRESS, IN_REVIEW, DONE, BLOCKED 
    	}
	public Employee getProject() {
		return null;
	}
}

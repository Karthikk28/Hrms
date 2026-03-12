package com.hrms.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class TaskResponseDTO {

    private Integer id;
    private Integer projectId;
    private String title;
    private String description;
    private String priority;
    private String status;
    private LocalDate dueDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private List<TaskAssigneeDTO> assignees;

    public TaskResponseDTO(
            Integer id,
            Integer projectId,
            String title,
            String description,
            String priority,
            String status,
            LocalDate dueDate,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            List<TaskAssigneeDTO> assignees
    ) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.assignees = assignees;
    }

    public Integer getId() { return id; }
    public Integer getProjectId() { return projectId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getPriority() { return priority; }
    public String getStatus() { return status; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public List<TaskAssigneeDTO> getAssignees() { return assignees; }
}
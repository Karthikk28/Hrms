package com.hrms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "project_members")
public class ProjectMember {

    @EmbeddedId
    private ProjectMemberId id;

    @Column(nullable = false)
    private String role;

    @Column(name = "joined_at", updatable = false)
    private LocalDateTime joinedAt;

    public ProjectMember() {}

    public ProjectMember(Integer projectId, Long employeeId, String role) {
        this.id = new ProjectMemberId(projectId, employeeId);
        this.role = role;
    }

    @PrePersist
    void onCreate() {
        joinedAt = LocalDateTime.now();
    }

    public ProjectMemberId getId() {
        return id;
    }

    public void setId(ProjectMemberId id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}
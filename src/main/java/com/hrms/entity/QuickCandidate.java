package com.hrms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "quick_candidates")
public class QuickCandidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String stage = "Applied";

    public QuickCandidate() {}

    public QuickCandidate(String name, String role) {
        this.name = name;
        this.role = role;
        this.stage = "Applied";
    }

    public Long getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getStage() { return stage; }
    public void setStage(String stage) { this.stage = stage; }
}
package com.hrms.entity;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String dept;
    private String location;
    private String salary;
    private String description;
    private String skills;
    private String status = "Active";
    private Integer applicants = 0;

    private Integer vacancies;

    @OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Candidate> applicantList;

 

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSkills() { return skills; }
    public void setSkills(String skills) { this.skills = skills; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Integer getApplicants() { return applicants; }
    public void setApplicants(Integer applicants) { this.applicants = applicants; }

    public Integer getVacancies() { return vacancies; }
    public void setVacancies(Integer vacancies) { this.vacancies = vacancies; }
}

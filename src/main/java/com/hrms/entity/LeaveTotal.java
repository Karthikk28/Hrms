package com.hrms.entity;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "leave_totals")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class LeaveTotal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    private Integer year;
    private Float totalDays;

    public Long getId() { return id; }
    public Employee getEmployee() { return employee; }
    public Integer getYear() { return year; }
    public Float getTotalDays() { return totalDays; }

    public void setId(Long id) { this.id = id; }
    public void setEmployee(Employee employee) { this.employee = employee; }
    public void setYear(Integer year) { this.year = year; }
    public void setTotalDays(Float totalDays) { this.totalDays = totalDays; }
}

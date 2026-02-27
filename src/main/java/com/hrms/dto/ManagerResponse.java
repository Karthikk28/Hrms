package com.hrms.dto;

import java.time.LocalDate;

public class ManagerResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String role;
    private String department;
    private String location;
    private LocalDate dateOfJoin;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public LocalDate getDateOfJoin() { return dateOfJoin; }
    public void setDateOfJoin(LocalDate dateOfJoin) { this.dateOfJoin = dateOfJoin; }
}

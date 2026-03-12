package com.hrms.dto;
 
public class TaskAssigneeDTO {
 
    private Long employeeId;
    private String name;
    private String email;
    private String role;
 
    public TaskAssigneeDTO(Long employeeId, String name, String email, String role) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.role=role;
    }
 
    public Long getEmployeeId() {
        return employeeId;
    }
 
    public String getName() {
        return name;
    }
 
    public String getEmail() {
        return email;
    }
    
    public String getRole() {
    	return role;
    }
}
 
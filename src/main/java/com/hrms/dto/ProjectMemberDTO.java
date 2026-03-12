package com.hrms.dto;

public class ProjectMemberDTO {

    private Integer projectId;
    private Long employeeId;
    private String employeeName;
    private String employeeEmail;
    private String role;

    public ProjectMemberDTO() {}

    public ProjectMemberDTO(
            Integer projectId,
            Long employeeId,
            String employeeName,
            String employeeEmail,
            String role) {

        this.projectId = projectId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeEmail = employeeEmail;
        this.role = role;
    }

    public Integer getProjectId() { return projectId; }
    public Long getEmployeeId() { return employeeId; }
    public String getEmployeeName() { return employeeName; }
    public String getEmployeeEmail() { return employeeEmail; }
    public String getRole() { return role; }
}
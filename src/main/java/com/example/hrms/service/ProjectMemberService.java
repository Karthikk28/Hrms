package com.example.hrms.service;
import com.example.hrms.entity.ProjectMember;
import com.example.hrms.repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;
@Service
public class ProjectMemberService {
    private final ProjectMemberRepository repo;
    public ProjectMemberService(ProjectMemberRepository repo) {
        this.repo = repo;
    }
    public ProjectMember addMember(ProjectMember member) {
        Integer projectId = member.getId().getProjectId();
        Integer employeeId = member.getId().getEmployeeId();
        if (repo.existsById_ProjectIdAndId_EmployeeId(projectId, employeeId)) {
            throw new RuntimeException("Employee already exists in project");
        }
        return repo. save(member);
    }
}
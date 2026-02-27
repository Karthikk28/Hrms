package com.hrms.service;

import com.hrms.dto.EmployeeSimpleDTO;
import com.hrms.dto.ProjectMemberDTO;
import com.hrms.entity.ProjectMember;
import com.hrms.repository.ProjectMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectMemberService {

    private final ProjectMemberRepository repo;

    public ProjectMemberService(ProjectMemberRepository repo) {
        this.repo = repo;
    }

    public ProjectMember addMember(Integer projectId, Long employeeId, String role) {

        if (projectId == null || employeeId == null) {
            throw new RuntimeException("ProjectId and EmployeeId cannot be null");
        }

        if (repo.existsById_ProjectIdAndId_EmployeeId(projectId, employeeId)) {
            throw new RuntimeException("Employee already exists in project");
        }

        ProjectMember member =
                new ProjectMember(projectId, employeeId, role);

        return repo.save(member);
    }

    public List<ProjectMember> getMembersByProject(Integer projectId) {
        return repo.findById_ProjectId(projectId);
    }

    public List<ProjectMemberDTO> getMembersWithDetails(Integer projectId) {

        List<Object[]> rows =
                repo.findMembersWithEmployeeDetails(projectId);

        return rows.stream()
                .map(r -> new ProjectMemberDTO(
                        ((Number) r[0]).intValue(),
                        ((Number) r[1]).longValue(),
                        (String) r[2],
                        (String) r[3],
                        (String) r[4]
                ))
                .toList();
    }

    public List<EmployeeSimpleDTO> getProjectMembers(Integer projectId) {

        List<Object[]> results =
                repo.findMembersWithEmployeeDetails(projectId);

        return results.stream().map(r -> {
            EmployeeSimpleDTO dto = new EmployeeSimpleDTO();
            dto.setEmployeeId(((Number) r[1]).longValue());
            dto.setName((String) r[2]);
            dto.setEmail((String) r[3]);
            dto.setRole((String) r[4]);
            return dto;
        }).toList();
    }
}
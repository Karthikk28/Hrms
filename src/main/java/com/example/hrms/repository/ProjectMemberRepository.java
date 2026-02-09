package com.example.hrms.repository;
import com.example.hrms.entity.ProjectMember;
import com.example.hrms.entity.ProjectMemberId;
import org.springframework.data.jpa.repository.JpaRepository;
public interface ProjectMemberRepository
        extends JpaRepository<ProjectMember, ProjectMemberId> {
    boolean existsById_ProjectIdAndId_EmployeeId(
            Integer projectId,
            Integer employeeId
    );
}
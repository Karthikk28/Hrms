package com.hrms.repository;

import com.hrms.entity.ProjectMember;
import com.hrms.entity.ProjectMemberId;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjectMemberRepository
        extends JpaRepository<ProjectMember, ProjectMemberId> {

    boolean existsById_ProjectIdAndId_EmployeeId(
            Integer projectId,
            Long employeeId
    );
    List<ProjectMember> findById_ProjectId(Integer projectId);
    @Query(value = """
    	    SELECT 
    	        pm.project_id,
    	        pm.employee_id,
    	        CONCAT(e.first_name, ' ', e.last_name) AS name,
    	        e.email,
    	        pm.role
    	    FROM project_members pm
    	    JOIN employees e ON pm.employee_id = e.id
    	    WHERE pm.project_id = :projectId
    	""", nativeQuery = true)
    	List<Object[]> findMembersWithEmployeeDetails(@Param("projectId") Integer projectId);

}

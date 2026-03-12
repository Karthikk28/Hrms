package com.hrms.repository;
 
import com.hrms.dto.TaskAssigneeDTO;
import com.hrms.entity.TaskAssignee;
import com.hrms.entity.TaskAssigneeId;
 
import java.util.List;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface TaskAssigneeRepository
        extends JpaRepository<TaskAssignee, TaskAssigneeId> {
 
    List<TaskAssignee> findById_TaskId(Integer taskId);
 
    @Query("""
    	    SELECT new com.hrms.dto.TaskAssigneeDTO(
    	        e.id,
    	        CONCAT(e.firstName, ' ', e.lastName),
    	        e.email,
    	        pm.role
    	    )
    	    FROM TaskAssignee ta
    	    JOIN Task t ON t.id = ta.id.taskId
    	    JOIN Employee e ON ta.id.employeeId = e.id
    	    JOIN ProjectMember pm 
    	        ON pm.id.employeeId = e.id 
    	        AND pm.id.projectId = t.projectId
    	    WHERE ta.id.taskId = :taskId
    	""")
    	List<TaskAssigneeDTO> findAssigneesWithNames(@Param("taskId") Integer taskId);
    @Query(value = """
    	    SELECT e.id, e.first_name, e.email
    	    FROM task_assignees ta
    	    JOIN employees e ON ta.employee_id = e.id
    	    WHERE ta.task_id = :taskId
    	""", nativeQuery = true)
    	List<Object[]> findAssigneesByTaskId(Integer taskId);
}

 
 

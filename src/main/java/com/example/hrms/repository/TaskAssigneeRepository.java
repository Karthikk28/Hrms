package com.example.hrms.repository;
 
import com.example.hrms.entity.TaskAssignee;
import com.example.hrms.entity.TaskAssigneeId;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface TaskAssigneeRepository
        extends JpaRepository<TaskAssignee, TaskAssigneeId> {
}
package com.example.hrms.service;

import com.example.hrms.entity.Employee;
import com.example.hrms.entity.Task;
import com.example.hrms.entity.TaskAssignee;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.ProjectMemberRepository;
import com.example.hrms.repository.TaskAssigneeRepository;
import com.example.hrms.repository.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskAssigneeService {

    private final TaskAssigneeRepository assigneeRepo;
    private final TaskRepository taskRepo;
    private final EmployeeRepository employeeRepo;
    private final ProjectMemberRepository memberRepo;

    public TaskAssigneeService(
            TaskAssigneeRepository assigneeRepo,
            TaskRepository taskRepo,
            EmployeeRepository employeeRepo,
            ProjectMemberRepository memberRepo
    ) {
        this.assigneeRepo = assigneeRepo;
        this.taskRepo = taskRepo;
        this.employeeRepo = employeeRepo;
        this.memberRepo = memberRepo;
    }

    public TaskAssignee assignTask(Integer taskId, Integer employeeId) {
        Task task = taskRepo.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        boolean isMember = memberRepo.existsById_ProjectIdAndId_EmployeeId(
                task.getProject().getId(),
                employeeId
        );

        if (!isMember) {
            throw new RuntimeException("Employee is not part of this project");
        }
        TaskAssignee assignee = new TaskAssignee();
        assignee.setTask(task);
        assignee.setEmployee(employee);

        return assigneeRepo.save(assignee);
    }
}

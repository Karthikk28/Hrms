package com.example.hrms.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "task_assignees")
public class TaskAssignee {

    @EmbeddedId
    private TaskAssigneeId id;

    @ManyToOne
    @MapsId("taskId")
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "assigned_at", updatable = false)
    private LocalDateTime assignedAt;

    public TaskAssignee() {}

    public TaskAssignee(Task task, Employee employee) {
        this.task = task;
        this.employee = employee;
        this.id = new TaskAssigneeId(task.getId(), employee.getId());
        this.assignedAt = LocalDateTime.now();
    }


    public TaskAssigneeId getId() { return id; }
    public void setId(TaskAssigneeId id) { this.id = id; }

    public Task getTask() { return task; }
    public void setTask(Task task) { this.task = task; }

    public Employee getEmployee() { return employee; }
    public void setEmployee(Employee employee) { this.employee = employee; }

    public LocalDateTime getAssignedAt() { return assignedAt; }
    public void setAssignedAt(LocalDateTime assignedAt) { this.assignedAt = assignedAt; }
}

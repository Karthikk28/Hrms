package com.example.hrms.controller;

import com.example.hrms.entity.Task;
import com.example.hrms.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService service;
    public TaskController(TaskService service) {
        this.service = service;
    }

    @PutMapping("/{taskId}/assign")
    public ResponseEntity<Task> assignTask(
            @PathVariable Integer taskId,
            @RequestBody AssignTaskRequest request
    ) {
        if (request.getEmployeeId() == null) {
            return ResponseEntity.badRequest().build();
        }
        Task updatedTask = service.assignTask(taskId, request.getEmployeeId());
        if (updatedTask == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedTask);
    }
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }
    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task savedTask = service.createTask(task);
        return ResponseEntity.ok(savedTask);
    }
    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<Task>> getTasksByEmployee(
            @PathVariable Integer employeeId
    ) {
        return ResponseEntity.ok(service.getTasksByEmployeeId(employeeId));
    }
    public static class AssignTaskRequest {
        private Integer employeeId;

        public Integer getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(Integer employeeId) {
            this.employeeId = employeeId;
        }
    }
}

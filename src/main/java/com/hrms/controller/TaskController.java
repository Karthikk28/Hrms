package com.hrms.controller;

import com.hrms.dto.TaskResponseDTO;
import com.hrms.entity.Task;
import com.hrms.entity.TaskStatus;
import com.hrms.service.TaskService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return service.createTask(task);
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return service.getAllTasks();
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getTaskById(@PathVariable Integer id) {
        return service.getTaskById(id);
    }
    @GetMapping("/my-tasks/{employeeId}")
    public List<Task> getMyTasks(@PathVariable Long employeeId) {
        return service.getMyTasks(employeeId);
    }

    
    @PutMapping("/{id}/status")
    public Task updateTaskStatus(
        @PathVariable Integer id,
        @RequestBody Map<String, String> body
    ) {
        TaskStatus status = TaskStatus.valueOf(body.get("status"));
        return service.updateTaskStatus(id, status);
    }

    
    @PutMapping("/{id}")
    public Task updateTask(
            @PathVariable Integer id,
            @RequestBody Task updatedTask
    ) {
        return service.updateTask(id, updatedTask);
    }

    @PostMapping("/{taskId}/assign/{employeeId}")
    public ResponseEntity<String> assignEmployee(
            @PathVariable Integer taskId,
            @PathVariable Long employeeId) {

    	service.assignEmployeeToTask(taskId, employeeId);
        return ResponseEntity.ok("Employee assigned successfully");
    }

}

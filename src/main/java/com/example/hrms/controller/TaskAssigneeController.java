package com.example.hrms.controller;
import com.example.hrms.entity.TaskAssignee;
import com.example.hrms.service.TaskAssigneeService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/task-assignees")
@CrossOrigin
public class TaskAssigneeController {

    private final TaskAssigneeService service;

    public TaskAssigneeController(TaskAssigneeService service) {
        this.service = service;
    }

    @PostMapping
    public TaskAssignee assignTask(@RequestBody Map<String, Integer> body) {
        return service.assignTask(
                body.get("taskId"),
                body.get("employeeId")
        );
    }
}

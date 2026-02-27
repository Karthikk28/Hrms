package com.hrms.controller;

import com.hrms.dto.TaskAssigneeDTO;
import com.hrms.dto.TaskResponseDTO;
import com.hrms.entity.TaskAssignee;
import com.hrms.service.TaskAssigneeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public TaskAssignee assignTask(@RequestBody Map<String, Object> body) {

        Integer taskId = ((Number) body.get("taskId")).intValue();
        Long employeeId = ((Number) body.get("employeeId")).longValue();

        return service.assignTask(taskId, employeeId);
    }

    @GetMapping("/{taskId}")
    public List<TaskAssignee> getByTask(@PathVariable Integer taskId) {
        return service.getAssigneesByTaskId(taskId);
    }
    @GetMapping
    public List<TaskAssignee> getAll() {
        return service.getAllAssignees();
    }
    @GetMapping("/{taskId}/details")
    public List<TaskAssigneeDTO> getAssignees(@PathVariable Integer taskId) {
        return service.getAssigneesWithNames(taskId);
    }

}

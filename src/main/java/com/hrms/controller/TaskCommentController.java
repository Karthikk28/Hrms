package com.hrms.controller;
 
import com.hrms.entity.TaskComment;
import com.hrms.service.TaskCommentService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/api/task-comments")
@CrossOrigin
public class TaskCommentController {

    private final TaskCommentService service;

    public TaskCommentController(TaskCommentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TaskComment> addComment(@RequestBody TaskComment comment) {
        return ResponseEntity.ok(service.addComment(comment));
    }

    @GetMapping("/{taskId}")
    public List<TaskComment> getByTask(@PathVariable Integer taskId) {
        return service.getCommentsByTaskId(taskId);
    }
}

 
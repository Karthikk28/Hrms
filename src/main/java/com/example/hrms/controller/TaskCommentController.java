package com.example.hrms.controller;
 
import com.example.hrms.entity.TaskComment;
import com.example.hrms.service.TaskCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/api/task-comments")
public class TaskCommentController {
 
    private final TaskCommentService service;
 
    public TaskCommentController(TaskCommentService service) {
        this.service = service;
    }
 
    @PostMapping
    public ResponseEntity<TaskComment> addComment(@RequestBody TaskComment comment) {
        TaskComment savedComment = service.addComment(comment);
        return ResponseEntity.ok(savedComment);
    }
}
package com.example.hrms.controller;
 
import com.example.hrms.entity.TaskAttachment;
import com.example.hrms.service.TaskAttachmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 
@RestController
@RequestMapping("/api/task-attachments")
public class TaskAttachmentController {
 
    private final TaskAttachmentService service;
 
    public TaskAttachmentController(TaskAttachmentService service) {
        this.service = service;
    }
 
    @PostMapping
    public ResponseEntity<TaskAttachment> addAttachment(@RequestBody TaskAttachment attachment) {
        TaskAttachment saved = service.addAttachment(attachment);
        return ResponseEntity.ok(saved);
    }
}
package com.example.hrms.service;
 
import com.example.hrms.entity.TaskAttachment;
import com.example.hrms.repository.TaskAttachmentRepository;
import org.springframework.stereotype.Service;
 
@Service
public class TaskAttachmentService {
 
    private final TaskAttachmentRepository repo;
 
    public TaskAttachmentService(TaskAttachmentRepository repo) {
        this.repo = repo;
    }
 
    public TaskAttachment addAttachment(TaskAttachment attachment) {
        return repo.save(attachment);
    }
}
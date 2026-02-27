package com.hrms.service;
 
import com.hrms.entity.TaskComment;
import com.hrms.repository.TaskCommentRepository;

import java.util.List;

import org.springframework.stereotype.Service;
 
@Service
public class TaskCommentService {

    private final TaskCommentRepository repo;

    public TaskCommentService(TaskCommentRepository repo) {
        this.repo = repo;
    }

    public TaskComment addComment(TaskComment comment) {
        return repo.save(comment);
    }

    public List<TaskComment> getCommentsByTaskId(Integer taskId) {
        return repo.findByTaskId(taskId);
    }
}

 
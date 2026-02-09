package com.example.hrms.service;
 
import org.springframework.stereotype.Service;

import com.example.hrms.entity.TaskComment;
import com.example.hrms.repository.TaskCommentRepository;

import io.micrometer.common.lang.Nullable;
 
@Service
public class TaskCommentService {
 
    private final TaskCommentRepository repo;
 
    public TaskCommentService(TaskCommentRepository repo) {
        this.repo = repo;
    }
 
    public TaskComment addComment(TaskComment comment) {
        return repo.save(comment);
    }
 
	public @Nullable Object getCommentsByTaskId(Integer taskId) {
		return null;
	}
}
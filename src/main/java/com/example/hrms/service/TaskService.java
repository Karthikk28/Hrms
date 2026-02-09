package com.example.hrms.service;

import com.example.hrms.entity.Task;
import java.util.List;

public interface TaskService {
    Task createTask(Task task);
    Task assignTask(Integer taskId, Integer employeeId);
    List<Task> getTasksByEmployeeId(Integer employeeId);
    List<Task> getAllTasks();
}

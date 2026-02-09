package com.example.hrms.service;

import com.example.hrms.entity.Employee;
import com.example.hrms.entity.Task;
import com.example.hrms.repository.EmployeeRepository;
import com.example.hrms.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }
    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }
    @Override
    public Task assignTask(Integer taskId, Integer employeeId) {
        Task task = taskRepository.findById(taskId).orElse(null);
        Employee employee = employeeRepository.findById(employeeId).orElse(null);

        if (task == null || employee == null) {
            return null;
        }
        task.setAssignedTo(employee);
        return taskRepository.save(task);
    }
    @Override
    public List<Task> getTasksByEmployeeId(Integer employeeId) {
        return taskRepository.findByAssignedToId(employeeId);
    }
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}

package com.hrms.service;
 
import com.hrms.dto.TaskAssigneeDTO;
import com.hrms.dto.TaskResponseDTO;
import com.hrms.entity.Task;
import com.hrms.entity.TaskAssignee;
import com.hrms.entity.TaskStatus;
import com.hrms.repository.ProjectMemberRepository;
import com.hrms.repository.TaskAssigneeRepository;
import com.hrms.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
 
@Service

public class TaskService {
 
    private final TaskRepository taskRepository;

    private final TaskAssigneeRepository taskAssigneeRepository;

    private final ProjectMemberRepository projectMemberRepository;
 
    public TaskService(

            TaskRepository taskRepository,

            TaskAssigneeRepository taskAssigneeRepository,

            ProjectMemberRepository projectMemberRepository

    ) {

        this.taskRepository = taskRepository;

        this.taskAssigneeRepository = taskAssigneeRepository;

        this.projectMemberRepository = projectMemberRepository;

    }
 
    public Task createTask(Task task) {

        if (task.getProjectId() == null) {

            throw new RuntimeException("Task must belong to a project");

        }

        return taskRepository.save(task);

    }
 
    public Task updateTaskStatus(Integer taskId, TaskStatus status) {

        Task task = taskRepository.findById(taskId)

                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setStatus(status);

        return taskRepository.save(task);

    }
 
    public List<Task> getAllTasks() {

        return taskRepository.findAll();

    }
 
 
    public List<Task> getMyTasks(Long employeeId) {

        return taskRepository.findTasksByEmployeeId(employeeId);

    }

    public Task updateTask(Integer id, Task updated) {

        Task existing = taskRepository.findById(id)

                .orElseThrow(() -> new RuntimeException("Task not found"));
 
        existing.setTitle(updated.getTitle());

        existing.setDescription(updated.getDescription());

        existing.setPriority(updated.getPriority());

        existing.setStatus(updated.getStatus());

        existing.setDueDate(updated.getDueDate());
 
        return taskRepository.save(existing);

    }

    public void assignEmployeeToTask(Integer taskId, Long employeeId) {
 
        Task task = taskRepository.findById(taskId)

                .orElseThrow(() -> new RuntimeException("Task not found"));
 
        Integer projectId = task.getProjectId();
 
        boolean isMember = projectMemberRepository

                .existsById_ProjectIdAndId_EmployeeId(projectId, employeeId);
 
        if (!isMember) {

            throw new RuntimeException("Employee is not part of this project");

        }
 
        TaskAssignee assignee = new TaskAssignee(taskId, employeeId);

        taskAssigneeRepository.save(assignee);

    }
 
    public TaskResponseDTO getTaskById(Integer taskId) {
 
        Task task = taskRepository.findById(taskId)

                .orElseThrow(() -> new RuntimeException("Task not found"));
 
        List<TaskAssigneeDTO> assignees =

                taskAssigneeRepository.findAssigneesWithNames(taskId);
 
        return new TaskResponseDTO(

                task.getId(),

                task.getProjectId(),

                task.getTitle(),

                task.getDescription(),

                task.getPriority().name(),

                task.getStatus().name(),

                task.getDueDate(),

                task.getCreatedAt(),

                task.getUpdatedAt(),

                assignees

        );

    }

}

 
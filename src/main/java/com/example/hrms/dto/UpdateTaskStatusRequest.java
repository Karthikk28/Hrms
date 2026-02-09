package com.example.hrms.dto;


import com.example.hrms.entity.TaskStatus;

public class UpdateTaskStatusRequest {

    private TaskStatus status;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }
}

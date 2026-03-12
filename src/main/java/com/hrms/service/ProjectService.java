package com.hrms.service;

import com.hrms.entity.Project;
import com.hrms.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository repo;

    public ProjectService(ProjectRepository repo) {
        this.repo = repo;
    }

    public Project createProject(Project project) {
        return repo.save(project);
    }

    public List<Project> getAllProjects() {
        return repo.findAll();
    }
}

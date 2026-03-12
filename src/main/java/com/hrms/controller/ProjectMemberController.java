package com.hrms.controller;

import com.hrms.dto.EmployeeSimpleDTO;
import com.hrms.dto.ProjectMemberDTO;
import com.hrms.entity.ProjectMember;
import com.hrms.service.ProjectMemberService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project-members")
@CrossOrigin
public class ProjectMemberController {

    private final ProjectMemberService service;

    public ProjectMemberController(ProjectMemberService service) {
        this.service = service;
    }

    @PostMapping
    public ProjectMember addMember(@RequestBody ProjectMemberDTO request) {

        return service.addMember(
                request.getProjectId(),
                request.getEmployeeId(),
                request.getRole()
        );
    }

    @GetMapping("/project/{projectId}")
    public List<ProjectMember> getMembersByProject(
            @PathVariable Integer projectId) {
        return service.getMembersByProject(projectId);
    }

    @GetMapping("/project/{projectId}/details")
    public List<ProjectMemberDTO> getMembersWithDetails(
            @PathVariable Integer projectId) {
        return service.getMembersWithDetails(projectId);
    }

    @GetMapping("/project/{projectId}/members")
    public List<EmployeeSimpleDTO> getProjectMembers(
            @PathVariable Integer projectId) {
        return service.getProjectMembers(projectId);
    }
}
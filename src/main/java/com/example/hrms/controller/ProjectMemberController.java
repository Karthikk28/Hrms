package com.example.hrms.controller;
import com.example.hrms.entity.ProjectMember;
import com.example.hrms.service.ProjectMemberService;
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
    public ProjectMember addMember(@RequestBody ProjectMember member) {
        return service.addMember(member);
    }
}
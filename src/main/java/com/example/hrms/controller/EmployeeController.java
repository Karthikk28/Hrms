package com.example.hrms.controller;

import com.example.hrms.entity.Employee;
import com.example.hrms.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        return service.create(employee);
    }
    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }
}

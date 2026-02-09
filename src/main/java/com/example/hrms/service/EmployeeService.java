package com.example.hrms.service;

import com.example.hrms.entity.Employee;
import com.example.hrms.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Employee create(Employee employee) {
        return repo.save(employee);
    }

    public List<Employee> getAll() {
        return repo.findAll();
    }
}

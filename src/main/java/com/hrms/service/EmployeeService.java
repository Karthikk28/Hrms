package com.hrms.service;

import java.util.List;

import com.hrms.entity.Employee;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);


    Employee updateEmployee(Long id, Employee updatedEmp);

    void deleteEmployee(Long id);
    
}

package com.hrms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hrms.entity.Employee;
import com.hrms.repository.EmployeeRepository;
import com.hrms.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeService employeeService,
                              EmployeeRepository employeeRepository) {
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    // GET ALL EMPLOYEES
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // GET EMPLOYEE BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE EMPLOYEE
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        Employee updatedEmployee = employeeService.updateEmployee(id, employee);

        if (updatedEmployee == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedEmployee);
    }

    // ADD EMPLOYEE
    @PostMapping
    public ResponseEntity<?> addEmployee(@RequestBody Employee employee) {

        try {
            Employee savedEmp = employeeService.addEmployee(employee);
            return ResponseEntity.ok(savedEmp);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to save employee");
        }
    }

    // DELETE EMPLOYEE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}
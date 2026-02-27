package com.hrms.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrms.entity.Employee;
import com.hrms.repository.EmployeeRepository;
import com.hrms.service.EmployeeService;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

 
    private final EmployeeService employeeService;
    
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @GetMapping
    public List<Employee> getAllEmployees() {
    	return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
    	Optional<Employee> employee = employeeRepository.findById(id);
        return employee.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }


   


    // Update employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        Optional<Employee> existingEmp = employeeRepository.findById(id);
        if (existingEmp.isEmpty()) return ResponseEntity.notFound().build();

        Employee emp = existingEmp.get();
        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());
        emp.setDepartment(employee.getDepartment());
        emp.setDesignation(employee.getDesignation());
        emp.setManager(employee.getManager());
        emp.setEmploymentStatus(employee.getEmploymentStatus());
        emp.setJoiningDate(employee.getJoiningDate());
        emp.setPhone(employee.getPhone());
        emp.setMaritalStatus(employee.getMaritalStatus());
        emp.setBloodGroup(employee.getBloodGroup());
        emp.setDob(employee.getDob());
        emp.setGender(employee.getGender());
        emp.setCurrentAddress(employee.getCurrentAddress());
        emp.setProfileImage(employee.getProfileImage());
        emp.setAnnualCtc(employee.getAnnualCtc());
        emp.setBankName(employee.getBankName());
        emp.setAccountNumber(employee.getAccountNumber());
        emp.setIfscCode(employee.getIfscCode());
        emp.setNomineeName(employee.getNomineeName());
        emp.setNomineeRelationship(employee.getNomineeRelationship());
        emp.setNomineeContact(employee.getNomineeContact());
        emp.setAadhaarCard(employee.getAadhaarCard());
        emp.setPanCard(employee.getPanCard());
        emp.setOfferLetter(employee.getOfferLetter());
        emp.setBankCheque(employee.getBankCheque());
        Employee updatedEmp = employeeRepository.save(emp);
        return ResponseEntity.ok(updatedEmp);
    }
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



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }
}


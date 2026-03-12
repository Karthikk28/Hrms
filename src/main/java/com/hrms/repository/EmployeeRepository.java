package com.hrms.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrms.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	 Optional<Employee> findByEmail(String email);
	 Optional<Employee> findById(Integer Id);

	    Optional<Employee> findByEmployeeCode(String employeeCode);
	    
}
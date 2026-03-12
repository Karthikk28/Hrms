package com.hrms.service;


import org.springframework.stereotype.Service;

import com.hrms.entity.Employee;
import com.hrms.repository.EmployeeRepository;
import com.hrms.entity.TeamMember;
import com.hrms.repository.TeamMemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamMemberRepository teamMemberRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               TeamMemberRepository teamMemberRepository) {
        this.employeeRepository = employeeRepository;
        this.teamMemberRepository = teamMemberRepository;
    }

    @Override
    public Employee addEmployee(Employee employee) {

        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new RuntimeException("Employee already exists with this email");
        }

        if (employeeRepository.findByEmployeeCode(employee.getEmployeeCode()).isPresent()) {
            throw new RuntimeException("Employee already exists with this employee code");
        }

        Employee savedEmployee = employeeRepository.save(employee);

        TeamMember member = new TeamMember();
        member.setEmployeeId(savedEmployee.getId());
        member.setName(savedEmployee.getFirstName() + " " + savedEmployee.getLastName());
        member.setRole(savedEmployee.getDesignation());
        member.setStatus("Active");

        teamMemberRepository.save(member);

        return savedEmployee;
    }



    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmp) {

        Employee emp = employeeRepository.findById(id).orElse(null);
        if (emp == null) return null;

        emp.setFirstName(updatedEmp.getFirstName());
        emp.setLastName(updatedEmp.getLastName());
        emp.setEmail(updatedEmp.getEmail());
        emp.setEmployeeCode(updatedEmp.getEmployeeCode());
        emp.setDepartment(updatedEmp.getDepartment());
        emp.setDesignation(updatedEmp.getDesignation());
        emp.setManager(updatedEmp.getManager());
        emp.setEmploymentStatus(updatedEmp.getEmploymentStatus());
        emp.setJoiningDate(updatedEmp.getJoiningDate());
        emp.setPhone(updatedEmp.getPhone());

        emp.setMaritalStatus(updatedEmp.getMaritalStatus());
        emp.setBloodGroup(updatedEmp.getBloodGroup());
        emp.setDob(updatedEmp.getDob());
        emp.setGender(updatedEmp.getGender());
        emp.setLocation(updatedEmp.getLocation());
        emp.setCurrentAddress(updatedEmp.getCurrentAddress());
        emp.setProfileImage(updatedEmp.getProfileImage());

        emp.setAnnualCtc(updatedEmp.getAnnualCtc());
        emp.setBankName(updatedEmp.getBankName());
        emp.setAccountNumber(updatedEmp.getAccountNumber());
        emp.setIfscCode(updatedEmp.getIfscCode());

        emp.setNomineeName(updatedEmp.getNomineeName());
        emp.setNomineeRelationship(updatedEmp.getNomineeRelationship());
        emp.setNomineeContact(updatedEmp.getNomineeContact());

        emp.setAadhaarCard(updatedEmp.getAadhaarCard());
        emp.setPanCard(updatedEmp.getPanCard());
        emp.setOfferLetter(updatedEmp.getOfferLetter());
        emp.setBankCheque(updatedEmp.getBankCheque());

        return employeeRepository.save(emp);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {

        
        teamMemberRepository.deleteByEmployeeId(id);

        
        employeeRepository.deleteById(id);
    }
}

package com.hrms.service;

import com.hrms.entity.Employee;
import com.hrms.repository.EmployeeRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class SettingsServiceImpl implements SettingsService {

    private final EmployeeRepository employeeRepository;

    public SettingsServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee getProfile(Integer employeeId) {
        return employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateProfile(Integer employeeId, Employee data) {
        Employee emp = getProfile(employeeId);

        emp.setFirstName(data.getFirstName());
        emp.setEmail(data.getEmail());
        emp.setPhone(data.getPhone());
        emp.setDesignation(data.getDesignation());
        emp.setDepartment(data.getDepartment());

        return employeeRepository.save(emp);
    }

    @Override
    public String updatePhoto(Integer employeeId, MultipartFile file) throws Exception {

    	String uploadDir = "C:\\hrms_profiles\\";
    	File folder = new File(uploadDir);
        if (!folder.exists()) folder.mkdirs();

        String fileName = employeeId + ".jpg";
        File dest = new File(uploadDir + fileName);

        file.transferTo(dest);

        Employee emp = getProfile(employeeId);
        emp.setProfileImage(uploadDir + fileName);

        employeeRepository.save(emp);

        return emp.getProfileImage();
    }
}

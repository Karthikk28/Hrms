package com.hrms.service;

import com.hrms.entity.Employee;
import org.springframework.web.multipart.MultipartFile;

public interface SettingsService {

    Employee getProfile(Integer empId);

    Employee updateProfile(Integer empId, Employee updatedData);

    String updatePhoto(Integer empId, MultipartFile file) throws Exception;
}

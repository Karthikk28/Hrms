package com.hrms.controller;

import com.hrms.entity.Employee;
import com.hrms.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/settings")
@CrossOrigin
public class SettingsController {

    @Autowired
    private SettingsService settingsService;

    @GetMapping("/me")
    public Employee getProfile(@RequestParam Integer empId) {
        return settingsService.getProfile(empId);
    }

    @PutMapping("/update")
    public Employee updateProfile(@RequestParam Integer empId, @RequestBody Employee data) {
        return settingsService.updateProfile(empId, data);
    }

    @PostMapping("/update-photo")
    public String updatePhoto(@RequestParam Integer empId,
                              @RequestParam("file") MultipartFile file) throws Exception {
        return settingsService.updatePhoto(empId, file);
    }
}

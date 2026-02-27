package com.hrms.controller;

import com.hrms.entity.AttendanceBreak;
import com.hrms.service.AttendanceBreakService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance/break")
@CrossOrigin
public class AttendanceBreakController {

    private final AttendanceBreakService breakService;

    public AttendanceBreakController(AttendanceBreakService breakService) {
        this.breakService = breakService;
    }

    @PostMapping("/start/{employeeId}")
    public void startBreak(@PathVariable Long employeeId) {
        breakService.startBreak(employeeId);
    }

    @PostMapping("/end/{employeeId}")
    public void endBreak(@PathVariable Long employeeId) {
        breakService.endBreak(employeeId);
    }

    @GetMapping("/today/{employeeId}")
    public List<AttendanceBreak> getTodayBreaks(@PathVariable Long employeeId) {
        return breakService.getBreaksForToday(employeeId);
    }
}
package com.hrms.controller;

import com.hrms.dto.AttendanceStatsResponse;
import com.hrms.dto.DailyAttendanceResponse;
import com.hrms.service.AttendanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
@CrossOrigin
public class AttendanceController {

    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping("/today/{employeeId}")
    public DailyAttendanceResponse getTodayAttendance(@PathVariable Long employeeId) {
        return attendanceService.getTodayAttendance(employeeId);
    }

    @GetMapping("/stats/{employeeId}")
    public AttendanceStatsResponse getStats(@PathVariable Long employeeId) {
        return attendanceService.getAttendanceStats(employeeId);
    }

    @PostMapping("/check-in/{employeeId}")
    public void checkIn(@PathVariable Long employeeId) {
        attendanceService.checkIn(employeeId);
    }

    @PostMapping("/check-out/{employeeId}")
    public void checkOut(@PathVariable Long employeeId) {
        attendanceService.checkOut(employeeId);
    }
}
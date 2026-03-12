package com.hrms.service;

import com.hrms.dto.AttendanceStatsResponse;
import com.hrms.dto.DailyAttendanceResponse;

public interface AttendanceService {

    DailyAttendanceResponse getTodayAttendance(Long employeeId);
    AttendanceStatsResponse getAttendanceStats(Long employeeId);
    void checkIn(Long employeeId);
    void checkOut(Long employeeId);
}
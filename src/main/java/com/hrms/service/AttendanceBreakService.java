package com.hrms.service;

import com.hrms.entity.AttendanceBreak;

import java.util.List;

public interface AttendanceBreakService {

    void startBreak(Long employeeId);

    void endBreak(Long employeeId);

    List<AttendanceBreak> getBreaksForToday(Long employeeId);
}
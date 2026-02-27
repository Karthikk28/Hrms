package com.hrms.service;

import com.hrms.dto.AttendanceStatsResponse;
import com.hrms.dto.DailyAttendanceResponse;
import com.hrms.entity.AttendanceRecord;
import com.hrms.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceServiceImpl(AttendanceRepository attendanceRepository) {
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public DailyAttendanceResponse getTodayAttendance(Long employeeId) {

        AttendanceRecord record = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElse(null);

        DailyAttendanceResponse response = new DailyAttendanceResponse();

        if (record != null) {
            response.setPunchIn(record.getCheckIn());
            response.setPunchOut(record.getCheckOut());
            response.setTotalHours(record.getTotalHours());
            response.setStatus(record.getStatus());
        } else {
            response.setStatus("ABSENT");
        }

        return response;
    }

    @Override
    public AttendanceStatsResponse getAttendanceStats(Long employeeId) {

        List<AttendanceRecord> records =
                attendanceRepository.findByEmployeeId(employeeId);

        AttendanceStatsResponse response = new AttendanceStatsResponse();

        double avgHours = records.stream()
                .filter(r -> r.getTotalHours() != null)
                .mapToDouble(AttendanceRecord::getTotalHours)
                .average()
                .orElse(0.0);

        response.setAverageHours(avgHours);
        response.setLateDays(0);
        response.setEarlyLeaves(0);

        return response;
    }

    @Override
    public void checkIn(Long employeeId) {

        LocalDate today = LocalDate.now();

        if (attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, today)
                .isPresent()) {
            return;
        }

        AttendanceRecord record = new AttendanceRecord();
        record.setEmployeeId(employeeId);
        record.setAttendanceDate(today);
        record.setCheckIn(LocalDateTime.now());
        record.setStatus("Active");

        attendanceRepository.save(record);
    }

    @Override
    public void checkOut(Long employeeId) {

        AttendanceRecord record = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElse(null);

        if (record == null || record.getCheckOut() != null) {
            return;
        }

        record.setCheckOut(LocalDateTime.now());

        long minutes = Duration
                .between(record.getCheckIn(), record.getCheckOut())
                .toMinutes();

        record.setTotalMinutes((int) minutes);
        record.setTotalHours(minutes / 60.0);
        record.setStatus("Completed");

        attendanceRepository.save(record);
    }
}
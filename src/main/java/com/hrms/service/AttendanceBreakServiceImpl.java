package com.hrms.service;

import com.hrms.entity.AttendanceBreak;
import com.hrms.entity.AttendanceRecord;
import com.hrms.repository.AttendanceBreakRepository;
import com.hrms.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AttendanceBreakServiceImpl implements AttendanceBreakService {

    private final AttendanceBreakRepository breakRepository;
    private final AttendanceRepository attendanceRepository;

    public AttendanceBreakServiceImpl(
            AttendanceBreakRepository breakRepository,
            AttendanceRepository attendanceRepository
    ) {
        this.breakRepository = breakRepository;
        this.attendanceRepository = attendanceRepository;
    }

    @Override
    public void startBreak(Long employeeId) {

        AttendanceRecord attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Employee not checked in"));

        AttendanceBreak attendanceBreak = new AttendanceBreak();
        attendanceBreak.setAttendanceId(attendance.getId());
        attendanceBreak.setBreakStart(LocalDateTime.now());

        breakRepository.save(attendanceBreak);
    }

    @Override
    public void endBreak(Long employeeId) {

        AttendanceRecord attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("Employee not checked in"));

        List<AttendanceBreak> breaks =
                breakRepository.findByAttendanceId(attendance.getId());

        AttendanceBreak activeBreak = breaks.stream()
                .filter(b -> b.getBreakEnd() == null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No active break"));

        activeBreak.setBreakEnd(LocalDateTime.now());

        long minutes = Duration
                .between(activeBreak.getBreakStart(), activeBreak.getBreakEnd())
                .toMinutes();

        activeBreak.setDurationMinutes((int) minutes);

        breakRepository.save(activeBreak);
    }

    @Override
    public List<AttendanceBreak> getBreaksForToday(Long employeeId) {

        AttendanceRecord attendance = attendanceRepository
                .findByEmployeeIdAndAttendanceDate(employeeId, LocalDate.now())
                .orElseThrow(() -> new RuntimeException("No attendance found"));

        return breakRepository.findByAttendanceId(attendance.getId());
    }
}
package com.hrms.repository;

import com.hrms.entity.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.List;

public interface AttendanceRepository
        extends JpaRepository<AttendanceRecord, Long> {

    Optional<AttendanceRecord>
    findByEmployeeIdAndAttendanceDate(Long employeeId, LocalDate attendanceDate);

    List<AttendanceRecord> findByEmployeeId(Long employeeId);
}
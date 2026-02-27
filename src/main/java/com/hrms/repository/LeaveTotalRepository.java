package com.hrms.repository;

import com.hrms.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaveTotalRepository extends JpaRepository<LeaveTotal, Long> {
    LeaveTotal findByEmployeeIdAndYear(Long employeeId, Integer year);
}
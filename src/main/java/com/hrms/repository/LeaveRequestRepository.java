package com.hrms.repository;

import com.hrms.entity.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long> {

    @Query("""
        select lr from LeaveRequest lr
        left join fetch lr.approvedBy
        where lr.employee.id = :employeeId
    """)
    List<LeaveRequest> findByEmployeeWithApprover(Long employeeId);
}
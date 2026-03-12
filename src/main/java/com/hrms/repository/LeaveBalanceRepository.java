package com.hrms.repository;

import com.hrms.entity.LeaveBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaveBalanceRepository extends JpaRepository<LeaveBalance, Long> {
    List<LeaveBalance> findByEmployee_Id(Long employeeId);
}

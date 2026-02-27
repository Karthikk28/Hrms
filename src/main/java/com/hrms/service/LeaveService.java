package com.hrms.service;

import com.hrms.entity.*;
import com.hrms.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveBalanceRepository balanceRepo;
    private final LeaveRequestRepository requestRepo;
    private final HolidayRepository holidayRepo;
    private final LeaveTotalRepository totalRepo;

    public LeaveService(
            LeaveBalanceRepository balanceRepo,
            LeaveRequestRepository requestRepo,
            HolidayRepository holidayRepo,
            LeaveTotalRepository totalRepo
    ) {
        this.balanceRepo = balanceRepo;
        this.requestRepo = requestRepo;
        this.holidayRepo = holidayRepo;
        this.totalRepo = totalRepo;
    }

    public List<LeaveBalance> getLeaveBalances(Long employeeId) {
        return balanceRepo.findByEmployee_Id(employeeId);
    }

    public List<LeaveRequest> getLeaveRequestsInternal(Long employeeId) {
        return requestRepo.findByEmployeeWithApprover(employeeId);
    }

    public List<Holiday> getHolidays() {
        return holidayRepo.findAllByOrderByDateAsc();
    }

    public Float getYearlyTotal(Long employeeId, Integer year) {
        LeaveTotal total = totalRepo.findByEmployeeIdAndYear(employeeId, year);
        return total != null ? total.getTotalDays() : 0f;
    }
}